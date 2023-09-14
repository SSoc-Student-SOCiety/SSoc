package gwangju.ssafy.backend.domain.reservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReservationSimpleInfo {
    private Long reservationId;
    // 상품 부분
    private Long productId;
    private String productCategory;
    private String productName;
    private String description;
    private String content;
    private String imageUrl;

    // 유저 부분
    private Long userId;
    private String userEmail;
    private String userName;

    // 예약 부분
    private boolean reservationCancelFlag;
    private String reservationContent;  // 예약 신청 내용
    private boolean reservationReturnStatus;    // 반납 여부
    private String reservationApproveStatus;
    private LocalDateTime reservationRealDate;
    private int reservationTime;
}
