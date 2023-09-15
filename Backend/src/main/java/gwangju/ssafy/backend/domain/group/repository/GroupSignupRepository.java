package gwangju.ssafy.backend.domain.group.repository;

import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupSignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupSignupRepository extends JpaRepository<GroupSignup, Long> {
    List<GroupSignup> findAllBySignupStatusAndGroupId(boolean status, Long groupId);

    GroupSignup findByUserId(Long userId);
}
