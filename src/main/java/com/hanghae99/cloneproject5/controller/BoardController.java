package com.hanghae99.cloneproject5.controller;

import com.hanghae99.cloneproject5.dto.BoardRequestDto;
import com.hanghae99.cloneproject5.dto.BoardResponseDto;
import com.hanghae99.cloneproject5.dto.BoardUpdateDto;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    //게시글 등록
    @PostMapping("/boards")
    public void registerBoard(@RequestBody BoardRequestDto requestDto){
        // 시큐리티 연동 후 변경
        String username = "username";

        boardService.registerBoard(requestDto, username);

    }

    // 게시글 전체 조회
    @GetMapping("/boards")
    public List<BoardResponseDto> getAllBoard(){
        return boardService.getAllBoard();
    }

    // 게시글 상세 조회
    @GetMapping("/boards/{id}")
    public Board getBoardDetail(@PathVariable Long id){
        return boardService.getBoardDetail(id);
    }

    // 게시글 수정
    @PutMapping("/boards/{id}")
    public void updateBoard(@PathVariable Long id, @RequestBody BoardUpdateDto updateDto){
        boardService.updateBoard(id, updateDto);
    }

    // 게시글 삭제
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}