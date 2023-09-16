package gwangju.ssafy.backend.domain.reservation.repository;

import gwangju.ssafy.backend.domain.reservation.entity.Reservation;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ReservationApproveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    List<Reservation> findByProductId(Long productId);

    @Query("select re from Reservation re where re.product.id = :productId AND re.realDate = :date")
    List<Reservation> findByProductIdAndTime(@Param("productId") Long productId, @Param("date") LocalDate date);

    //    @Query("select re from Reservation re where re.product.id = (select pr.id from Product pr where pr.group.id = :groupId)")
    @Query("select r from Reservation r inner join Product p on r.product.id = p.id where p.group.id = :groupId " +
            "and ((:approveStatus is null) or (r.approveStatus = :approveStatus)) " +
            "and ((:returnStatus is null) or (r.returnStatus = :returnStatus)) " +
            "order by r.createdAt asc")
    List<Reservation> findByProductIdReservation(
            @Param("groupId") Long groupId,
            @Param("approveStatus") ReservationApproveStatus approveStatus,
            @Param("returnStatus") Optional<Boolean> returnStatus);

    List<Reservation> findByProductId(Long productId);

    List<Reservation> findByUserId(Long userId);

}
