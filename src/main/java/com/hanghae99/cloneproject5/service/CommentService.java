package com.hanghae99.cloneproject5.service;

import com.hanghae99.cloneproject5.dto.commentDto.CommentRequestDto;
import com.hanghae99.cloneproject5.dto.commentDto.CommentResponseDto;
import com.hanghae99.cloneproject5.dto.commentDto.CreateCommentResponseDto;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Comment;
import com.hanghae99.cloneproject5.model.Member;
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

//    public CommentRegisterResponseDto createComment(Long boardId, CommentRegisterDto requestDto, UserDetailsImpl userDetails) {
//        Board board = boardRepository.findbyId(boardId).orElseThrow(() -> new NullPointerException("registerComment ID를 찾을 수 없습니다."));
//        CommentValidation.validationCommentRegister(requestDto);
//
//        Member member = memberRepository.findById(userDetails.getUser().getId()).orElseThrow(("createComment ID 오류"));
//        Comment comment = new Comment(requestDto.getContent, board, member);
//        commentRepository.save(comment);
//
//        CreateCommentResponseDto commentResponseDto = new CreateCommentResponseDto();
//        commentResponseDto.setComment_Id(comment.getId());
//        commentResponseDto.setCreatedAt(comment.getCreatedAt());
//
//        return commentResponseDto;
//    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto registerDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("updateComment ID를 찾을 수 없습니다."));

        Optional<Board> board = boardRepository.findById(comment.getBoard().getId());

        if (!board.isPresent()) {
            try {
                throw new IllegalAccessException("해당 게시글이 없습니다.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        CommentValidation.validationCommentRegister(registerDto);

        comment.update(registerDto);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("ID를 찾을 수 없습니다."));
        commentRepository.delete(comment);
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
