package com.hanghae99.cloneproject5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupDto {
    private String username;
    private String password;
    private String passwordCheck;
    private String email;
    private String introduce;
    private String githubId;
    private String imgPath;
}
