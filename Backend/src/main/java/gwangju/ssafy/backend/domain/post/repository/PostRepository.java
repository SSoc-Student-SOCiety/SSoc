package gwangju.ssafy.backend.domain.post.repository;

import gwangju.ssafy.backend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> , QuerydslPostRepository{

}
