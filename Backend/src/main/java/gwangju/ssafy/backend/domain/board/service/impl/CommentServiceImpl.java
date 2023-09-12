package gwangju.ssafy.backend.domain.board.service.impl;

import gwangju.ssafy.backend.domain.board.dto.CreateCommentRequest;
import gwangju.ssafy.backend.domain.board.entity.Post;
import gwangju.ssafy.backend.domain.board.entity.Comment;
import gwangju.ssafy.backend.domain.board.repository.CommentRepository;
import gwangju.ssafy.backend.domain.board.repository.PostRepository;
import gwangju.ssafy.backend.domain.board.service.CommentService;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import jakarta.transaction.Transactional;
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
			.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(post.getGroup().getId(),
				request.getUserId())
			.orElseThrow(() -> new RuntimeException("그룹원이 아님"));

		return commentRepository.save(Comment.builder()
			.post(post)
			.user(member.getUser())
			.content(request.getContent())
			.isAnonymous(request.getIsAnonymous())
			.build()).getId();
	}

}