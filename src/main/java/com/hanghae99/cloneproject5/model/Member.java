package com.hanghae99.cloneproject5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = true)
    private String githubId;

    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = true)
    private String introduce;

    @JsonIgnore
    @Column (nullable = false)
    private String password;

    public Member(String email, String githubId, String username, String introduce, String password) {
        this.email = email;
        this.githubId = githubId;
        this.username = username;
        this.introduce = introduce;
        this.password =password;
    }

}
