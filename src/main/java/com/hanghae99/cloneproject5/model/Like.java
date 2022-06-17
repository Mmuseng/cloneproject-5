package com.hanghae99.cloneproject5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Like {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne
    private Member member;

    @OneToOne
    private Board board;
}
