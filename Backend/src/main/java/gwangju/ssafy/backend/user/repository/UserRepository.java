package gwangju.ssafy.backend.user.repository;

import gwangju.ssafy.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 기본적인 CRUD는 JPA에서 제공

    // 이메일 중복체크를 위해 이메일이 존재하는지 여부 조회 (JPA에서 제공)
    public boolean existsUserByUserEmail(String userEmail);


    // 네이티브 쿼리 이용
    // 해당 이메일이 학교 이메일인지 여부 확인
//    @Query(value = "SELECT EXISTS " +
//                            "(SELECT * " +
//                            "FROM users as u" +
//                            "WHERE u.user_email = ?1" +
//                            "limit 1) as exist")
//    public boolean checkSchoolEmail(String userEmail);


}
