package gwangju.ssafy.backend.global.component.jwt.service;

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
public class JwtParserServiceImpl implements JwtParserService {

    private final JwtIssuer jwtIssuer;
    private final JwtParser jwtParser;
    private final JwtUtils jwtUtils;
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

    @Override
    public TokenDto refreshToken(@NonNull String refreshToken) {
        Claims claims = jwtParser.parseToken(refreshToken, jwtUtils.getEncodedKey());

        String savedToken = refreshRepository.find(claims.get(KEY_EMAIL, String.class))
                .orElseThrow(() -> new RuntimeException("RefreshToken 만료되었습니다."));

        validateRefreshTokens(refreshToken, savedToken);

        return issueToken(TokenUserInfoDto.from(claims));
    }

    @Override
    public void validateRefreshTokens(String refreshToken, String savedToken) {
        if(!refreshToken.equals(savedToken)) {
            throw new RuntimeException("refresh 토큰값이 같지 않습니다.");
        }
    }


}
