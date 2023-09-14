package gwangju.ssafy.backend.domain.reservation.service.impl;

import gwangju.ssafy.backend.domain.reservation.dto.ReservationSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.Reservation;
import gwangju.ssafy.backend.domain.reservation.repository.ReservationRepository;
import gwangju.ssafy.backend.domain.reservation.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {
    // 대여물품 id를 통해 예약 내역 조회 -> 그룹 멤버 입장
    private final ReservationRepository reservationRepository;

    // 대여 물품에 대한 예약 내역 확인
    @Override
    public List<ReservationSimpleInfo> searchReservation(Long productId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        List<Reservation> reservationList = reservationRepository.findByProductIdAndTime(productId, dateTime);
        List<ReservationSimpleInfo> reservationSimpleInfoList = new ArrayList<>();


        for(Reservation reservation: reservationList) {
            // 예약 내역이 있으면서
            if(reservation != null) {
                // 예약 취소를 안했으면서
                if(!reservation.isCancelFlag()) {
                    // 예약 승인인 것인 경우
                    if(reservation.getApproveStatus().getValue().equals("ACCEPT")) {
                        // 반납 안한 것인 경우
                        if(!reservation.isReturnStatus()) {
                            ReservationSimpleInfo reservationSimpleInfo = new ReservationSimpleInfo();
                            // 해당 대여 물품의 예약 정보 반환할 수 있게끔
                            reservationSimpleInfo.convert(reservation);
                            reservationSimpleInfoList.add(reservationSimpleInfo);
                        }
                    }
                }
            }
        }

        // 예약 정보 내역들 반환
        return reservationSimpleInfoList;
    }
}
