package gwangju.ssafy.backend.global.config;

import gwangju.ssafy.backend.global.component.jwt.JwtFilter;
import gwangju.ssafy.backend.global.component.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * TokenProvider, RedisTemplate, JwtFilter를 SecurityConfig에 적용할 때 사용
 */
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;
    private final StringRedisTemplate redisTemplate;

    // TokenProvider를 주입받아서 JwtFilter를 통해 Security 로직에 필터를 적용
//    @Bean
//    public void configure(HttpSecurity httpSecurity) {
//        JwtFilter customFilter = new JwtFilter(tokenProvider, redisTemplate);
//        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//    }

}
