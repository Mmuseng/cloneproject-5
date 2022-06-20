package com.hanghae99.cloneproject5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "LIKE_TABLE")
public class Like {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    public Like(Board board, Member member) {
        this.board = board;
        this.member = member;
    }
}
