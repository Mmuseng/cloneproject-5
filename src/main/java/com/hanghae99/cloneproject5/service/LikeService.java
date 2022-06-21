package com.hanghae99.cloneproject5.service;

import com.hanghae99.cloneproject5.dto.requestDto.LikeDto;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Like;
import com.hanghae99.cloneproject5.model.Member;
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
    public void uplike(Long boardId, Long memberId ,LikeDto behavior) { // 토큰 작업시 member - decode

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        Like like = new Like(member, board);

        if (behavior.getBehavior().equals("like")) {
            likeRepository.save(like);
        } else if (behavior.getBehavior().equals("unlike")) {
            likeRepository.delete(likeRepository.findByBoardAndMember(member, board));
        }

        Long count = (long) likeRepository.findAllByBoard(board).size();
        // 좋아요 카운트 - 형준님이랑 의논하긔
        board.setLikesCnt(count);
    }

    public boolean getLike(Long boardId, Long memberId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 없습니다.")
        );

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );

        return likeRepository.existsByBoardAndMember(board, member);
    }


}
