package gwangju.ssafy.backend.domain.board.controller;

import gwangju.ssafy.backend.domain.board.dto.CreatePostRequest;
import gwangju.ssafy.backend.domain.board.dto.DeletePostRequest;
import gwangju.ssafy.backend.domain.board.dto.EditPostRequest;
import gwangju.ssafy.backend.domain.board.dto.PostInfo;
import gwangju.ssafy.backend.domain.board.dto.SearchPostRequest;
import gwangju.ssafy.backend.domain.board.service.PostService;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
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
@RequestMapping("/group/post")
@RestController
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<Message<Long>> createPost(@RequestBody CreatePostRequest request) {
		return ResponseEntity.ok().body(Message.success(postService.createPost(request)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Message<Long>> editPost(@RequestBody EditPostRequest request,
		@PathVariable("id") Long id) {
		request.setPostId(id);
		return ResponseEntity.ok().body(Message.success(postService.editPost(request)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Message<Long>> deletePost(@RequestBody DeletePostRequest request,
		@PathVariable("id") Long id) {
		request.setPostId(id);
		return ResponseEntity.ok().body(Message.success(postService.deletePost(request)));
	}

	@GetMapping
	public ResponseEntity<Message<List<PostInfo>>> searchPost(@RequestBody SearchPostRequest request) {
		return ResponseEntity.ok().body(Message.success(postService.searchPost(request)));
	}

}
