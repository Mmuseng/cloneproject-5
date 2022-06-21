package com.hanghae99.cloneproject5.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberTokenRespDto {

    boolean result;
    String errMsg;
    String payload;

    public MemberTokenRespDto(boolean result, String errMsg) {
        this.result = result;
        this.errMsg = errMsg;
    }

    public MemberTokenRespDto(boolean result, String payload, String errMsg) {
        this.result = result;
        this.payload = payload;
        this.errMsg = errMsg;
    }
}
