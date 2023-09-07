package gwangju.ssafy.backend.domain.group.repository;

import gwangju.ssafy.backend.domain.group.entity.Group;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>,QueryDslGroupRepository {

	Optional<Group> findByIdAndIsActiveIsTrue(Long id);


}
