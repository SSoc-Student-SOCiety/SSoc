package gwangju.ssafy.backend.transaction.repository;

import gwangju.ssafy.backend.transaction.entity.GroupAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupAccountRepository extends JpaRepository<GroupAccount, Long> {

}
