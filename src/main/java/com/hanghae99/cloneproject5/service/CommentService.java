package com.hanghae99.cloneproject5.service;

import com.hanghae99.cloneproject5.dto.CommentRegisterDto;
import com.hanghae99.cloneproject5.dto.CommentRegisterResponseDto;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Comment;
import com.hanghae99.cloneproject5.repository.BoardRepository;
import com.hanghae99.cloneproject5.repository.CommentRepository;
import com.hanghae99.cloneproject5.repository.MemberRepository;
import com.hanghae99.cloneproject5.validation.CommentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    //public CommentRegisterResponseDto registerComment(Long boardId, CommentRegisterDto requestDto, UserDetailsImpl userDetails) {
    //Board board = boardRepository.findbyId(boardId).orElseThrow(() -> new NullPointerException("registerComment ID를 찾을 수 없습니다."));

    @Transactional
    public void updateComment(Long commentId, CommentRegisterDto registerDto) {

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



}
