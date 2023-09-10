package gwangju.ssafy.backend.global.component.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Getter
@Component
public class JwtUtils {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String KEY_ID = "id";
    public static final String KEY_EMAIL = "userEmail";
    public static final String KEY_NAME = "userName";
    public static final String KEY_NICKNAME = "userNickname";
    public static final String KEY_ROLES = "roles";

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expired-min.access}")
    private int accessTokenExpiredMin;

    @Value("${jwt.expired-min.refresh}")
    private int refreshTokenExpiredMin;

    private Key encodedKey;

    // 로직 탈때 수행됨
    @PostConstruct
    private void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        encodedKey = Keys.hmacShaKeyFor(keyBytes);
    }
}
