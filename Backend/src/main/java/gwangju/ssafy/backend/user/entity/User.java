package gwangju.ssafy.backend.user.entity;

import gwangju.ssafy.backend.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")  // 테이블명 user일 시 생성 안됨!(오류)
public class User extends BaseEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 유저 아이디 (기본키)

    @Column(name = "user_email")
    private String userEmail;   // 유저 이메일

    @Column(name = "user_password")
    private String userPassword;    // 유저 비밀번호

    @Column(name = "user_name")
    private String userName;    // 유저 이름

    @Column(name = "user_nickname")
    private String userNickNmae;    // 유저 닉네임

    @Column(name = "user_nickName")
    private String userNickNmae;    // 유저 닉네임

    @CreatedDate
    @Column(name = "user_create_date", updatable = false)
    private LocalDateTime userCreateDate;

    @LastModifiedBy
    @Column(name = "user_modify_date")
    private LocalDateTime userModifyDate;


}
