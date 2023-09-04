package gwangju.ssafy.backend.group.repository;

import gwangju.ssafy.backend.group.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
