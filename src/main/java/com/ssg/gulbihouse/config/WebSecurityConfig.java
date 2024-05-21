package com.ssg.gulbihouse.config;

import lombok.RequiredArgsConstructor;
import com.ssg.gulbihouse.service.UserDetailService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

// 실제 인증 처리를 하는 시큐리티 설정
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userService;
//    private final TokenProvider tokenProvider; // 1. TokenProvider를 주입받음


    // 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 2. JWT 토큰 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
        // http.addFilterBefore(new TokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);


        return http
                // 인증, 인가 설정
                .authorizeRequests(auth -> auth
                        .requestMatchers(   // 특정 요청과 일치하는 url에 대한 엑세스 설정
                                new AntPathRequestMatcher("/index"),
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/user")
                        ).permitAll()   // 누구나 접근 가능(위에서 설정한 url로 요청이 오면 인증/인가 없이도 접근)
                        .anyRequest()   // 위에 설정한 url이외의 요청에 대해 설정
                        .authenticated())  // 별도의 인가는 필요하지 않지만 인증이 성공된 상태여야 접근
                // 폼 기반 로그인 설정
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/ingredients") // 로그인이 되었을 때 이동할 경로 설정
                )
                //로그아웃 설정
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")     // 로그아웃 되었을 때 이동할 경로 설정
                        .invalidateHttpSession(true)    // 로그아웃 이후에 세션을 전체 삭제할지 여부
                )
                // csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    // 인증 관리자 관련 설정: 사용자 정보를 가져올 서비스를 재정의, 인증방법등을 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);        // 사용자 정보 서비스 설정
        authProvider.setPasswordEncoder(bCryptPasswordEncoder); //비밀번호를 암호화 하기 위한 인코더 설정
        return new ProviderManager(authProvider);
    }

    //패스워드 인코더로 사용할 빈 설정
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}