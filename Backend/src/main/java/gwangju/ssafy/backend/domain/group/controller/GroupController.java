package gwangju.ssafy.backend.domain.group.controller;

import gwangju.ssafy.backend.domain.group.dto.GroupSearchCond;
import gwangju.ssafy.backend.domain.group.dto.GroupSimpleInfo;
import gwangju.ssafy.backend.global.common.dto.Message;
import gwangju.ssafy.backend.domain.group.dto.EditGroupInfoRequest;
import gwangju.ssafy.backend.domain.group.service.GroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group")
@RestController
public class GroupController {

	private final GroupService groupService;

	@PostMapping("/edit")
	public ResponseEntity<Message> createGroup(@RequestBody EditGroupInfoRequest request) {
		groupService.editGroupInfo(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@PostMapping
	public ResponseEntity<Message> searchGroup(@RequestBody GroupSearchCond cond) {
		return ResponseEntity.ok().body(Message.success(groupService.searchGroup(cond)));
	}
	
}
