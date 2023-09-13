package gwangju.ssafy.backend.domain.group.controller;

import gwangju.ssafy.backend.domain.group.dto.GetGroupInfoDetailRequest;
import gwangju.ssafy.backend.domain.group.dto.GroupDetailInfo;
import gwangju.ssafy.backend.domain.group.dto.GroupSearchCond;
import gwangju.ssafy.backend.domain.group.dto.GroupSimpleInfo;
import gwangju.ssafy.backend.domain.group.dto.MyGroupSearchCond;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import gwangju.ssafy.backend.domain.group.dto.EditGroupInfoRequest;
import gwangju.ssafy.backend.domain.group.service.GroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/groups")
@RestController
public class GroupController {

	private final GroupService groupService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PutMapping("/{groupId}")
	public ResponseEntity<Message> editGroupInfo(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody EditGroupInfoRequest request,
		@PathVariable Long groupId
	) {
		request.setGroupId(groupId);
		request.setUserId(login.getId());
		groupService.editGroupInfo(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<Message<List<GroupSimpleInfo>>> searchGroup(
		GroupSearchCond cond
	) {
		return ResponseEntity.ok().body(Message.success(groupService.searchGroup(cond)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/my-groups")
	public ResponseEntity<Message> searchMyGroup(
		@AuthenticationPrincipal LoginActiveUserDto login,
		MyGroupSearchCond cond
	) {
		cond.setUserId(login.getId());
		return ResponseEntity.ok().body(Message.success(groupService.searchMyGroup(cond)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/{groupId}")
	public ResponseEntity<Message<GroupDetailInfo>> getGroupInfoDetail(
		GetGroupInfoDetailRequest request,
		@PathVariable Long groupId
	) {
		request.setGroupId(groupId);
		return ResponseEntity.ok().body(Message.success(groupService.getGroupDetail(request)));
	}
}
