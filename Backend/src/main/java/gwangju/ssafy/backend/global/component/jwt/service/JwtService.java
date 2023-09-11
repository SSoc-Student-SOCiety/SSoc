package gwangju.ssafy.backend.global.component.jwt.service;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.NonNull;

import java.util.Optional;

public interface JwtService {

    // 토큰 발급
    TokenDto issueToken(@NonNull TokenUserInfoDto info);

    // 토큰 재발급
    TokenDto refreshToken(@NonNull String refreshToken);

    // access 토큰 파싱
    TokenUserInfoDto parseAccessToken(@NonNull String accessToken);

    void deleteRefreshToken(String userEmail);

    Optional<String> find(String userEmail);

    // 복호화 작업
    Jws<Claims> getClaims(String jwt);

}
