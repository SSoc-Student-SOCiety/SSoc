package gwangju.ssafy.backend.group.controller;

import gwangju.ssafy.backend.common.dto.Message;
import gwangju.ssafy.backend.group.dto.EditGroupInfoRequest;
import gwangju.ssafy.backend.group.service.GroupService;
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
}
