package gwangju.ssafy.backend.domain.reservation.service;

import gwangju.ssafy.backend.domain.reservation.dto.GetReservationUser;
import gwangju.ssafy.backend.domain.reservation.dto.ReservationSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ReservationApproveStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    // 해당 물품아이디 및 날짜를 통해 해당 물품이 예약되어 있는 것들 조회
    List<ReservationSimpleInfo> searchReservation(Long productId, String date);

    // 해당 날짜 및 시간에 해당 물품 예약하기
    ReservationSimpleInfo createReservation(Long productId, Long userId, String date, int time);

    // 해당 그룹의 예약 내역 조회 (그룹장)
    List<GetReservationUser> searchAllGroupReservation(Long groupId,
                                                       Long loginMemberId,
                                                       ReservationApproveStatus approveStatusStr,
                                                       Optional<Boolean> returnStatus);


    // 해당 그룹의 예약 승인 여부 처리 (수락 또는 거절)
    GetReservationUser setApproveReservation(Long groupId,
                                             Long loginMemberId,
                                             Long reservationId,
                                             ReservationApproveStatus reservationApproveStatus);
}
