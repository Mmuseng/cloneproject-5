package com.hanghae99.cloneproject5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberLoginRespDto {

    boolean result;
    String errMsg;
    String token;
    String refreshToken;

    public MemberLoginRespDto(boolean result, String errMsg) {
        this.result = result;
        this.errMsg = errMsg;
    }

    public MemberLoginRespDto(boolean result, String token, String refreshToken, String errMsg) {
        this.result = result;
        this.errMsg = errMsg;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
