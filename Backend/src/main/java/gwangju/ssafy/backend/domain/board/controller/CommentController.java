package gwangju.ssafy.backend.domain.board.controller;

import gwangju.ssafy.backend.domain.board.dto.CreateCommentRequest;
import gwangju.ssafy.backend.domain.board.service.CommentService;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group/post")
@RestController
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/{postId}/comment")
	public ResponseEntity<Message<Long>> createComment(@RequestBody CreateCommentRequest request,
		@PathVariable Long postId) {
		request.setPostId(postId);
		return ResponseEntity.ok().body(Message.success(commentService.createComment(request)));
	}
}
