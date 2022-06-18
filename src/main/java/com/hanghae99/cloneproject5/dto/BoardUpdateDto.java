package com.hanghae99.cloneproject5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateDto {
    private String title;
    private String content;
    private String contentSummary;
}
