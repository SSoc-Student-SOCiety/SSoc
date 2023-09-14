package gwangju.ssafy.backend.domain.reservation.service;

import gwangju.ssafy.backend.domain.reservation.dto.ReservationSimpleInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<ReservationSimpleInfo> searchReservation(Long productId, String date);
}
