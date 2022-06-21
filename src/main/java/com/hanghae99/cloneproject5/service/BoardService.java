package com.hanghae99.cloneproject5.service;

import com.hanghae99.cloneproject5.dto.requestDto.BoardRequestDto;
import com.hanghae99.cloneproject5.dto.responseDto.BoardResponseDto;
import com.hanghae99.cloneproject5.dto.requestDto.BoardUpdateDto;
import com.hanghae99.cloneproject5.dto.responseDto.BoardResponseDtoByTag;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Member;
import com.hanghae99.cloneproject5.model.Tag;
import com.hanghae99.cloneproject5.model.TokenDecode;
import com.hanghae99.cloneproject5.repository.BoardRepository;
import com.hanghae99.cloneproject5.repository.MemberRepository;
import com.hanghae99.cloneproject5.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    
    @Transactional // 게시글 등록
    public void registerBoard(BoardRequestDto requestDto, TokenDecode decode) {
        Member member = memberRepository.findById(decode.getId()).orElse(null);
            Tag tag = new Tag(requestDto.getTagStrings());
            tagRepository.save(tag);
            if ( member != null && tag.getNameList().size() != 0){
                Board board = new Board(requestDto, member);
                board.setTagString(requestDto.getTagStrings());
                boardRepository.save(board);
            }else if (member != null){
                Board board = new Board(requestDto, member);
                boardRepository.save(board);
            }else{
            throw new IllegalArgumentException("조회된 회원이 없습니다.");
        }
    }

    // 게시글 전체 조회
    public List<BoardResponseDto> getAllBoard() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardResponseDto responseDto = new BoardResponseDto(board);
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }
    
    // 게시글 상세 조회
    public Board getBoardDetail(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다.")
        );
    }

    // 게시글 수정
    public void updateBoard(Long id, BoardUpdateDto updateDto, TokenDecode decode) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다.")
        );

        if ( decode.getId().equals(board.getMember().getId())){
            board.update(updateDto);
            boardRepository.save(board);
        }else{
            throw new IllegalArgumentException("본인의 게시글만 수정할 수 있습니다.");
        }

    }

    // 게시글 삭제
    public void deleteBoard(Long id, TokenDecode decode) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다.")
        );
        if ( decode.getId().equals(board.getMember().getId())){
            boardRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("본인의 게시글만 삭제할 수 있습니다.");
        }
    }

    // Tag를 포함한 Board 전체 조회
    public List<BoardResponseDtoByTag> getBoardByTag(String tag) {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDtoByTag> responseDtoByTagList = new ArrayList<>();

        for (Board board : boardList) {
            if ( board.getTagString().contains(tag)){
                BoardResponseDtoByTag boardResponseDtoByTag = new BoardResponseDtoByTag(board);
                responseDtoByTagList.add(boardResponseDtoByTag);
            }
        }

        return responseDtoByTagList;
    }
}
