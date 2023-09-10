package gwangju.ssafy.backend.user.repository;

import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;


@DataJpaTest
@AutoConfigureTestDatabase
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 생성 - 성공")
    void save() {
        // given
        User user = User.builder()
                .userEmail("test1234@yonsei.ac.kr")
                .userPassword("1234")
                .userName("테스트1")
                .userNickname("테스트 닉네임")

                .build();
        // when
        User saveUser = userRepository.save(user);

        // then
        Assertions.assertEquals("test1234@yonsei.ac.kr", saveUser.getUserEmail());
        Assertions.assertEquals("테스트1", saveUser.getUserName());

        System.out.println(saveUser.getUserEmail());
    }

    @Test
    @DisplayName("유저 이메일 중복 체크 - 성공")
    void emailCheck() {
        // given
        User user = User.builder()
                .userEmail("test1234@yonsei.ac.kr")
                .userPassword("1234")
                .userName("테스트1")
                .userNickname("테스트 닉네임")

                .build();
        // when
        User saveUser = userRepository.save(user);

        // then
        System.out.println(userRepository.existsUserByUserEmail("test1234@yonsei.ac.kr"));

        System.out.println(saveUser.getUserEmail());
    }


}
