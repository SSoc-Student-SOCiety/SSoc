package gwangju.ssafy.backend.domain.post.service.impl;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.post.dto.CommentInfo;
import gwangju.ssafy.backend.domain.post.dto.CreateCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.DeleteCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.EditCommentRequest;
import gwangju.ssafy.backend.domain.post.dto.SearchCommentRequest;
import gwangju.ssafy.backend.domain.post.entity.Comment;
import gwangju.ssafy.backend.domain.post.entity.Post;
import gwangju.ssafy.backend.domain.post.exception.PostError;
import gwangju.ssafy.backend.domain.post.exception.PostException;
import gwangju.ssafy.backend.domain.post.repository.CommentRepository;
import gwangju.ssafy.backend.domain.post.repository.PostRepository;
import gwangju.ssafy.backend.domain.post.service.CommentService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentServiceImpl implements CommentService {

	private final GroupMemberRepository groupMemberRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	@Override
	public Long createComment(CreateCommentRequest request) {
		Post post = postRepository.findById(request.getPostId())
			.orElseThrow(() -> new PostException(PostError.NOT_EXISTS_POST));

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(post.getGroup().getId(),
				request.getUserId())
			.orElseThrow(() -> new PostException(PostError.NOT_GROUP_MEMBER));

		return commentRepository.save(Comment.builder()
			.post(post)
			.user(member.getUser())
			.content(request.getContent())
			.isAnonymous(request.getIsAnonymous())
			.build()).getId();
	}

	@Override
	public Long editComment(EditCommentRequest request) {
		Comment comment = getComment(request.getCommentId());

		if (comment.isDeleted()) {
			throw new PostException(PostError.ALREADY_DELETED_COMMENT);
		}

		validateCommentUser(request.getUserId(), comment);

		comment.edit(request.getContent(), request.getIsAnonymous());

		return comment.getId();
	}

	@Override
	public Long deleteComment(DeleteCommentRequest request) {
		Comment comment = getComment(request.getCommentId());

		validateCommentUser(request.getUserId(), comment);

		comment.delete();

		return comment.getId();
	}

	@Override
	public List<CommentInfo> searchComment(SearchCommentRequest request) {
		Post post = postRepository.findById(request.getPostId())
			.orElseThrow(() -> new PostException(PostError.NOT_EXISTS_POST));

		if(post.isDeleted()) throw new PostException(PostError.ALREADY_DELETED_POST);

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(post.getGroup().getId(),
				request.getUserId())
			.orElseThrow(() -> new PostException(PostError.NOT_GROUP_MEMBER));

		return commentRepository.findAllInPostByCond(request.getPostId(), request.getFilter());
	}

	private Comment getComment(Long commentId) {
		return commentRepository.findById(commentId)
			.orElseThrow(() -> new PostException(PostError.NOT_EXISTS_COMMENT));
	}

	private static void validateCommentUser(Long userId, Comment comment) {
		if (!comment.getUser().getId().equals(userId)) {
			throw new PostException(PostError.NOT_COMMENT_OWNER);
		}
	}

}