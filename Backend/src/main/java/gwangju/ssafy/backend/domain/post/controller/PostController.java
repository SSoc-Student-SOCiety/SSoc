package gwangju.ssafy.backend.domain.post.controller;

import gwangju.ssafy.backend.domain.post.dto.CreatePostRequest;
import gwangju.ssafy.backend.domain.post.dto.DeletePostRequest;
import gwangju.ssafy.backend.domain.post.dto.EditPostRequest;
import gwangju.ssafy.backend.domain.post.dto.PostInfo;
import gwangju.ssafy.backend.domain.post.dto.SearchPostRequest;
import gwangju.ssafy.backend.domain.post.service.PostService;
import gwangju.ssafy.backend.domain.user.dto.LoginActiveUserDto;
import gwangju.ssafy.backend.global.common.dto.Message;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

	private final PostService postService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Message<Long>> createPost(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody CreatePostRequest request
	) {
		request.setUserId(login.getId());
		return ResponseEntity.ok().body(Message.success(postService.createPost(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Message<Long>> editPost(@RequestBody EditPostRequest request,
		@PathVariable("id") Long id) {
		request.setPostId(id);
		return ResponseEntity.ok().body(Message.success(postService.editPost(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Message<Long>> deletePost(@RequestBody DeletePostRequest request,
		@PathVariable("id") Long id) {
		request.setPostId(id);
		return ResponseEntity.ok().body(Message.success(postService.deletePost(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<Message<List<PostInfo>>> searchPost(@RequestBody SearchPostRequest request) {
		return ResponseEntity.ok().body(Message.success(postService.searchPost(request)));
	}

}
