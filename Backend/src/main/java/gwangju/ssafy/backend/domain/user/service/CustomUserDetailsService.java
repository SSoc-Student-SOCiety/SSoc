package gwangju.ssafy.backend.domain.user.service;

import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("login");
        log.info(username);
        return userRepository.findByUserEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " ->를 가진 해당 사용자를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(User user) {
        return User.builder()
                .userEmail(user.getUserEmail())
                .userPassword(passwordEncoder.encode(user.getUserPassword()))
                .role(user.getRole())
                .build();
    }

}
