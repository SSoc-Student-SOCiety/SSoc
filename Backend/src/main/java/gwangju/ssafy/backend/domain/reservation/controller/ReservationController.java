package gwangju.ssafy.backend.domain.reservation.controller;

import gwangju.ssafy.backend.domain.reservation.dto.ReservationSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.service.ReservationService;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/detail/{productId}/{date}")
    public ResponseEntity<Message<List<ReservationSimpleInfo>>> searchReservation(
            @PathVariable("productId") Long productId,
            @PathVariable("date") String date) {
        List<ReservationSimpleInfo> reservationSimpleInfoList = reservationService.searchReservation(productId, date);
        // 해당 물품의 예약 내역이 없는 경우 -> 아무 시간이나 예약 가능하다
        if(reservationSimpleInfoList.isEmpty()) {
            return ResponseEntity.ok().body(Message.success(reservationSimpleInfoList, "0", "아무 시간이나 예약 가능합니다."));
        }
        return ResponseEntity.ok().body(Message.success(reservationSimpleInfoList, "1", "예약 된 해당 시간 확인 바랍니다."));
    }
}
