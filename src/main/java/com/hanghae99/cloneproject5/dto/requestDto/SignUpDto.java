package com.hanghae99.cloneproject5.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SignUpDto {

    private String email;
    private String githubId;
    private String username;
    private String introduce;
    private String password;

}
