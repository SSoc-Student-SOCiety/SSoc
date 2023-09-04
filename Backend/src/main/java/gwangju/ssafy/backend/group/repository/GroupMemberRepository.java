package gwangju.ssafy.backend.group.repository;

import gwangju.ssafy.backend.group.entity.GroupMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

	Optional<GroupMember> findByGroupIdAndUserId(Long groupId, Long userId);

}
