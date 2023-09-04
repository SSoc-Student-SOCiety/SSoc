package gwangju.ssafy.backend.domain.group.repository;

import gwangju.ssafy.backend.domain.group.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
