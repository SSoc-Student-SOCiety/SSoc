package gwangju.ssafy.backend.global.config;


import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_EMAIL;
import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_ID;
import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_NAME;
import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_NICKNAME;
import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_ROLES;
import static javax.management.timer.Timer.ONE_MINUTE;

import gwangju.ssafy.backend.domain.account.entity.DailyTransactionStatistics;
import gwangju.ssafy.backend.domain.account.entity.GroupAccount;
import gwangju.ssafy.backend.domain.account.entity.MonthlyTransactionStatistics;
import gwangju.ssafy.backend.domain.account.entity.Transaction;
import gwangju.ssafy.backend.domain.account.repository.DailyTransactionStatisticsRepository;
import gwangju.ssafy.backend.domain.account.repository.GroupAccountRepository;
import gwangju.ssafy.backend.domain.account.repository.MonthlyTransactionStatisticsRepository;
import gwangju.ssafy.backend.domain.account.repository.TransactionRepository;
import gwangju.ssafy.backend.domain.group.entity.*;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupCategory;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.*;
import gwangju.ssafy.backend.domain.reservation.entity.Product;
import gwangju.ssafy.backend.domain.reservation.entity.Reservation;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ProductCategory;
import gwangju.ssafy.backend.domain.reservation.entity.enums.ReservationApproveStatus;
import gwangju.ssafy.backend.domain.reservation.repository.ProductRepository;
import gwangju.ssafy.backend.domain.reservation.repository.ReservationRepository;
import gwangju.ssafy.backend.domain.user.entity.User;
import gwangju.ssafy.backend.domain.user.entity.enums.UserRole;
import gwangju.ssafy.backend.domain.user.repository.UserRepository;
import gwangju.ssafy.backend.global.common.entity.vo.Bank;
import gwangju.ssafy.backend.global.component.jwt.JwtIssuer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Configuration
public class TestConfig {

    private final UserRepository userRepository;
    private final SchoolEmailRepository schoolEmailRepository;

    private final SchoolRepository schoolRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupSignupRepository groupSignupRepository;
    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;
    private final GroupAccountRepository groupAccountRepository;

    private final TransactionRepository transactionRepository;

