package gwangju.ssafy.backend.global.component.jwt.service.impl;

import gwangju.ssafy.backend.domain.user.exception.UserException;
import gwangju.ssafy.backend.global.component.jwt.JwtIssuer;
import gwangju.ssafy.backend.global.component.jwt.JwtParser;
import gwangju.ssafy.backend.global.component.jwt.JwtUtils;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import gwangju.ssafy.backend.global.component.jwt.repository.RefreshRepository;
import gwangju.ssafy.backend.global.component.jwt.service.JwtService;
import gwangju.ssafy.backend.global.exception.GlobalError;
import gwangju.ssafy.backend.global.exception.GlobalException;
import gwangju.ssafy.backend.global.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.*;

@Slf4j
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
                .orElseThrow(() -> new TokenException(GlobalError.REDIS_NOT_TOKEN));

        log.info(savedToken);
        validateRefreshTokens(refreshToken, savedToken);

        return issueToken(TokenUserInfoDto.from(claims));
    }

    // refresh 토큰 유효성 검사
    private void validateRefreshTokens(String refreshToken, String savedToken) {
        if(!refreshToken.equals(savedToken)) {
            throw new TokenException(GlobalError.NOT_VALIDATE_TOKEN);
        }
    }

    // access 토큰 파싱
    @Override
    public TokenUserInfoDto parseAccessToken(@NonNull String accessToken) {
        Claims claims = jwtParser.parseToken(accessToken, jwtUtils.getEncodedKey());
        if(claims == null) {
            return null;
        }

        return TokenUserInfoDto.from(claims);
    }

    // redisTemplate을 통해 refresh 토큰 삭제(블랙리스트 추가)
    @Override
    public void deleteRefreshToken(String userEmail) {
        refreshRepository.delete(userEmail);
    }

    @Override
    public Optional<String> find(String userEmail) {
        return refreshRepository.find(userEmail);
    }

    // refreshToken을 이용한 토큰 복호화 작업
    @Override
    public TokenUserInfoDto decryptionRefreshToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtUtils.getEncodedKey())
                .build()
                .parseClaimsJws(refreshToken).getBody();

        return TokenUserInfoDto.builder()
                .id(claims.get(KEY_ID, Long.class))
                .userEmail(claims.get(KEY_EMAIL, String.class))
                .userName(claims.get(KEY_NAME, String.class))
                .userNickname(claims.get(KEY_NICKNAME, String.class))
                .userImageUrl(claims.get(KEY_IMAGEURL, String.class))
                .role(claims.get(KEY_ROLES, String.class))
                .build();
    }


    // Refresh 토큰 복호화
//    @Override
//    public UserLoginResponseDto decryptionRefreshToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(JwtAuthenticationFilter.REFRESH_HEADER);
//        UserLoginResponseDto userLoginResponseDto = null;
//        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(bearerToken)) {
//            String refreshToken = bearerToken.substring(7);
//
//            try {
//                Jws<Claims> claims = Jwts.parser().setSigningKey(jwtUtils.getEncodedKey()).parseClaimsJws(refreshToken);
//                String userEmail = claims.getBody().get("userEmail").toString();
//                TokenUserInfoDto tokenUserInfoDto = userService.userInformationFind(userEmail);
//                TokenDto tokenDto = issueToken(tokenUserInfoDto);
//                userLoginResponseDto = UserLoginResponseDto.builder()
//                        .userInfo(tokenUserInfoDto)
//                        .token(tokenDto)
//                        .build();
//            }
//            catch(Exception e) {
//                throw new TokenException(ErrorCode.EXPIRED_TOKEN, e);
//            }
//
//        }
//        return userLoginResponseDto;
//    }
}
