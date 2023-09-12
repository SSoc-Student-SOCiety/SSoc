package gwangju.ssafy.backend.domain.post.repository;

import gwangju.ssafy.backend.domain.post.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long>,QuerydslReplyRepository {
}
