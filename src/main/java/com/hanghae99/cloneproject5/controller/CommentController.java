package com.hanghae99.cloneproject5.controller;

import com.hanghae99.cloneproject5.dto.requestDto.CommentRequestDto;
import com.hanghae99.cloneproject5.dto.responseDto.CommentResponseDto;
import com.hanghae99.cloneproject5.dto.responseDto.CreateCommentResponseDto;
import com.hanghae99.cloneproject5.model.TokenDecode;
import com.hanghae99.cloneproject5.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comments/{boardId}")
    public CreateCommentResponseDto createComment(@PathVariable Long boardId,
                                @RequestBody CommentRequestDto requestDto,
                                HttpServletRequest httpRequest) {

        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        return commentService.createComment(boardId, requestDto, decode);
    }

    // 댓글 조회
    @GetMapping("/comments/{boardId}")
    public List<CommentResponseDto> getComments(@PathVariable Long boardId) {

        return commentService.getComment(boardId);
    }

    // 댓글 수정
    @PutMapping("/comments/update/{commentid}")
    public String updateComment(@PathVariable Long commentId,
                                @RequestBody CommentRequestDto requestDto,
                                HttpServletRequest httpRequest) {

        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        return commentService.updateComment(commentId, requestDto, decode);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentid}")
    public String deleteComment(@PathVariable Long commentId,
                              HttpServletRequest httpRequest) {

        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        return commentService.deleteComment(commentId, decode);
    }
}
