package gwangju.ssafy.backend.domain.board.repository;

import gwangju.ssafy.backend.domain.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
