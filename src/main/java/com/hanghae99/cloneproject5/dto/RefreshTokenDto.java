package com.hanghae99.cloneproject5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RefreshTokenDto {
    private String refreshToken;
    private String accessToken;
}
