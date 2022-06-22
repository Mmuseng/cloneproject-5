package com.hanghae99.cloneproject5.dto.responseDto;

import com.hanghae99.cloneproject5.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDtoByTag {
    private Long id;
    private String title;
    private String username;
    private String contentSummary;
    private String thumbnail;
    private LocalDateTime date;

    public BoardResponseDtoByTag(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contentSummary = board.getContentSummary();
        this.thumbnail = board.getImgPath();
        this.date = board.getModifiedAt();
    }
}
