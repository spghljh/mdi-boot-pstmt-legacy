package kr.co.mdi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/home", "/about", "/product/**", "/register", "/login",
                    "/css/**", "/js/**", "/image/**"
                ).permitAll() // 공개 경로
                .requestMatchers("/mypage/**", "/cart/**", "/order/**").authenticated() // 로그인 필요
            )
            .formLogin(form -> form
                .loginPage("/login") // 커스텀 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공 후 이동
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }
}
