package gwangju.ssafy.backend.domain.transaction.repository;

import gwangju.ssafy.backend.domain.transaction.entity.GroupAccount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupAccountRepository extends JpaRepository<GroupAccount, Long> {

	List<GroupAccount> findAllByIsActiveIsTrue();

}
