package gwangju.ssafy.backend.admin.controller;

import gwangju.ssafy.backend.common.dto.Message;
import gwangju.ssafy.backend.group.dto.CreateGroupRequest;
import gwangju.ssafy.backend.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminGroupController {

	private final GroupService groupService;

	@PostMapping("/groups")
	public ResponseEntity<Message> createGroup(@RequestBody CreateGroupRequest request) {
		groupService.createGroup(request);
		return ResponseEntity.ok().body(Message.success());
	}

	@GetMapping("/groups/{id}/inactive")
	public ResponseEntity<Message> inactivateGroup(@PathVariable Long id) {
		groupService.inactiveGroup(id);
		return ResponseEntity.ok().body(Message.success());
	}
}
