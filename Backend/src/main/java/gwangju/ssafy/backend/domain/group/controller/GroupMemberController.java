package gwangju.ssafy.backend.domain.group.controller;

import gwangju.ssafy.backend.domain.group.dto.GetGroupMemberInfo;
import gwangju.ssafy.backend.domain.group.dto.GetMemberRoleRequest;
import gwangju.ssafy.backend.domain.group.dto.GetMemberRoleResponse;
import gwangju.ssafy.backend.domain.group.service.GroupMemberService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/groups/{groupId}")
@RestController
public class GroupMemberController {

	private final GroupMemberService groupMemberService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/my-role")
	public ResponseEntity<Message<GetMemberRoleResponse>> getMyRole(
		@AuthenticationPrincipal LoginActiveUserDto login,
		GetMemberRoleRequest request,
		@PathVariable Long groupId
	) {
		request.setGroupId(groupId);
		request.setUserId(login.getId());
		return ResponseEntity.ok().body(Message.success(groupMemberService.getMemberRole(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/user/list")
	public ResponseEntity<Message<List<GetGroupMemberInfo>>> getGroupMember(
			@PathVariable("groupId") Long groupId,
			@AuthenticationPrincipal LoginActiveUserDto login) {
		log.info(login.getUserName());
		List<GetGroupMemberInfo> groupMemberInfoList = groupMemberService.getMemberList(login.getId(), groupId);
		return ResponseEntity.ok().body(Message.success(groupMemberInfoList));
	}

}
