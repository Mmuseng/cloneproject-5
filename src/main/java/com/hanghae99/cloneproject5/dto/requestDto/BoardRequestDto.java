package com.hanghae99.cloneproject5.dto.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {

    private String title;
    private String content;
    private String contentSummary;
    private String imgPath;
    private String tagStrings;

}
