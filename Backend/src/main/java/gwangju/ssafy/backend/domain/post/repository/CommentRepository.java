package gwangju.ssafy.backend.domain.post.repository;

import gwangju.ssafy.backend.domain.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, QuerydslCommentRepository {
}