    private final DailyTransactionStatisticsRepository dailyTransactionStatisticsRepository;
    private final MonthlyTransactionStatisticsRepository monthlyTransactionStatisticsRepository;
    private final JwtIssuer jwtIssuer;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init() {

        User testUser = User.builder()
                .userEmail("test@ssafy.com")
                .userPassword(passwordEncoder.encode("12341234"))
                .userNickname("Dokuny")
                .userImageUrl(
                        "https://images.mypetlife.co.kr/content/uploads/2022/12/16162807/IMG_1666-edited-scaled.jpg")
                .userName("도훈")
                .role(UserRole.USER)
                .build();
        userRepository.save(testUser);


        ArrayList<School> schools = new ArrayList<>();
        // 학교 데이터
        School school1 = School.builder()
                .name("인하대")
                .build();
        School school2 = School.builder()
                .name("서울대")
                .build();
        School school3 = School.builder()
                .name("고려대")
                .build();
        School school4 = School.builder()
                .name("연세대")
                .build();
        School school5 = School.builder()
                .name("전남대")
                .build();
        School school7 = School.builder()
                .name("싸피대")
                .build();

        schoolRepository.save(school1);
        schoolRepository.save(school2);
        schoolRepository.save(school3);
        schoolRepository.save(school4);
        schoolRepository.save(school5);
        schoolRepository.save(school7);

        schools.add(school1);
        schools.add(school2);
        schools.add(school3);
        schools.add(school4);
        schools.add(school5);
        schools.add(school7);

        // 학교 이메일
        SchoolEmail email1 = SchoolEmail.builder()
                .school(school1)
                .email("@inha.edu")
                .build();
        SchoolEmail email2 = SchoolEmail.builder()
                .school(school2)
                .email("@snu.ac.kr")
                .build();
        SchoolEmail email3 = SchoolEmail.builder()
                .school(school4)
                .email("@yonsei.ac.kr.")
                .build();
        SchoolEmail email4 = SchoolEmail.builder()
                .school(school7)
                .email("@ssafy.com")
                .build();
        schoolEmailRepository.save(email1);
        schoolEmailRepository.save(email2);
        schoolEmailRepository.save(email3);
        schoolEmailRepository.save(email4);

        Group testGroup = Group.builder()
                .school(school7)
                .aboutUs("19대 국제통상학과 학생회 - 팔레트")
                .category(GroupCategory.COUNCIL)
                .introduce(
                        "커다란 구하지 위하여, 보라. 내려온 풀이 얼마나 인생에 관현악이며, 같이 말이다. 넣는 그러므로 전인 인생에 그들의 칼이다. 영원히 목숨을 얼마나 꾸며 물방아 보라. 청춘이 같이, 지혜는 이것을 천지는 방황하였으며, 위하여 것이다. 청춘을 노래하며 미인을 무한한 역사를 방황하였으며, 철환하였는가? 넣는 얼마나 더운지라 보이는 동력은 인도하겠다는 꽃이 맺어, 그들의 것이다. 무엇을 방황하였으며, 같이, 청춘이 더운지라 이것을 인생을 천지는 때까지 보라. 뜨거운지라, 피고, 이것을 용감하고 끓는다. 황금시대의 사랑의 그들에게 없으면 동력은 구할 고행을 살았으며, 품고 사막이다. 그와 심장의 귀는 피에 그들의 곳으로 가장 부패뿐이다.")
                .thumbnail(
                        "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/6O27GRABBTU3PQOS57U3Q3TFTI.jpg")
                .isActive(true)
                .name("국제통상학과 학생회")
                .build();

        groupRepository.save(testGroup);

        for (int i = 0; i < 5; i++) {
            Group dummyGroup = Group.builder()
                    .school(school7)
                    .aboutUs("test")
                    .thumbnail(
                            "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F996333405A8280FC23")
                    .category(GroupCategory.COUNCIL)
                    .introduce(
                            "커다란 구하지 위하여, 보라. 내려온 풀이 얼마나 인생에 관현악이며, 같이 말이다. 넣는 그러므로 전인 인생에 그들의 칼이다. 영원히 목숨을 얼마나 꾸며 물방아 보라. 청춘이 같이, 지혜는 이것을 천지는 방황하였으며, 위하여 것이다. 청춘을 노래하며 미인을 무한한 역사를 방황하였으며, 철환하였는가? 넣는 얼마나 더운지라 보이는 동력은 인도하겠다는 꽃이 맺어, 그들의 것이다. 무엇을 방황하였으며, 같이, 청춘이 더운지라 이것을 인생을 천지는 때까지 보라. 뜨거운지라, 피고, 이것을 용감하고 끓는다. 황금시대의 사랑의 그들에게 없으면 동력은 구할 고행을 살았으며, 품고 사막이다. 그와 심장의 귀는 피에 그들의 곳으로 가장 부패뿐이다.")
                    .isActive(true)
                    .name("전자공학과 학생회" + i)
                    .build();
            groupRepository.save(dummyGroup);
        }
        for (int i = 0; i < 5; i++) {
            Group dummyGroup = Group.builder()
                    .school(school7)
                    .aboutUs("test")
                    .category(GroupCategory.CLUB)
                    .thumbnail(
                            "https://health.chosun.com/site/data/img_dir/2023/05/31/2023053102582_0.jpg")
                    .isActive(true)
                    .introduce("tsettestsetset")
                    .name("농구 동아리" + i)
                    .build();
            groupRepository.save(dummyGroup);
        }

        GroupMember member = GroupMember.builder()
                .group(testGroup)
                .user(testUser)
                .role(GroupMemberRole.MANAGER)
                .build();
        groupMemberRepository.save(member);


        GroupAccount testAccount = GroupAccount.builder()
                .group(testGroup)
                .bank(new Bank("신한","088"))
                .isActive(true)
                .number("123456789")
                .build();

        groupAccountRepository.save(testAccount);

        LocalDateTime nowTime = LocalDateTime.now();
        List<LocalDateTime> timeList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            timeList.add(nowTime = nowTime.plusDays(1));
        }
        long balance = 0;

        for (int i = 0; i < 50; i++) {
            balance += 10000;
            Transaction t = Transaction.builder()
                    .date(timeList.get(i))
                    .withdrawal(0L)
                    .deposit(10000l)
                    .balance(balance)
                    .branch("신한 광주점")
                    .briefs("입금")
                    .detail("이도훈")
                    .note("회비 납부")
                    .groupAccount(testAccount)
                    .build();

            transactionRepository.save(t);
        }
        for (int i = 50; i < 100; i++) {
            balance -= 10000;
            Transaction t = Transaction.builder()
                    .date(timeList.get(i))
                    .withdrawal(10000l)
                    .deposit(0L)
                    .balance(balance)
                    .branch("신한 광주점")
                    .briefs("출금")
                    .detail("모닝 글로리")
                    .note("물품 구매")
                    .groupAccount(testAccount)
                    .build();

            transactionRepository.save(t);
        }

