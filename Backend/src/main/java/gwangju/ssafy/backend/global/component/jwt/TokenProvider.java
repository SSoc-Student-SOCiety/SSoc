package gwangju.ssafy.backend.global.component.jwt;

import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.service.UserService;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {
//    // HTTP 헤더에 담을 키값
//    private static final String AUTHORITIES_KEY = "auth";
//    private static final String BEARER_TYPE = "bearer";
//
//
////    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 1분
//
//    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 10;            // 1분
////    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일
//
//    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 2;  // 2분
//
//    private final Key key;
//
//    public long getAccessTokenExpireTime() {
//        return ACCESS_TOKEN_EXPIRE_TIME;
//    }
//
//    public long getRefreshTokenExpireTime() {
//        return REFRESH_TOKEN_EXPIRE_TIME;
//    }
//
//    private UserService userService;
//
//    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public TokenDto generateTokenDto(User user) {
//
////        String role = user.getRole().stream()
////                .map(GrantedAuthority::getAuthority)
////                .collect(Collectors.joining(","));
//
//        // 권한 가져오기
////        String authorities = authentication.getAuthorities().stream()
////                .map(GrantedAuthority::getAuthority)
////                .collect(Collectors.joining(","));
//
//        log.info("==================토큰 생성 확인하기=====================");
////        log.info(String.valueOf(authorities));
////        log.info(authentication.toString());
//        long now = (new Date()).getTime();
//
//        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
//
//        // Access Token 생성
//        String accessToken = Jwts.builder()
//                .setSubject(user.getUserEmail())       // payload "sub": "name"
//                .claim(AUTHORITIES_KEY, user.getRole())         // payload "auth": "ROLE_USER"
//                .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)
//                .signWith(key, SignatureAlgorithm.HS512)    // header "algorithm": "HS512"
//                .compact();
//
//        // Refresh Token 생성
//        String refreshToken = Jwts.builder()
//                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//
////        Authentication authentication = getAuthentication(accessToken);
//
//        return TokenDto.builder()
//                .grantType(BEARER_TYPE)
//                .accessToken(accessToken)
//                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    public Authentication getAuthentication(String accessToken) {
//        // 토큰 복호화 작업
//        Claims claims = parseClaims(accessToken);
//
//        log.info("===================토큰에서 claims 내용 가져오기==========================");
//        log.info(claims.toString());
//        log.info(claims.get(AUTHORITIES_KEY).toString());
//        if(claims.get(AUTHORITIES_KEY) == null) {
//            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
//        }
//
//        // 클레임에서 권한 정보 가져오기
////        Collection<? extends GrantedAuthority> authorities =
////                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
////                        .map(SimpleGrantedAuthority::new)
////                        .collect(Collectors.toList());
//
////        userService.existsUserByUserEmail()
//
//
//        JwtAuth
////        UserDetails principal = new User(claims.getSubject(), "", claims.get(AUTHORITIES_KEY).toString());
//
//        return claims;
//    }
//
//    private Claims parseClaims(String accessToken) {
//        try {
//            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
//        } catch (ExpiredJwtException e) {
//            return e.getClaims();
//        }
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//            log.info("validateToken() 메서드 실행 및 claims 값 받아오기 " + " " + claims.toString());
//            return true;
//        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            log.info("잘못된 JWT 서명입니다.");
//        } catch (ExpiredJwtException e) {
//            log.info("만료된 JWT 토큰입니다.");
//        } catch (UnsupportedJwtException e) {
//            log.info("지원되지 않는 JWT 토큰입니다.");
//        } catch (IllegalArgumentException e) {
//            log.info("JWT 토큰이 잘못되었습니다.");
//        }
//        return false;
//    }
}
