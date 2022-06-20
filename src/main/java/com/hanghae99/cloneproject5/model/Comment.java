package com.hanghae99.cloneproject5.model;

import com.hanghae99.cloneproject5.dto.commentDto.CommentRequestDto;
import com.hanghae99.cloneproject5.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamp {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    public Comment(String content, Board board, Member member) {
        this.content = content;
        this.board = board;
        this.member = member;
    }

    public void update(CommentRequestDto registerDto) {

        this.content = registerDto.getContent();
    }
}
