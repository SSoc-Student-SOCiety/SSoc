package gwangju.ssafy.backend.domain.reservation.entity;

import gwangju.ssafy.backend.domain.reservation.entity.enums.ReservationApproveStatus;
import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="reservations")
@Entity
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private User user;

    @Column(name = "cancel_flag")
    private boolean cancelFlag;

    @Column
    private String content;

    @Column(name = "return_status")
    private boolean returnStatus;

    @Column(name = "approve_status")
    @Enumerated(EnumType.STRING)
    private ReservationApproveStatus approveStatus;

    @Column(name = "real_date")
    private LocalDate realDate;

    @Column
    private int time;



}
