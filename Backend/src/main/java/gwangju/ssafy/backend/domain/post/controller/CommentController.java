package gwangju.ssafy.backend.domain.post.controller;

import gwangju.ssafy.backend.domain.post.dto.CommentInfo;
import gwangju.ssafy.backend.domain.post.dto.CreateCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.DeleteCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.EditCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.SearchCommentRequest;
import gwangju.ssafy.backend.domain.post.service.CommentService;
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
@RequestMapping("/posts/{postId}")
@RestController
public class CommentController {

	private final CommentService commentService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping("/comments")
	public ResponseEntity<Message<Long>> createComment(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody CreateCommentRequest request,
		@PathVariable Long postId
	) {
		request.setUserId(login.getId());
		request.setPostId(postId);
		return ResponseEntity.ok().body(Message.success(commentService.createComment(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PutMapping("/comments/{commentId}")
	public ResponseEntity<Message<Long>> editComment(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody EditCommentRequest request,
		@PathVariable Long commentId
	) {
		request.setUserId(login.getId());
		request.setCommentId(commentId);
		return ResponseEntity.ok().body(Message.success(commentService.editComment(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<Message<Long>> deleteComment(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody DeleteCommentRequest request,
		@PathVariable Long commentId
	) {
		request.setUserId(login.getId());
		request.setCommentId(commentId);
		return ResponseEntity.ok().body(Message.success(commentService.deleteComment(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/comments")
	public ResponseEntity<Message<List<CommentInfo>>> searchComment(
		@AuthenticationPrincipal LoginActiveUserDto login,
		SearchCommentRequest request,
		@PathVariable Long postId
	) {
		request.setUserId(login.getId());
		request.setPostId(postId);
		return ResponseEntity.ok().body(Message.success(commentService.searchComment(request)));
	}
}
