package gwangju.ssafy.backend.domain.reservation.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReservationApproveStatus {
    // 승인, 거절, 미확인 상태
    ACCEPT, REJECT, NOTCONFIRM;

    @JsonCreator
    public static ReservationApproveStatus from(String value) {
        for(ReservationApproveStatus reservationApproveStatus: ReservationApproveStatus.values()) {
            if(reservationApproveStatus.name().equals(value)) {
                return reservationApproveStatus;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }

}
