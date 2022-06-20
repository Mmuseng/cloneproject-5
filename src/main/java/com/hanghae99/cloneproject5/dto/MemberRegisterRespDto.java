package com.hanghae99.cloneproject5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberRegisterRespDto {

    boolean result;
    String errMsg;

    public MemberRegisterRespDto(boolean result, String errMsg) {
        this.result = result;
        this.errMsg = errMsg;
    }
}
