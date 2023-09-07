package gwangju.ssafy.backend.domain.group.controller;

import gwangju.ssafy.backend.domain.group.dto.GetMemberRoleRequest;
import gwangju.ssafy.backend.domain.group.dto.GetMemberRoleResponse;
import gwangju.ssafy.backend.domain.group.service.GroupMemberService;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group/member")
@RestController
public class GroupMemberController {

	private final GroupMemberService groupMemberService;

	@PostMapping("/role")
	public ResponseEntity<Message<GetMemberRoleResponse>> createGroup(@RequestBody GetMemberRoleRequest request) {
		GetMemberRoleResponse memberRole = groupMemberService.getMemberRole(request);
		return ResponseEntity.ok().body(Message.success(memberRole));
	}
}
