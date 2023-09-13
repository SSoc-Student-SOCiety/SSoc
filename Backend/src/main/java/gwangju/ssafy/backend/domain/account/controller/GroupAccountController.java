package gwangju.ssafy.backend.domain.account.controller;

import gwangju.ssafy.backend.domain.account.dto.RegisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.dto.SendAuthCodeRequest;
import gwangju.ssafy.backend.domain.account.dto.UnregisterGroupAccountRequest;
import gwangju.ssafy.backend.domain.account.service.GroupAccountService;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group/account")
@RestController
public class GroupAccountController {

	private final GroupAccountService groupAccountService;

	@PostMapping("/auth/send")
	public ResponseEntity<Message> sendAuthCode(@RequestBody SendAuthCodeRequest request) {
		groupAccountService.sendAuthCode(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@PostMapping("/register")
	public ResponseEntity<Message> registerGroupAccount(@RequestBody RegisterGroupAccountRequest request) {
		groupAccountService.registerGroupAccount(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@PostMapping("/unregister")
	public ResponseEntity<Message> unregisterGroupAccount(@RequestBody UnregisterGroupAccountRequest request) {
		groupAccountService.unregisterGroupAccount(request);
		return ResponseEntity.ok().body(Message.success());
	}
}
