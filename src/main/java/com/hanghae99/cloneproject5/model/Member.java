package com.hanghae99.cloneproject5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanghae99.cloneproject5.dto.SignupDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (nullable = false)
    private String email;

    @Column (nullable = true)
    private String githubId;

    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = true)
    private String introduce;

    @Column(nullable = true)
    private String imgPath;

    @JsonIgnore
    @Column (nullable = false)
    private String password;

    @Transient
    private String passwordCheck;

    public Member(SignupDto signupDto){
        this.username = signupDto.getUsername();
        this.password = signupDto.getPassword();
        this.email = signupDto.getEmail();
        this.introduce = signupDto.getIntroduce();
        this.githubId = signupDto.getGithubId();
    }

}
