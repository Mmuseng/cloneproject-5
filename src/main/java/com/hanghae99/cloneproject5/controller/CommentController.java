package com.hanghae99.cloneproject5.controller;

import com.hanghae99.cloneproject5.dto.requestDto.CommentRequestDto;
import com.hanghae99.cloneproject5.dto.responseDto.CommentResponseDto;
import com.hanghae99.cloneproject5.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

//    @PostMapping("/comments/{boardId}")
//    public CreateCommentResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

//        return commentService.createComment(boardId, requestDto, userDetails);
//    }

    // 댓글 조회
    @GetMapping("/comments/{boardId}")
    public List<CommentResponseDto> getComments(@PathVariable Long boardId) {

        return commentService.getComment(boardId);
    }

    // 댓글 수정
    @PutMapping("/comments/update/{commentid}")
    public void updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {

        commentService.updateComment(commentId, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentid}")
    public void deleteComment(@PathVariable Long commentId){

        commentService.deleteComment(commentId);
    }
}
