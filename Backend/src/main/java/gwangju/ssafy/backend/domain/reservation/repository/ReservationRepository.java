package gwangju.ssafy.backend.domain.reservation.repository;

import gwangju.ssafy.backend.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    List<Reservation> findByProductId(Long productId);

    @Query("select re from Reservation re where re.product.id = :productId AND to_char(re.realDate, 'yyyy-mm-dd') = :date")
    List<Reservation> findByProductIdAndTime(@Param("productId") Long productId, @Param("date") LocalDate date);
}
