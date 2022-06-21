package com.hanghae99.cloneproject5.service;

import com.hanghae99.cloneproject5.dto.requestDto.LikeDto;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Like;
import com.hanghae99.cloneproject5.model.Member;
import com.hanghae99.cloneproject5.model.TokenDecode;
import com.hanghae99.cloneproject5.repository.BoardRepository;
import com.hanghae99.cloneproject5.repository.LikeRepository;
import com.hanghae99.cloneproject5.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public void uplike(TokenDecode decode, Long boardId, LikeDto behavior) {

        Member member = memberRepository.findById(decode.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        Like like = new Like(board, member);

        if (behavior.getBehavior().equals("like")) {
            likeRepository.save(like);
        } else if (behavior.getBehavior().equals("unlike")) {
            likeRepository.delete(likeRepository.findByBoardAndMember(board, member));
        }

        Long count = (long) likeRepository.findAllByBoard(board).size();

        board.setLikesCnt(count);
    }

    // 해당 게시글의 사용자 좋아요 유무 전달
    public boolean getLike(Long boardId, TokenDecode decode) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 없습니다.")
        );

        Member member = memberRepository.findById(decode.getId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );

        return likeRepository.existsByBoardAndMember(board, member);
    }


}
