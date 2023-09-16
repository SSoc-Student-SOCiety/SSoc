package gwangju.ssafy.backend.domain.reservation.dto;

import gwangju.ssafy.backend.domain.reservation.entity.Reservation;
import lombok.*;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetReservationProduct {
    private String productName;
    // 예약 부분
    private String reservationCreatedAt;
    private boolean reservationCancelFlag;  // 예약 취소 여부
    private boolean reservationReturnStatus;    // 반납 여부
    private String reservationApproveStatus;    // 예약 승인 여부
    private String reservationRealDate;     // 예약 날짜
    private int reservationTime;    // 예약 시간

    public void convert(Reservation reservation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        this.productName = reservation.getProduct().getName();
        this.reservationCreatedAt = reservation.getCreatedAt().format(formatter);
        this.reservationCancelFlag = reservation.isCancelFlag();
        this.reservationReturnStatus = reservation.isReturnStatus();
        this.reservationApproveStatus = reservation.getApproveStatus().getValue();
        this.reservationRealDate = reservation.getRealDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.reservationTime = reservation.getTime();
    }
}
