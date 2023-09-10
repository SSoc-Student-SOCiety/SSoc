package gwangju.ssafy.backend.global.component.jwt.service;

import gwangju.ssafy.backend.domain.user.dto.UserDto;
import gwangju.ssafy.backend.domain.user.dto.UserLoginResponseDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import lombok.NonNull;

public interface JwtService {

    // 토큰 발급
    public TokenDto issueToken(@NonNull TokenUserInfoDto info);

    // 토큰 재발급
    public TokenDto refreshToken(@NonNull String refreshToken);

    // access 토큰 파싱
    public TokenUserInfoDto parseAccessToken(@NonNull String accessToken);


}
