package gwangju.ssafy.backend.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class UserRoleSet {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "authority")
    private String authority;   // 유저 권한
}
