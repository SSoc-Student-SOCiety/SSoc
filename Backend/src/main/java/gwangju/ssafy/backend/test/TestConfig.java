//package gwangju.ssafy.backend.test;
//
//
//import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_EMAIL;
//import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_ID;
//import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_NAME;
//import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_NICKNAME;
//import static gwangju.ssafy.backend.global.component.jwt.JwtUtils.KEY_ROLES;
//import static javax.management.timer.Timer.ONE_MINUTE;
//
//import gwangju.ssafy.backend.domain.account.entity.DailyTransactionStatistics;
//import gwangju.ssafy.backend.domain.account.entity.GroupAccount;
//import gwangju.ssafy.backend.domain.account.entity.MonthlyTransactionStatistics;
//import gwangju.ssafy.backend.domain.account.entity.Transaction;
//import gwangju.ssafy.backend.domain.account.repository.DailyTransactionStatisticsRepository;
//import gwangju.ssafy.backend.domain.account.repository.GroupAccountRepository;
//import gwangju.ssafy.backend.domain.account.repository.MonthlyTransactionStatisticsRepository;
//import gwangju.ssafy.backend.domain.account.repository.TransactionRepository;
//import gwangju.ssafy.backend.domain.group.entity.Group;
//import gwangju.ssafy.backend.domain.group.entity.GroupMember;
//import gwangju.ssafy.backend.domain.group.entity.School;
//import gwangju.ssafy.backend.domain.group.entity.SchoolEmail;
//import gwangju.ssafy.backend.domain.group.entity.enums.GroupCategory;
//import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
//import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
//import gwangju.ssafy.backend.domain.group.repository.GroupRepository;
//import gwangju.ssafy.backend.domain.group.repository.SchoolEmailRepository;
//import gwangju.ssafy.backend.domain.group.repository.SchoolRepository;
//import gwangju.ssafy.backend.domain.user.entity.User;
//import gwangju.ssafy.backend.domain.user.entity.enums.UserRole;
//import gwangju.ssafy.backend.domain.user.repository.UserRepository;
//import gwangju.ssafy.backend.global.common.entity.vo.Bank;
//import gwangju.ssafy.backend.global.component.jwt.JwtIssuer;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.crypto.SecretKey;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@RequiredArgsConstructor
//@Configuration
//public class TestConfig {
//
//	private final UserRepository userRepository;
//	private final SchoolEmailRepository schoolEmailRepository;
//
//	private final SchoolRepository schoolRepository;
//	private final GroupRepository groupRepository;
//	private final GroupMemberRepository groupMemberRepository;
//	private final GroupAccountRepository groupAccountRepository;
//
//	private final TransactionRepository transactionRepository;
//
//	private final DailyTransactionStatisticsRepository dailyTransactionStatisticsRepository;
//	private final MonthlyTransactionStatisticsRepository monthlyTransactionStatisticsRepository;
//	private final JwtIssuer jwtIssuer;
//	private final PasswordEncoder passwordEncoder;
//
//
//	@PostConstruct
//	public void init() {
//
//		/**
//		 * 유저 데이터
//		 */
//		User admin = User.builder()
//			.userEmail("admin@ssafy.com")
//			.userPassword(passwordEncoder.encode("1234"))
//			.userNickname("admin")
//			.userImageUrl(
//				"https://scontent-ssn1-1.xx.fbcdn.net/v/t1.6435-9/30571850_1741776345876747_1413087666798329856_n.png?stp=dst-png&_nc_cat=111&ccb=1-7&_nc_sid=174925&_nc_ohc=E2UbsrKuy6oAX-98nJE&_nc_ht=scontent-ssn1-1.xx&oh=00_AfCjk_ra7QokUNzKOeu28np0V9XDZ4Nr9Xrcw1poe7QYxQ&oe=652CEF47")
//			.userName("어드민")
//			.role(UserRole.ADMIN)
//			.build();
//		userRepository.save(admin);
//
//		ArrayList<User> users = new ArrayList<>();
//		User user1 = User.builder()
//			.userEmail("test1@ssafy.com")
//			.userPassword(passwordEncoder.encode("1234"))
//			.userNickname("쏠")
//			.userImageUrl(
//				"https://www.shinhangroup.com/kr/asset/images/pr_center/character/sh_character_01.png")
//			.userName("쏠")
//			.role(UserRole.USER)
//			.build();
//		userRepository.save(user1);
//		User user2 = User.builder()
//			.userEmail("test2@ssafy.com")
//			.userPassword(passwordEncoder.encode("1234"))
//			.userNickname("몰리")
//			.userImageUrl(
//				"https://www.shinhangroup.com/kr/asset/images/pr_center/character/sh_character_02.png")
//			.userName("몰리")
//			.role(UserRole.USER)
//			.build();
//		userRepository.save(user2);
//		users.add(user2);
//		User user3 = User.builder()
//			.userEmail("test3@ssafy.com")
//			.userPassword(passwordEncoder.encode("1234"))
//			.userNickname("리노")
//			.userImageUrl(
//				"https://www.shinhangroup.com/kr/asset/images/pr_center/character/sh_character_03.png")
//			.userName("리노")
//			.role(UserRole.USER)
//			.build();
//		userRepository.save(user3);
//		users.add(user3);
//
//		User user4 = User.builder()
//			.userEmail("test4@ssafy.com")
//			.userPassword(passwordEncoder.encode("1234"))
//			.userNickname("슈")
//			.userImageUrl(
//				"https://www.shinhangroup.com/kr/asset/images/pr_center/character/sh_character_04.png")
//			.userName("슈")
//			.role(UserRole.USER)
//			.build();
//		userRepository.save(user4);
//		users.add(user4);
//
//		// 테스트용 학교 & 이메일
//		School school = School.builder()
//			.name("신한대학교")
//			.build();
//		schoolRepository.save(school);
//
//		SchoolEmail.builder()
//			.email("@ssafy.com")
//			.school(school)
//			.build();
//
//		// 테스트용 그룹
//		Group testGroup = Group.builder()
//			.school(school)
//			.aboutUs("1대 글로벌 금융학과 학생회 - SOL")
//			.category(GroupCategory.COUNCIL)
//			.introduce(
//				"신한대학교 글로벌 금융학과 학생회, SOL 입니다.")
//			.thumbnail(
//				"https://blog.kakaocdn.net/dn/oFmS1/btq6v9DrkUn/KuhQRvK2GDXJyVtDQX5YbK/img.jpg")
//			.isActive(true)
//			.name("글로벌 금융학과 학생회")
//			.build();
//
//		groupRepository.save(testGroup);
//
//		GroupMember member = GroupMember.builder()
//			.group(testGroup)
//			.user(user1)
//			.role(GroupMemberRole.MANAGER)
//			.build();
//		groupMemberRepository.save(member);
//
//		for (User user : users) {
//			GroupMember dummy = GroupMember.builder()
//				.group(testGroup)
//				.user(user)
//				.role(GroupMemberRole.MEMBER)
//				.build();
//			groupMemberRepository.save(dummy);
//		}
//
//		GroupAccount testAccount = GroupAccount.builder()
//			.group(testGroup)
//			.bank(new Bank("신한", "088"))
//			.isActive(true)
//			.number("123456789")
//			.build();
//
//		groupAccountRepository.save(testAccount);
//
//		LocalDateTime nowTime = LocalDateTime.now();
//		List<LocalDateTime> timeList = new ArrayList<>();
//		for (int i = 0; i < 100; i++) {
//			timeList.add(nowTime = nowTime.plusDays(1).minusHours(1));
//		}
//
//		long balance = 0;
//
//		for (int i = 0; i < 50; i++) {
//			balance += 10000;
//			Transaction t = Transaction.builder()
//				.date(timeList.get(i))
//				.withdrawal(0L)
//				.deposit(10000l)
//				.balance(balance)
//				.branch("신한 광주점")
//				.briefs("입금")
//				.detail("이도훈")
//				.note("회비 납부")
//				.groupAccount(testAccount)
//				.build();
//
//			transactionRepository.save(t);
//		}
//		for (int i = 50; i < 100; i++) {
//			balance -= 10000;
//			Transaction t = Transaction.builder()
//				.date(timeList.get(i))
//				.withdrawal(10000l)
//				.deposit(0L)
//				.balance(balance)
//				.branch("신한 광주점")
//				.briefs("출금")
//				.detail("모닝 글로리")
//				.note("물품 구매")
//				.groupAccount(testAccount)
//				.build();
//
//			transactionRepository.save(t);
//		}
//
//		LocalDate now = LocalDate.now();
//
//		LocalDate date = LocalDate.of(now.getYear(), now.getMonth(), 1);
//
//		for (int i = 0; i <= 30; i++) {
//			DailyTransactionStatistics statistics = DailyTransactionStatistics.builder()
//				.groupAccountId(1L)
//				.withdrawal(5000L)
//				.deposit(5000L)
//				.date(date.minusMonths(1).plusDays(i))
//				.build();
//
//			dailyTransactionStatisticsRepository.save(statistics);
//		}
//
//		for (int i = 1; i <= 12; i++) {
//			MonthlyTransactionStatistics month = MonthlyTransactionStatistics.builder()
//				.year(2023)
//				.month(i)
//				.groupAccountId(testAccount.getId())
//				.withdrawal(10000000000l)
//				.deposit(23213213123l)
//				.build();
//
//			monthlyTransactionStatisticsRepository.save(month);
//		}
//
//		Claims claims = Jwts.claims();
//
//		Date time = new Date();
//
//		claims.put(KEY_ID, 2);
//		claims.put(KEY_EMAIL, "test@gmail.com");
//		claims.put(KEY_NAME, "tester");
//		claims.put(KEY_NICKNAME, "dokuny");
//		claims.put(KEY_ROLES, UserRole.USER);
//		claims.setIssuedAt(time);
//		claims.setExpiration(new Date(time.getTime() + 999999 * ONE_MINUTE));
//
//		byte[] keyBytes = Decoders.BASE64.decode(
//			"c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK");
//		SecretKey key = Keys.hmacShaKeyFor(keyBytes);
//		String tokeen = jwtIssuer.issueToken(claims,
//			key);
//		System.out.println(tokeen);
//
//	}
//}