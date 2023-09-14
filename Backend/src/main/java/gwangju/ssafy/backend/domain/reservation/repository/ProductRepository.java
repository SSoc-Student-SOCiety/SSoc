package gwangju.ssafy.backend.domain.reservation.repository;

import gwangju.ssafy.backend.domain.reservation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 해당 그룹의 대여 물품들(예약 물품) 조회
    List<Product> findAllByGroupId(Long groupId);

    // 카테고리에 따른 해당 그룹의 대여 물품들(예약 물품) 조회
    List<Product> findAllByGroupIdAndCategory(Long groupId, String category);
}
