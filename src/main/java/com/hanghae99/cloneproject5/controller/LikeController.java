package com.hanghae99.cloneproject5.controller;

import com.hanghae99.cloneproject5.dto.requestDto.LikeDto;
import com.hanghae99.cloneproject5.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    //좋아요 등록
    @PostMapping("/like/{boardId}")
    public void uplike(HttpServletRequest httpRequest,
                       @PathVariable Long boardId,
                       @RequestBody LikeDto behavior) {
        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        likeService.uplike(decode, boardId, behavior);
    }

    // 좋아요 조회
    @GetMapping("/viewlikes/{boardId}")
    public boolean getlike(@PathVariable Long boardId,
                           HttpServletRequest httpRequest) {
        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");

        return likeService.getLike(boardId, decode);
    }
}
