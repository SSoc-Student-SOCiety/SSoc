package gwangju.ssafy.backend.global.component.jwt.security;

import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private LoginActiveUserDto principal;

    private Object credentials;

    public JwtAuthenticationToken(LoginActiveUserDto principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public LoginActiveUserDto getPrincipal() {
        return this.principal;
    }
}