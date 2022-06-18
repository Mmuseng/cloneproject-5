package com.hanghae99.cloneproject5.service;

import com.hanghae99.cloneproject5.dto.likeDto.CreateLikeResponseDto;
import com.hanghae99.cloneproject5.dto.likeDto.LikeResponseDto;
import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Like;
import com.hanghae99.cloneproject5.model.Member;
import com.hanghae99.cloneproject5.repository.BoardRepository;
import com.hanghae99.cloneproject5.repository.LikeRepository;
import com.hanghae99.cloneproject5.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    public CreateLikeResponseDto createLike (Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        if (!board.isPresent()) {
            try {
                throw new IllegalAccessException("해당 게시글이 없습니다.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        // test code
        Optional<Member> member = memberRepository.findById(1L);
        if (!member.isPresent()) {
            try {
                throw new IllegalAccessException("해당 유저가 없습니다.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        Optional<Like> likeCheck = likeRepository.findByBoardAndMember(board.get(), member.get());
        if (likeCheck.isPresent()) {
            try {
                throw new IllegalAccessException("이미 좋아요 상태입니다.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        Like like = new Like(board.get(), member.get());
        likeRepository.save(like);

        return new CreateLikeResponseDto(like.getId());
    }

    public void deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId).orElseThrow(() -> new IllegalArgumentException("deleteLike ID 오류"));
        likeRepository.delete(like);
    }

//    public LikeResponseDto createOrDeleteLike (Long boardId, UserDetailsImpl userDetails) {
//        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("createOrDeleteLike ID 오류"));
//        Member member = userDetails.getUser();
//        Optional<Like> like = likeRepository.findByBoardAndMember(board, member);
//        if (like.isPresent()) {
//            likeRepository.delete(like.get());
//            //board.updateLikeCount(board.getLikeCount()-1L);
//            boardRepository.save(board);
//            return new LikeResponseDto(false, board.getlikeCount);
//        }
//        Like newLike = new Like(board, member);
//        likeRepository.save(newLike);
//        board.updateLikeCount(board.getLikeCount()+1L);
//        boardRepository.save(board);
//        return new LikeResponseDto(true, board.getLikeCount());
//    }

    public LikeResponseDto getBoardsLike(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("getBoardLike ID 오류"));

        List <Like> likeList = likeRepository.findAllByBoard(board);
        Long listSize = (long) likeList.size();

        return new LikeResponseDto(listSize);
    }
}
