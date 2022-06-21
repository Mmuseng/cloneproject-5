package com.hanghae99.cloneproject5.controller;

import com.hanghae99.cloneproject5.dto.requestDto.BoardRequestDto;
import com.hanghae99.cloneproject5.dto.responseDto.BoardResponseDto;
import com.hanghae99.cloneproject5.dto.requestDto.BoardUpdateDto;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.TokenDecode;
import com.hanghae99.cloneproject5.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    //게시글 등록
    @PostMapping("/boards")
    public void registerBoard(HttpServletRequest httpRequest, @RequestBody BoardRequestDto requestDto){
        // 시큐리티 연동 후 변경
        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");

        boardService.registerBoard(requestDto, decode);

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
    public void updateBoard(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody BoardUpdateDto updateDto){
        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        boardService.updateBoard(id, updateDto, decode);
    }

    // 게시글 삭제
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(HttpServletRequest httpRequest, @PathVariable Long id) {
        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        boardService.deleteBoard(id, decode);
    }
}
