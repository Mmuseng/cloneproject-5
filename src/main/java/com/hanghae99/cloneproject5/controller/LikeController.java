package com.hanghae99.cloneproject5.controller;

import com.hanghae99.cloneproject5.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

//    @GetMapping("/like/{boardId}")
//    public BoardGetLikeResponseDto getLikeResponseDto (@PathVariable Long boardId, @AuthticationPrincipal UserDetailsImpl userDetails) {
//
//        return likeService.getBoardsLike(boardId, userDetails);
//    }
}
