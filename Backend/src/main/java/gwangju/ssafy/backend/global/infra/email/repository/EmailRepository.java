package gwangju.ssafy.backend.global.infra.email.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class EmailRepository {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String EMAIL_SIGNUP_CODE = "signupCode::";

    public void save(String userEmail, String signupCode, int expiresMin) {
        String key = EMAIL_SIGNUP_CODE + userEmail;
        redisTemplate.opsForValue().set(key, signupCode);
        redisTemplate.expire(key, expiresMin, TimeUnit.MINUTES); // 만료 시간 설정
    }

    public Optional<String> findSignupCode(String userEmail) {
        String signupCode = redisTemplate.opsForValue().get(EMAIL_SIGNUP_CODE + userEmail);

        return Optional.ofNullable(signupCode);
    }

    public void deleteSignupCode(String userEmail) {
        redisTemplate.delete(EMAIL_SIGNUP_CODE + userEmail);
    }


}
