package com.hanghae99.cloneproject5.dto.likeDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateLikeResponseDto {

    private Long likesId;

    public CreateLikeResponseDto(Long likesId){
        this.likesId = likesId;
    }

}