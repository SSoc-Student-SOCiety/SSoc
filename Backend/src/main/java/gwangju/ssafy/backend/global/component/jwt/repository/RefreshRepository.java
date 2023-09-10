package gwangju.ssafy.backend.global.component.jwt.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RefreshRepository {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String KEY_PREFIX = "refreshToken::";

    public void save(String userEmail, String token, int expiresMin) {
        redisTemplate.opsForValue().set(KEY_PREFIX + userEmail, token, Duration.ofMinutes(expiresMin));
    }

    public boolean exists(String userEmail) {
        String token = redisTemplate.opsForValue().get(KEY_PREFIX + userEmail);

        if(!StringUtils.hasText(token)) {
            return false;
        }

        return true;
    }

    public Optional<String> find(String userEmail) {
        String token = redisTemplate.opsForValue().get(KEY_PREFIX + userEmail);

        return Optional.ofNullable(token);
    }

    public void delete(String userEmail) {
        redisTemplate.delete(KEY_PREFIX + userEmail);
    }
}
