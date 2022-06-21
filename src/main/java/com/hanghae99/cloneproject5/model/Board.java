package com.hanghae99.cloneproject5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanghae99.cloneproject5.dto.requestDto.BoardRequestDto;
import com.hanghae99.cloneproject5.dto.requestDto.BoardUpdateDto;
import com.hanghae99.cloneproject5.util.Timestamp;
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

    @Column (nullable = false, length = 2000)
    private String content;

    @Column (nullable = true, length = 2000)
    private String contentSummary;

    @Column (nullable = true, length = 2000)
    private String imgPath;

    @JsonIgnore
    @ManyToOne
    private Member member;

    @Column
    private String username;

    @Column
    private String tagString;

    @Column
    private Long likesCnt;

    public Board(BoardRequestDto requestDto, String username, Tag tag){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.contentSummary = requestDto.getContentSummary();

        this.username = username;
    }
    public Board(BoardRequestDto requestDto, Member member){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.contentSummary = requestDto.getContentSummary();
        this.imgPath = requestDto.getImagePath();

        // 게시글 작성자 추가
        this.member = member;
        this.username = member.getUsername();
    }

    public void update(BoardUpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
        this.contentSummary = updateDto.getContentSummary();
    }
}
