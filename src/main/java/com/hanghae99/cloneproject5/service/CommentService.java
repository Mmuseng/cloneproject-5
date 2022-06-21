package com.hanghae99.cloneproject5.service;

import com.hanghae99.cloneproject5.dto.requestDto.CommentRequestDto;
import com.hanghae99.cloneproject5.dto.responseDto.CommentResponseDto;
import com.hanghae99.cloneproject5.dto.responseDto.CreateCommentResponseDto;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Comment;
import com.hanghae99.cloneproject5.model.Member;
import com.hanghae99.cloneproject5.model.TokenDecode;
import com.hanghae99.cloneproject5.repository.BoardRepository;
import com.hanghae99.cloneproject5.repository.CommentRepository;
import com.hanghae99.cloneproject5.repository.MemberRepository;
import com.hanghae99.cloneproject5.validation.CommentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    // 댓글 작성 로직
    public CreateCommentResponseDto createComment(Long boardId, CommentRequestDto requestDto, TokenDecode decode) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("registerComment ID를 찾을 수 없습니다."));
        CommentValidation.validationCommentRegister(requestDto);

        Member member = memberRepository.findById(decode.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );

        Comment comment = new Comment(requestDto.getContent(), board, member);
        commentRepository.save(comment);

        CreateCommentResponseDto commentResponseDto = new CreateCommentResponseDto();
        commentResponseDto.setComment_Id(comment.getId());
        commentResponseDto.setCreatedAt(comment.getCreatedAt());

        return commentResponseDto;
    }

    // 댓글 수정 로직
    @Transactional
    public String updateComment(Long commentId, CommentRequestDto requestDto, TokenDecode decode) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (comment.getMember().getId().equals(decode.getId())) {
            comment.setContent(requestDto.getContent());
            commentRepository.save(comment);
            return "댓글이 수정 되었습니다.";
        } else {
            return "다른 사람의 댓글입니다.";
        }
    }

    public String deleteComment(Long commentId, TokenDecode decode) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("ID를 찾을 수 없습니다.")
        );
        if (comment.getMember().getId().equals(decode.getId())) {
            commentRepository.deleteById(commentId);
            return "댓글이 삭제되었습니다.";
        } else {
            return "다른 사람의 댓글입니다.";
        }
    }

    public List<CommentResponseDto> getComment(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("getComment ID 오류"));
        List<Comment> commentList = commentRepository.findAllByBoard(board);

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentResponseDto commentResponseDto = new CommentResponseDto();
            commentResponseDto.setContent(comment.getContent());
            commentResponseDto.setUsername(comment.getMember().getUsername());
            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }

}
