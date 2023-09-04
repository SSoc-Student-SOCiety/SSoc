package gwangju.ssafy.backend.group.repository;

import gwangju.ssafy.backend.group.entity.Group;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	Optional<Group> findByIdAndIsActiveIsTrue(Long id);


}