package gwangju.ssafy.backend.domain.reservation.controller;

import gwangju.ssafy.backend.domain.reservation.dto.GetReservationProduct;
import gwangju.ssafy.backend.domain.reservation.dto.GetReservationUser;
import gwangju.ssafy.backend.domain.reservation.dto.ReservationSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ReservationApproveStatus;
import gwangju.ssafy.backend.domain.reservation.service.ReservationService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/detail/{productId}/{date}/{time}/ok")
    public ResponseEntity<Message<ReservationSimpleInfo>> createReservation(
            @PathVariable("productId") Long productId,
            @AuthenticationPrincipal LoginActiveUserDto login,
            @PathVariable("date") String date,
            @PathVariable("time") int time
    ) {
            ReservationSimpleInfo reservationSimpleInfo = reservationService.createReservation(productId, login.getId(), date, time);
            return ResponseEntity.ok().body(Message.success(reservationSimpleInfo));
    }

    // 그룹관리자 부분
    // 예약 내역 전체 조회 및 반납 여부애 따른 조회 및 승인 여부에 따른 조회
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/list/{groupId}")
    public ResponseEntity<Message<List<GetReservationUser>>> reservationList(
            @PathVariable("groupId") Long groupId,
            @RequestParam(name = "approveStatus", required = false) ReservationApproveStatus approveStatus,
            @RequestParam(name = "returnStatus", required = false) Optional<Boolean> returnStatus,
            @AuthenticationPrincipal LoginActiveUserDto login
    ) {
        List<GetReservationUser> getReservationUserList = reservationService.searchAllGroupReservation(groupId,
                login.getId(), approveStatus, returnStatus);
        return ResponseEntity.ok().body(Message.success(getReservationUserList));
    }

    // 해당 예약 승인 여부 값에 따른 처리 (수락 or 거절)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping("{groupId}/{reservationId}")
    public ResponseEntity<Message<GetReservationUser>> setApproveReservation(
            @PathVariable("groupId") Long groupId,
            @AuthenticationPrincipal LoginActiveUserDto login,
            @PathVariable("reservationId") Long reservationId,
            @RequestParam(name = "approveStatus") ReservationApproveStatus approveStatus
    ) {
        GetReservationUser getReservationUser = reservationService.setApproveReservation(groupId, login.getId(),
                reservationId, approveStatus);
        return ResponseEntity.ok().body(Message.success(getReservationUser));
    }

    // 그룹원(로그인 한 본인)이 예약한 내역 조회
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/my/list/{groupId}")
    public ResponseEntity<Message<List<GetReservationProduct>>> searchReservationProduct(
            @PathVariable("groupId") Long groupId,
            @AuthenticationPrincipal LoginActiveUserDto login
    ) {
        List<GetReservationProduct> getReservationProductList = reservationService.searchReservationProduct(groupId, login.getId());
        return ResponseEntity.ok().body(Message.success(getReservationProductList));
    }
}
