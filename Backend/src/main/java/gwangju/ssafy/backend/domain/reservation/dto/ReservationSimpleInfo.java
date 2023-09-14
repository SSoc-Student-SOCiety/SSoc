package gwangju.ssafy.backend.domain.reservation.dto;

import gwangju.ssafy.backend.domain.reservation.entity.Reservation;
import lombok.*;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReservationSimpleInfo {
    private Long reservationId;
    // 상품 부분
//    private Long productId;
//    private String productCategory;
//    private String productName;
//    private String description;
//    private String content;
//    private String imageUrl;
//
//    // 유저 부분
//    private Long userId;
//    private String userEmail;
//    private String userName;

    // 예약 부분
    private boolean reservationCancelFlag;
    //    private String reservationContent;  // 예약 신청 내용
    private boolean reservationReturnStatus;    // 반납 여부
    private String reservationApproveStatus;
    private String reservationRealDate;
    private int reservationTime;

    public void convert(Reservation reservation) {
        this.reservationId = reservation.getId();
//        this.productId = reservation.getProduct().getId();
//        this.productCategory = reservation.getProduct().getCategory().getValue();
//        this.productName = reservation.getProduct().getName();
//        this.description = reservation.getProduct().getDescription();
//        this.content = reservation.getProduct().getContent();
//        this.imageUrl = reservation.getProduct().getImageUrl();
//        this.userId = reservation.getUser().getId();
//        this.userEmail = reservation.getUser().getUserEmail();
//        this.userName = reservation.getUser().getUserName();
        this.reservationCancelFlag = reservation.isCancelFlag();
//        this.reservationContent = reservation.getContent();
        this.reservationReturnStatus = reservation.isReturnStatus();
        this.reservationApproveStatus = reservation.getApproveStatus().getValue();
        this.reservationRealDate = reservation.getRealDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.reservationTime = reservation.getTime();

    }
}
