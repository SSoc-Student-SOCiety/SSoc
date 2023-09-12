package gwangju.ssafy.backend.global.component.jwt.service;
import gwangju.ssafy.backend.domain.user.dto.UserLoginResponseDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;

import java.util.Optional;

public interface JwtService {

    // 토큰 발급
    TokenDto issueToken(@NonNull TokenUserInfoDto info);

    // RefreshToken을 이용한 Access, Refresh 토큰 재발급
    TokenDto refreshToken(@NonNull String refreshToken);

    // access 토큰 파싱
    TokenUserInfoDto parseAccessToken(@NonNull String accessToken);

    // userEmail을 이용한 redis에 해당 유저의 refresh 토큰 삭제
    void deleteRefreshToken(String userEmail);

    Optional<String> find(String userEmail);

//    UserLoginResponseDto decryptionRefreshToken(HttpServletRequest request);

    // refreshToken을 이용한 토큰 복호화 작업
    TokenUserInfoDto decryptionRefreshToken(String refreshToken);
}
