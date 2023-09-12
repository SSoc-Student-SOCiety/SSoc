package gwangju.ssafy.backend.domain.post.service.impl;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.post.dto.CreateReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.DeleteReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.EditReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.ReplyInfo;
import gwangju.ssafy.backend.domain.post.dto.SearchReplyRequest;
import gwangju.ssafy.backend.domain.post.entity.Comment;
import gwangju.ssafy.backend.domain.post.entity.Post;
import gwangju.ssafy.backend.domain.post.entity.Reply;
import gwangju.ssafy.backend.domain.post.repository.CommentRepository;
import gwangju.ssafy.backend.domain.post.repository.PostRepository;
import gwangju.ssafy.backend.domain.post.repository.ReplyRepository;
import gwangju.ssafy.backend.domain.post.service.ReplyService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class ReplyServiceImpl implements ReplyService {

	private final GroupMemberRepository groupMemberRepository;
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final ReplyRepository replyRepository;

	@Override
	public Long createReply(CreateReplyRequest request) {
		Comment comment = commentRepository.findById(request.getCommentId())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 댓글"));

		Post post = postRepository.findById(request.getPostId())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(post.getGroup().getId(),
				request.getUserId())
			.orElseThrow(() -> new RuntimeException("그룹원이 아님"));

		return replyRepository.save(Reply.builder()
			.comment(comment)
			.user(member.getUser())
			.content(request.getContent())
			.isAnonymous(request.getIsAnonymous())
			.build()).getId();
	}

	@Override
	public Long editReply(EditReplyRequest request) {
		Reply reply = getReply(request.getReplyId());

		if (reply.isDeleted()) {
			throw new RuntimeException("삭제된 대댓글");
		}

		validateReplyUser(request.getUserId(), reply);

		reply.edit(request.getContent(), request.getIsAnonymous());

		return reply.getId();
	}

	@Override
	public Long deleteReply(DeleteReplyRequest request) {
		Reply reply = getReply(request.getReplyId());

		validateReplyUser(request.getUserId(), reply);

		reply.delete();

		return reply.getId();
	}

	@Override
	public List<ReplyInfo> searchReply(SearchReplyRequest request) {
		Comment comment = commentRepository.findById(request.getCommentId())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 댓글"));

		if(comment.isDeleted()) throw new RuntimeException("삭제된 댓글");

		Post post = postRepository.findById(request.getPostId())
			.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(post.getGroup().getId(),
				request.getUserId())
			.orElseThrow(() -> new RuntimeException("그룹원이 아님"));

		return replyRepository.findAllInCommentByCond(request.getCommentId(), request.getFilter());
	}

	private Reply getReply(Long replyId) {
		return replyRepository.findById(replyId)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 대댓글"));
	}

	private static void validateReplyUser(Long userId, Reply reply) {
		if (!reply.getUser().getId().equals(userId)) {
			throw new RuntimeException("대댓글 작성자가 아님");
		}
	}

}