package com.hanghae99.cloneproject5.dto.responseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateCommentResponseDto {

    private Long comment_Id;
    private LocalDateTime createdAt;

}
