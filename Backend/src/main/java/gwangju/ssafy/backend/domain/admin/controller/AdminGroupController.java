package gwangju.ssafy.backend.domain.admin.controller;

import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import gwangju.ssafy.backend.domain.group.dto.CreateGroupRequest;
import gwangju.ssafy.backend.domain.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin/groups")
@RestController
public class AdminGroupController {

	private final GroupService groupService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Message> createGroup(
		@RequestBody CreateGroupRequest request
	) {
		groupService.createGroup(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{groupId}/inactive")
	public ResponseEntity<Message> inactivateGroup(
		@PathVariable Long groupId
	) {
		groupService.inactiveGroup(groupId);
		return ResponseEntity.ok().body(Message.success());
	}
}
