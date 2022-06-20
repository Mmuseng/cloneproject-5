package com.hanghae99.cloneproject5.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // make spring security usable
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // AuthenticationManager Bean으로 할당
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // cors 적용
                .cors()
                .and()
                // csrf 비활성화
                .csrf().disable()
                // 세션 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 에러 발생 시, 401 에러로 일단 내뱉기..
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                // 회원가입, 로그인을 위한 요청들은 모두 허가
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                // 조회 기능을 맡는 GET /boards, /comments API 허가
                .antMatchers(HttpMethod.GET, "/boards", "/comments").permitAll()
                // 그 외의 다른 모든 API 는 인증 필요
                .anyRequest()
                .authenticated();

/*                // 기본 설정 비활성화. 활성화 시 비인증 사용자는 로그인 페이지로 리다이렉트
                .httpBasic().disable()
                // cors 적용
                .cors()
                .and()
                // csrf 보안 불필요함으로 인해 비활성화
                .csrf()
                .disable()
                // form 형태의 로그인 방식을 사용하지 않음.
                .formLogin().disable()
                // JWT 를 이용한 인증 방식이기 때문에 세션 생성을 무상태로 설정한다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 다음 리퀘스트에 대한 권한을 설정한다. permitAll(), hasRole() 등..
                .authorizeRequests()
                // /user 가 붙은 모든 요청은 모든 권한이 승인되어있다.
                .antMatchers("/user/**", "/boards").permitAll()
                // /boards, /comments 가 붙은 모든 GET 요청은 모든 권한이 승인되어있다.
                .antMatchers(HttpMethod.GET, "/boards", "/comments").permitAll()
                // 그 외의 모든 요청은 로그인한 사용자(인증된 사용자)만이 요청할 수 있다.
                .anyRequest().authenticated();*/
    }

    // cors 정책
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // 비밀번호 암호화를 위한 Encoder 설정
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
