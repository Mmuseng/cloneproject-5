package com.hanghae99.cloneproject5.model;

import com.hanghae99.cloneproject5.domain.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board extends Timestamp {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (nullable = false)
    private String title;

    @Column (nullable = false)
    private String content;

    @Column (nullable = true)
    private String contentSummary;

    @Column (nullable = true)
    private String imgPath;

    @ManyToOne
    private Member member;
}
