package com.hanghae99.cloneproject5.dto.likeDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LikeResponseDto {

    private Boolean result;
    private Long likesNum;

    public LikeResponseDto(Long likesNum){
        this.result = result;
        this.likesNum = likesNum;
    }

}