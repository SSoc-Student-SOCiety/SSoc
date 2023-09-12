package gwangju.ssafy.backend.domain.board.controller;

import gwangju.ssafy.backend.domain.board.dto.CreatePostRequest;
import gwangju.ssafy.backend.domain.board.service.PostService;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group/post")
@RestController
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<Message> createPost(@RequestBody CreatePostRequest request) {
		return ResponseEntity.ok().body(Message.success(postService.createPost(request)));
	}
	
}
