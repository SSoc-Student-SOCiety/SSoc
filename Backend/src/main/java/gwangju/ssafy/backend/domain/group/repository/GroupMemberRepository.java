package gwangju.ssafy.backend.domain.group.repository;

import gwangju.ssafy.backend.domain.group.entity.Group;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

	Optional<GroupMember> findByGroupIdAndUserId(Long groupId, Long userId);

	Long countByGroup_Id(Long groupId);

	@Query("select gm from GroupMember gm where gm.group.id = :groupId AND gm.user.id != :userId")
	List<GroupMember> findAllByGroupIdAndUserId(@Param("groupId") Long groupId, @Param("userId") Long userId);

	GroupMember findByUserId(Long userId);
}
