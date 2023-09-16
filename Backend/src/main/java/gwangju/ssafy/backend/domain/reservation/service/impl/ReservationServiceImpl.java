package gwangju.ssafy.backend.domain.reservation.service.impl;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.exception.GroupError;
import gwangju.ssafy.backend.domain.group.exception.GroupException;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.reservation.dto.GetReservationProduct;
import gwangju.ssafy.backend.domain.reservation.dto.GetReservationUser;
import gwangju.ssafy.backend.domain.reservation.dto.ReservationSimpleInfo;
import gwangju.ssafy.backend.domain.reservation.entity.Product;
import gwangju.ssafy.backend.domain.reservation.entity.Reservation;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ReservationApproveStatus;
import gwangju.ssafy.backend.domain.reservation.exception.ReservationException;
import gwangju.ssafy.backend.domain.reservation.repository.ProductRepository;
import gwangju.ssafy.backend.domain.reservation.repository.ReservationRepository;
import gwangju.ssafy.backend.domain.reservation.service.ReservationService;
import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.exception.UserError;
import gwangju.ssafy.backend.domain.user.exception.UserException;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import gwangju.ssafy.backend.global.component.alarm.AlarmService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static gwangju.ssafy.backend.domain.group.exception.GroupError.NOT_GROUP_MEMBER;
import static gwangju.ssafy.backend.domain.reservation.exception.ReservationError.NOT_EXIST_PRODUCT;
import static gwangju.ssafy.backend.domain.reservation.exception.ReservationError.NOT_EXIST_RESERVATION;
import static gwangju.ssafy.backend.domain.user.exception.UserError.NOT_EXIST_USER;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {
    // 대여물품 id를 통해 예약 내역 조회 -> 그룹 멤버 입장
    private final ReservationRepository reservationRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final GroupMemberRepository groupMemberRepository;

    private final AlarmService alarmService;

    // 대여 물품에 대한 예약 내역 확인
    @Override
    public List<ReservationSimpleInfo> searchReservation(Long productId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);

        List<Reservation> reservationList = reservationRepository.findByProductIdAndTime(productId, dateTime);
        List<ReservationSimpleInfo> reservationSimpleInfoList = new ArrayList<>();


        for (Reservation reservation : reservationList) {
            // 예약 내역이 있으면서
            if (reservation != null) {
                // 예약 취소를 안했으면서
                if (!reservation.isCancelFlag()) {
                    // 예약 승인인 것인 경우
                    if (reservation.getApproveStatus().getValue().equals("ACCEPT")) {
                        // 반납 안한 것인 경우
                        if (!reservation.isReturnStatus()) {
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

    @Override
    public ReservationSimpleInfo createReservation(Long productId, Long userId, String date, int time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        // 해당 품목 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ReservationException(NOT_EXIST_PRODUCT));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_EXIST_USER));

        Reservation reservation = Reservation.builder()
                .product(product)
                .user(user)
                .cancelFlag(false)  // 취소 여부 false로 default
                .returnStatus(false)    // 반납 여부 false로 default
                .approveStatus(ReservationApproveStatus.NOTCONFIRM) // 예약 승인 여부 "NOTCONFIRM"으로 초기화
                .realDate(dateTime)
                .time(time)
                .build();

        reservationRepository.save(reservation);
        ReservationSimpleInfo reservationSimpleInfo = new ReservationSimpleInfo();
        reservationSimpleInfo.convert(reservation);
        return reservationSimpleInfo;
    }

    // 해당 그룹의 예약 내역 전체 조회
    @Override
    public List<GetReservationUser> searchAllGroupReservation(Long groupId,
                                                              Long loginMemberId,
                                                              ReservationApproveStatus approveStatus,
                                                              Optional<Boolean> returnStatus) {
        GroupMember groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, loginMemberId)
                .orElseThrow(() -> new GroupException(NOT_GROUP_MEMBER));

        if (groupMember.getRole() != GroupMemberRole.MANAGER) {
            throw new GroupException(NOT_GROUP_MEMBER);
        }


        List<Reservation> reservationList = reservationRepository.findByProductIdReservation(groupId, approveStatus, returnStatus);
        List<GetReservationUser> getReservationUserList = new ArrayList<>();

        for (Reservation reservation : reservationList) {
            GetReservationUser getReservationUser = new GetReservationUser();
            getReservationUser.convert(reservation);
            getReservationUserList.add(getReservationUser);
        }

        return getReservationUserList;
    }

    @Override
    public GetReservationUser setApproveReservation(Long groupId,
                                                    Long loginMemberId,
                                                    Long reservationId,
                                                    ReservationApproveStatus reservationApproveStatus) {
        GroupMember groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, loginMemberId)
                .orElseThrow(() -> new GroupException(NOT_GROUP_MEMBER));

        if (groupMember.getRole() != GroupMemberRole.MANAGER) {
            throw new GroupException(NOT_GROUP_MEMBER);
        }

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException(NOT_EXIST_RESERVATION));

        StringBuilder sb = new StringBuilder();

        sb.append("예약이 ");
        // 예약 승인된 경우
        if(reservationApproveStatus.getValue().equals("ACCEPT")) {
            sb.append("승인되었습니다.").append("<br><br>");
            sb.append("예약된 물품은 ").append(reservation.getProduct().getName()).append("입니다.");
            alarmService.sendMailAlarm("대여물품 예약이 승인되었습니다.", sb.toString(), reservation.getUser().getUserEmail(), "alarm");
        }
        // 예약 거절된 경우
        else if(reservationApproveStatus.getValue().equals("REJECT")){
            sb.append("거절되었습니다.").append("<br><br>");
            alarmService.sendMailAlarm("대여 물품 예약이 거절되었습니다.", sb.toString(), reservation.getUser().getUserEmail(), "alarm");
        }

        reservation.setApproveStatus(reservationApproveStatus); // 해당 예약 승인 여부 값 받아서 update 처리 -> 승인(ACCEPT), 거절(RRJECT)

        GetReservationUser getReservationUser = new GetReservationUser();
        getReservationUser.convert(reservation);
        return getReservationUser;
    }

    // 그룹원(로그인 한 본인)이 예약한 내역 조회
    @Override
    public List<GetReservationProduct> searchReservationProduct(Long groupId, Long loginMemberId) {
        GroupMember groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, loginMemberId)
                .orElseThrow(() -> new GroupException(NOT_GROUP_MEMBER));

        List<Reservation> reservationList = reservationRepository.findByUserId(loginMemberId);
        List<GetReservationProduct> getReservationProductList = new ArrayList<>();

        for(Reservation reservation : reservationList) {
            GetReservationProduct getReservationProduct = new GetReservationProduct();
            getReservationProduct.convert(reservation);
            getReservationProductList.add(getReservationProduct);
        }

        return getReservationProductList;
    }
}
