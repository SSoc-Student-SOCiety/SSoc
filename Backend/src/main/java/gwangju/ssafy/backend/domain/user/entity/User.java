package gwangju.ssafy.backend.domain.user.entity;

import gwangju.ssafy.backend.domain.user.entity.enums.UserRole;
import gwangju.ssafy.backend.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_users", columnNames = {"email"})
}, name = "users")  // 테이블명 user일 시 생성 안됨!(오류)
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
    private String userNickname;    // 유저 닉네임

    @Column(name = "image",length = 2000)
    private String userImageUrl;

//    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private UserRole role;   // 유저 권한

    @Column(name = "deactivate_check")
    private boolean deActivateCheck;    // 회원 탈퇴시 비활성화 여부 체크하기 위한 컬럼

    // 유저 패스워드 세팅 (암호화해서 넘겨줌) (회원정보 수정 - 패스워드)
    public void updatePassword(String userPassword) {
        this.userPassword = userPassword;
    }

    // 유저 닉네임만 세팅 (회원정보 수정 - 닉네임)
    public void updateUserNickname(String userNickName) {
        this.userNickname = userNickName;
    }

    // 유저 프로필 이미지만 세팅 (회원정보 수정 - 닉네임)
    public void updateUserImage(String userImage) {
        this.userImageUrl = userImage;
    }

    // 유저 비활성화 (회원 탈퇴시 사용)
    public void deActivation() {
        this.deActivateCheck = true;
    }

    public void emailDisable(Long userId) {
        this.userEmail = Long.toString(userId);
    }

}
