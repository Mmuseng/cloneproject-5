package com.hanghae99.cloneproject5.dto.responseDto;

import com.hanghae99.cloneproject5.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    // id date -> 수정됨 프론트와 협의하기
    private Long id;
    private String username;
    private String title;
    private String contentSummary;
    private String imgPath;
    private LocalDateTime date;

    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contentSummary = board.getContentSummary();
        this.imgPath = board.getImgPath();
        this.date = board.getModifiedAt();
    }
    
}
