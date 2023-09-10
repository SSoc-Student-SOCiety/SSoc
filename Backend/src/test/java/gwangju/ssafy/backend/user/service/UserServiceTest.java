package gwangju.ssafy.backend.user.service;

import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import gwangju.ssafy.backend.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
	UserService userService;

    @DisplayName("유저 이메일 중복검사 - 성공")
    @Test
    void existEmailCheck_success() {
        // given
        User user = User.builder()
                .id(1L)
                .userPassword("1234")
                .userEmail("test1234@yonsei.ac.kr")
                .userNickname("테스트닉네임")
                .build();

        // 내 예상한 값을 넣는다 (무슨 값이 들어오던 무조건 true 반환하겠다)
        given(userRepository.existsUserByUserEmail(user.getUserEmail())).willReturn(true);

        // when
        boolean emailFind = userService.existsUserByUserEmail(user.getUserEmail());

        // then

    }
}
