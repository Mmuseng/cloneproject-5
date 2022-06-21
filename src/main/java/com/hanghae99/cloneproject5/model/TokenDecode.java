package com.hanghae99.cloneproject5.model;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDecode {

    String token;
    String email;
    String username;
    String githubId;
    Long id;
    String introduce;

    public TokenDecode(DecodedJWT jwt) {
        this.token = jwt.getToken();
        this.id = Long.parseLong(jwt.getClaim("id").toString());
        this.email = jwt.getClaim("email").toString();
        this.username = jwt.getClaim("username").toString();
        this.githubId = jwt.getClaim("githubId").toString();
        this.introduce = jwt.getClaim("introduce").toString();

    }
}
