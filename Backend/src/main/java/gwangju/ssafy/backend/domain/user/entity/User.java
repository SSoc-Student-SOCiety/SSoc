package gwangju.ssafy.backend.domain.user.entity;

import gwangju.ssafy.backend.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")  // 테이블명 user일 시 생성 안됨!(오류)
public class User extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 유저 아이디 (기본키)

    @Column(name = "email")
    private String userEmail;   // 유저 이메일

    @Column(name = "password")
    private String userPassword;    // 유저 비밀번호

    @Column(name = "name")
    private String userName;    // 유저 이름

    @Column(name = "nickname")
    private String userNickName;    // 유저 닉네임

    @Column(name = "authority")
    private String userAuthority;   // 유저 권한

    // 유저 패스워드 암호화
    private void encodePassword(PasswordEncoder passwordEncoder) {
        this.userPassword = passwordEncoder.encode(userPassword);
    }
}
