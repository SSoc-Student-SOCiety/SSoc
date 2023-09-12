package gwangju.ssafy.backend.domain.post.controller;

import gwangju.ssafy.backend.domain.post.dto.CommentInfo;
import gwangju.ssafy.backend.domain.post.dto.CreateReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.DeleteCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.DeleteReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.EditReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.ReplyInfo;
import gwangju.ssafy.backend.domain.post.dto.SearchCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.SearchReplyRequest;
import gwangju.ssafy.backend.domain.post.service.ReplyService;
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
@RequestMapping("/posts/{postId}/replies")
@RestController
public class ReplyController {

	private final ReplyService replyService;

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Message<Long>> createReply(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody CreateReplyRequest request,
		@PathVariable Long postId
	) {
		request.setUserId(login.getId());
		request.setPostId(postId);
		return ResponseEntity.ok().body(Message.success(replyService.createReply(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PutMapping("/{replyId}")
	public ResponseEntity<Message<Long>> editReply(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody EditReplyRequest request,
		@PathVariable Long replyId
	) {
		request.setUserId(login.getId());
		request.setReplyId(replyId);
		return ResponseEntity.ok().body(Message.success(replyService.editReply(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@DeleteMapping("/{replyId}")
	public ResponseEntity<Message<Long>> deleteReply(
		@AuthenticationPrincipal LoginActiveUserDto login,
		@RequestBody DeleteReplyRequest request,
		@PathVariable Long replyId
	) {
		request.setUserId(login.getId());
		request.setReplyId(replyId);
		return ResponseEntity.ok().body(Message.success(replyService.deleteReply(request)));
	}

	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping
	public ResponseEntity<Message<List<ReplyInfo>>> searchReply(
		@AuthenticationPrincipal LoginActiveUserDto login,
		SearchReplyRequest request,
		@PathVariable Long postId
	) {
		request.setUserId(login.getId());
		request.setPostId(postId);
		return ResponseEntity.ok().body(Message.success(replyService.searchReply(request)));
	}

}