        LocalDate now = LocalDate.now();

        LocalDate date = LocalDate.of(now.getYear(), now.getMonth(), 1);

        for (int i = 0; i <= 30; i++) {
            DailyTransactionStatistics statistics = DailyTransactionStatistics.builder()
                    .groupAccountId(1L)
                    .withdrawal(5000L)
                    .deposit(5000L)
                    .date(date.minusMonths(1).plusDays(i))
                    .build();

            dailyTransactionStatisticsRepository.save(statistics);
        }

        for (int i = 1; i <= 12; i++) {
            MonthlyTransactionStatistics month = MonthlyTransactionStatistics.builder()
                    .year(2023)
                    .month(i)
                    .groupAccountId(testAccount.getId())
                    .withdrawal(10000000000l)
                    .deposit(23213213123l)
                    .build();

            monthlyTransactionStatisticsRepository.save(month);
        }

        Claims claims = Jwts.claims();

        Date time = new Date();

        claims.put(KEY_ID, 2);
        claims.put(KEY_EMAIL, "test@gmail.com");
        claims.put(KEY_NAME, "tester");
        claims.put(KEY_NICKNAME, "dokuny");
        claims.put(KEY_ROLES, UserRole.USER);
        claims.setIssuedAt(time);
        claims.setExpiration(new Date(time.getTime() + 999999 * ONE_MINUTE));

        byte[] keyBytes = Decoders.BASE64.decode(
                "c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK");
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        String tokeen = jwtIssuer.issueToken(claims,
                key);
        System.out.println(tokeen);

        User user = User.builder()
                .userEmail("dohun@inha.co.kr")
                .userPassword("$2a$12$0ewC6N2PD3kCZNNZjFZI6eIqkABK0uVAGpGmo2dF18SqBFDf6.q5.")
                .userName("도훈")
                .userNickname("도훈 테스트")
                .userImageUrl(null)
                .role(UserRole.USER)
                .build();

        User user2 = User.builder()
                .userEmail("donggeun@yonsei.ac.kr")
                .userPassword("$2a$12$0ewC6N2PD3kCZNNZjFZI6eIqkABK0uVAGpGmo2dF18SqBFDf6.q5.")
                .userName("동근")
                .userNickname("동근 테스트")
                .userImageUrl(null)
                .role(UserRole.USER)
                .build();

        User user3 = User.builder()
                .userEmail("hanju@yonsei.ac.kr")
                .userPassword("$2a$12$0ewC6N2PD3kCZNNZjFZI6eIqkABK0uVAGpGmo2dF18SqBFDf6.q5.")
                .userName("한주")
                .userNickname("한주 테스트")
                .userImageUrl(null)
                .role(UserRole.USER)
                .build();

