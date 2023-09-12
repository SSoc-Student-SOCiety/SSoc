package gwangju.ssafy.backend.domain.board.service.impl;

import gwangju.ssafy.backend.domain.board.dto.CreatePostRequest;
import gwangju.ssafy.backend.domain.board.dto.DeletePostRequest;
import gwangju.ssafy.backend.domain.board.dto.EditPostRequest;
import gwangju.ssafy.backend.domain.board.entity.Post;
import gwangju.ssafy.backend.domain.board.entity.enums.PostCategory;
import gwangju.ssafy.backend.domain.board.repository.PostRepository;
import gwangju.ssafy.backend.domain.board.service.PostService;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class PostServiceImpl implements PostService {

	private final GroupMemberRepository groupMemberRepository;
	private final PostRepository postRepository;

	@Override
	public Long createPost(CreatePostRequest request) {

		GroupMember member = groupMemberRepository.findByGroupIdAndUserId(request.getGroupId(),
				request.getUserId())
			.orElseThrow(() -> new RuntimeException("그룹원 X"));

		// 카테고리가 공지사항인지 확인
		if (request.getCategory().equals(PostCategory.NOTICE)) {
			validateManager(member);
		}

		return postRepository.save(Post.builder()
			.group(member.getGroup())
			.user(member.getUser())
			.title(request.getTitle())
			.content(request.getContent())
			.isAnonymous(request.getIsAnonymous())
			.category(request.getCategory())
			.build()).getId();
	}

	@Override
	public Long editPost(EditPostRequest request) {
		Post post = getPost(request.getPostId(), request.getUserId());

		if (post.isDeleted()) {
			throw new RuntimeException("삭제된 게시글");
		}

		post.edit(request.getTitle(), request.getContent());

		return post.getId();
	}

	@Override
	public Long deletePost(DeletePostRequest request) {
		Post post = getPost(request.getPostId(), request.getUserId());

		post.delete();

		return post.getId();
	}

	private Post getPost(Long postId, Long userId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new RuntimeException("게시글 없음"));

		if (!post.getUser().getId().equals(userId)) {
			throw new RuntimeException("게시글 작성자가 아님");
		}
		return post;
	}

	private void validateManager(GroupMember member) {
		if (!member.getRole().equals(GroupMemberRole.MANAGER)) {
			throw new RuntimeException("매니저가 아님");
		}
	}
}
