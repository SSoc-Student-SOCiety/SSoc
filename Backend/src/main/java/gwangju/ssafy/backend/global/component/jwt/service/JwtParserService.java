package gwangju.ssafy.backend.global.component.jwt.service;

import gwangju.ssafy.backend.global.component.jwt.dto.TokenDto;
import gwangju.ssafy.backend.global.component.jwt.dto.TokenUserInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;

public interface JwtParserService {
    TokenDto issueToken(@NonNull TokenUserInfoDto info);

    TokenDto refreshToken(@NonNull String refreshToken);

    void validateRefreshTokens(String refreshToken, String savedToken);

}