        User user4 = User.builder()
                .userEmail("surin@inha.co.kr")
                .userPassword("$2a$12$0ewC6N2PD3kCZNNZjFZI6eIqkABK0uVAGpGmo2dF18SqBFDf6.q5.")
                .userName("수린")
                .userNickname("수린 테스트")
                .userImageUrl(null)
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);



        Group group1 = Group.builder()
                .school(school1)
                .aboutUs("test")
                .category(GroupCategory.COUNCIL)
                .isActive(true)
                .name("국제통상학과 학생회")
                .build();
        Group group2 = Group.builder()
                .school(school2)
                .aboutUs("test")
                .category(GroupCategory.COUNCIL)
                .isActive(true)
                .name("전자공학과 학생회")
                .build();
        Group group3 = Group.builder()
                .school(school3)
                .category(GroupCategory.CLUB)
                .aboutUs("test")
                .isActive(true)
                .name("농구 동아리")
                .build();

        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);

        groupMemberRepository.save(member);

        GroupSignup groupSignup2 = GroupSignup.builder()
                .user(user2)
                .group(group1)
                .signupStatus(false)
                .build();

        GroupSignup groupSignup3 = GroupSignup.builder()
                .user(user3)
                .group(group1)
                .signupStatus(false)
                .build();

        GroupSignup groupSignup4 = GroupSignup.builder()
                .user(user4)
                .group(group1)
                .signupStatus(false)
                .build();

        groupSignupRepository.save(groupSignup2);
        groupSignupRepository.save(groupSignup3);
        groupSignupRepository.save(groupSignup4);


        Product product1 = Product.builder()
                .group(group1)
                .category(ProductCategory.CONVENIENCE)
                .name("물품 테스트1 (편의성1)")
                .description("테스트 게즈아1111@@@")
                .content("내용 게즈아1111@@@@")
                .imageUrl("https://imageUrl")
                .build();

        Product product2 = Product.builder()
                .group(group1)
                .category(ProductCategory.CONVENIENCE)
                .name("물품 테스트2 (편의성2)")
                .description("테스트 게즈아2222@@@")
                .content("내용 게즈아2222@@@@")
                .imageUrl("https://imageUrl")
                .build();

        Product product3 = Product.builder()
                .group(group1)
                .category(ProductCategory.PARTY)
                .name("물품 테스트3 (행사용품1)")
                .description("테스트 게즈아3333@@@")
                .content("내용 게즈아3333@@@@")
                .imageUrl("https://imageUrl")
                .build();

        Product product4 = Product.builder()
                .group(group1)
                .category(ProductCategory.BOOK)
                .name("물품 테스트4 (전공서적1)")
                .description("테스트 게즈아44444@@@")
                .content("내용 게즈아4444@@@@")
                .imageUrl("https://imageUrl")
                .build();

        Product product5 = Product.builder()
                .group(group1)
                .category(ProductCategory.BOOK)
                .name("물품 테스트5 (전공서적2)")
                .description("테스트 게즈아5555@@@")
                .content("내용 게즈아5555@@@@")
                .imageUrl("https://imageUrl")
                .build();

        Product product6 = Product.builder()
                .group(group2)
                .category(ProductCategory.PARTY)
                .name("물품 테스트6 (행사용품2)")
                .description("테스트 게즈아6666@@@")
                .content("내용 게즈아6666@@@@")
                .imageUrl("https://imageUrl")
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);


        LocalDate localDateTime = LocalDate.now();
        // 예약부분

        // 예약 취소 안했으면서, 승인된것, 반납 안한것, 시간은 11시에 예약(빌렸음)
        Reservation reservation1 = Reservation.builder()
                .product(product1)
                .user(user3)
                .cancelFlag(false)
                .approveStatus(ReservationApproveStatus.ACCEPT)
                .content("예약 좀 하겠습니다 예약 취소 안했고, 승인되면서 반납 안한거입니다잉, 시간은 11시 예약했습니다잉")
                .returnStatus(false)
                .realDate(localDateTime)
                .time(11)
                .build();

        // 예약 취소(true), 확인 안함, 반납 안함(default), 시간은 16시로 예약
        Reservation reservation2 = Reservation.builder()
                .product(product2)
                .user(user3)
                .cancelFlag(true)
                .approveStatus(ReservationApproveStatus.NOTCONFIRM)
                .content("예약 했는데 취소한 케이스입니다")
                .returnStatus(false)
                .realDate(localDateTime)
                .time(16)
                .build();

        // 예약 취소 안함, 거절당함
        Reservation reservation3 = Reservation.builder()
                .product(product3)
                .user(user3)
                .cancelFlag(false)
                .approveStatus(ReservationApproveStatus.REJECT)
                .content("예약 좀 하겠습니다 거절 당했습니다잉")
                .returnStatus(false)
                .realDate(localDateTime)
                .time(17)
                .build();

        // 예약 취소 안함, 승인됨, 반납 함, 시간 10시
        Reservation reservation4 = Reservation.builder()
                .product(product4)
                .user(user3)
                .cancelFlag(false)
                .approveStatus(ReservationApproveStatus.ACCEPT)
                .content("예약 좀 하겠습니다 취소 안하고, 승인된겁니당, 반납했습니다. 시간 10시에 빌렸습니다.")
                .returnStatus(true)
                .realDate(localDateTime)
                .time(10)
                .build();

        // 예약 취소 안함, 승인 됨, 반납 안함, 시간 12시
        Reservation reservation5 = Reservation.builder()
                .product(product1)
                .user(user4)
                .cancelFlag(false)
                .approveStatus(ReservationApproveStatus.ACCEPT)
                .content("예약 좀 하겠습니다 예약 취소 안하고, 승인되고 반납 안한것 입니다잉 시간은 10시 예약했습니다잉")
                .returnStatus(false)
                .realDate(localDateTime)
                .time(12)
                .build();

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);
        reservationRepository.save(reservation4);
        reservationRepository.save(reservation5);
    }
}