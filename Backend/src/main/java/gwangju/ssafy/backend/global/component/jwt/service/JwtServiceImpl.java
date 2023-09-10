package gwangju.ssafy.backend.global.component.jwt.service;

import gwangju.ssafy.backend.domain.user.dto.UserDto;
import gwangju.ssafy.backend.domain.user.dto.UserLoginResponseDto;
import gwangju.ssafy.backend.global.component.jwt.JwtIssuer;
import gwangju.ssafy.backend.global.component.jwt.JwtParser;
import gwangju.ssafy.backend.global.component.jwt.JwtUtils;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import gwangju.ssafy.backend.global.component.jwt.repository.RefreshRepository;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.BEARER_PREFIX;
import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_EMAIL;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final JwtUtils jwtUtils;
    private final JwtIssuer jwtIssuer;
    private final JwtParser jwtParser;
    private final RefreshRepository refreshRepository;

    @Override
    public TokenDto issueToken(@NonNull TokenUserInfoDto info) {
        String accessToken = jwtIssuer.issueToken(
                info.toClaims(jwtUtils.getAccessTokenExpiredMin()), jwtUtils.getEncodedKey()
        );
        String refreshToken = jwtIssuer.issueToken(
                info.toClaims(jwtUtils.getRefreshTokenExpiredMin()), jwtUtils.getEncodedKey()
        );

        refreshRepository.save(info.getUserEmail(), refreshToken, jwtUtils.getRefreshTokenExpiredMin());

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(jwtUtils.getAccessTokenExpiredMin())
                .grantType(BEARER_PREFIX)
                .build();
    }

    // refresh 토큰을 통한 accessToken 재발급
    @Override
    public TokenDto refreshToken(@NonNull String refreshToken) {
        Claims claims = jwtParser.parseToken(refreshToken, jwtUtils.getEncodedKey());

        String savedToken = refreshRepository.find(claims.get(KEY_EMAIL, String.class))
                .orElseThrow(() -> new RuntimeException("토큰 재발급 오류 발생"));

        validateRefreshTokens(refreshToken, savedToken);

        return issueToken(TokenUserInfoDto.from(claims));
    }

    // refresh 토큰 유효성 검사
    private void validateRefreshTokens(String refreshToken, String savedToken) {
        if(!refreshToken.equals(savedToken)) {
            throw new RuntimeException("refresh 토큰이 같지 않습니다. 오류 발생");
        }
    }

    // access 토큰 파싱
    @Override
    public TokenUserInfoDto parseAccessToken(@NonNull String accessToken) {
        return TokenUserInfoDto.from(
                jwtParser.parseToken(accessToken, jwtUtils.getEncodedKey())
        );
    }

    // redisTemplate을 통해 refresh 토큰 삭제(블랙리스트 추가)
    @Override
    public void deleteRefreshToken(String userEmail) {
        refreshRepository.delete(userEmail);
    }
}
