package gwangju.ssafy.backend.domain.account.controller;

import gwangju.ssafy.backend.domain.account.dto.GetMyGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.dto.GroupAccountInfo;
import gwangju.ssafy.backend.domain.account.dto.RegisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.dto.SendAuthCodeRequest;
import gwangju.ssafy.backend.domain.account.dto.UnregisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.service.GroupAccountService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class GroupAccountController {

	private final GroupAccountService groupAccountService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping("/auth")
	public ResponseEntity<Message> sendAuthCode(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody SendAuthCodeRequest request
	) {
		request.setUserId(login.getId());
		groupAccountService.sendAuthCode(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Message> registerGroupAccount(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody RegisterGroupAccountRequest request
	) {
		request.setUserId(login.getId());
		groupAccountService.registerGroupAccount(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/my-group/{groupId}")
	public ResponseEntity<Message<List<GroupAccountInfo>>> getMyGroupAccount(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@PathVariable Long groupId
	) {

		return ResponseEntity.ok()
			.body(Message.success(groupAccountService.getMyGroupAccount(
				GetMyGroupAccountRequest.builder()
					.groupId(groupId)
					.userId(login.getId())
					.build())));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@DeleteMapping("/{accountId}")
	public ResponseEntity<Message> unregisterGroupAccount(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody UnregisterGroupAccountRequest request,
		@PathVariable Long accountId
	) {
		request.setUserId(login.getId());
		request.setAccountId(accountId);
		groupAccountService.unregisterGroupAccount(request);
		return ResponseEntity.ok().body(Message.success());
	}


}
