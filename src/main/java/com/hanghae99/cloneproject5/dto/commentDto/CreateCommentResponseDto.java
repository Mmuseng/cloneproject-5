package com.hanghae99.cloneproject5.dto.commentDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateCommentResponseDto {

    private Long comment_Id;
    private String createdAt;

}
