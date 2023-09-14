package gwangju.ssafy.backend.domain.reservation.service;

import gwangju.ssafy.backend.domain.reservation.dto.ReservationSimpleInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    // 해당 물품아이디 및 날짜를 통해 해당 물품이 예약되어 있는 것들 조회
    List<ReservationSimpleInfo> searchReservation(Long productId, String date);

    // 해당 날짜 및 시간에 해당 물품 예약하기
    ReservationSimpleInfo setReservation(Long productId, Long userId, String date, int time);
}
