package gwangju.ssafy.backend.global.component.jwt.security;

import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private LoginActiveUserDto pricipal;

    private Object credentials;

    public JwtAuthenticationToken(LoginActiveUserDto pricipal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.pricipal = pricipal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.getPricipal();
    }
}
