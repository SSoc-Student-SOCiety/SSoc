package gwangju.ssafy.backend.domain.group.repository;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

	Optional<GroupMember> findByGroupIdAndUserId(Long groupId, Long userId);

}
