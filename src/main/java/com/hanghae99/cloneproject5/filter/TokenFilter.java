package com.hanghae99.cloneproject5.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae99.cloneproject5.dto.MemberTokenRespDto;
import com.hanghae99.cloneproject5.model.TokenDecode;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        MemberTokenRespDto tokenRespDto = null;
        String url = httpRequest.getRequestURI();

        if (url.startsWith("/comment") || url.startsWith("/like") || url.startsWith("/boards")) {
            String authorization = httpRequest.getHeader("Authorization");

            if (authorization == null || !authorization.startsWith("Bearer ")) {
                tokenRespDto = new MemberTokenRespDto(false,"토큰이 유효하지 않습니다.");
            }

            if (tokenRespDto == null) {
                // 인증 성공
                String token = authorization.substring(7); // Bearer 토큰의 앞부분 잘라줌

                DecodedJWT jwt = null;

                try {
                    Algorithm algorithm = Algorithm.HMAC256("fhrmdlsdlek"); //use more secure key
                    JWTVerifier verifier = JWT.require(algorithm)
                            .withIssuer("hanghae99") // 발급자
                            .build(); //Reusable verifier instance

                    jwt = verifier.verify(token);

                    TokenDecode tokenDecode = new TokenDecode(jwt);
                    httpRequest.setAttribute("decode", tokenDecode);
                } catch (JWTVerificationException exception) {
                    tokenRespDto = new MemberTokenRespDto(false, exception.getMessage());
                }
            }
        }

        if (tokenRespDto != null) {
            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_OK);

            OutputStream out = httpResponse.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(out, tokenRespDto);
            out.flush();
            return;
        }

        chain.doFilter(httpRequest, httpResponse);
    }

}
