package gwangju.ssafy.backend.domain.post.service.impl;

import static gwangju.ssafy.backend.domain.post.exception.PostError.ALREADY_DELETED_POST;
import static gwangju.ssafy.backend.domain.post.exception.PostError.NOT_EXISTS_POST;
import static gwangju.ssafy.backend.domain.post.exception.PostError.NOT_GROUP_MANAGER;
import static gwangju.ssafy.backend.domain.post.exception.PostError.NOT_GROUP_MEMBER;
import static gwangju.ssafy.backend.domain.post.exception.PostError.NOT_POST_OWNER;

import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.post.dto.CreatePostRequest;
import gwangju.ssafy.backend.domain.post.dto.DeletePostRequest;
import gwangju.ssafy.backend.domain.post.dto.EditPostRequest;
import gwangju.ssafy.backend.domain.post.dto.PostInfo;
import gwangju.ssafy.backend.domain.post.dto.SearchPostRequest;
import gwangju.ssafy.backend.domain.post.entity.Post;
import gwangju.ssafy.backend.domain.post.entity.enums.PostCategory;
import gwangju.ssafy.backend.domain.post.exception.PostException;
import gwangju.ssafy.backend.domain.post.repository.PostRepository;
import gwangju.ssafy.backend.domain.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostServiceImpl implements PostService {

	private final GroupMemberRepository groupMemberRepository;
	private final PostRepository postRepository;

	@Override
	public Long createPost(CreatePostRequest request) {
		GroupMember member = getGroupMember(request.getGroupId(),
			request.getUserId());

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
			throw new PostException(ALREADY_DELETED_POST);
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

	@Transactional(readOnly = true)
	@Override
	public List<PostInfo> searchPost(SearchPostRequest request) {
		getGroupMember(request.getGroupId(), request.getUserId());

		return postRepository.findAllInGroupByCond(request.getGroupId(),
			request.getFilter());
	}

	private GroupMember getGroupMember(Long groupId, Long userId) {
		return groupMemberRepository.findByGroupIdAndUserId(groupId, userId)
			.orElseThrow(() -> new PostException(NOT_GROUP_MEMBER));
	}

	private Post getPost(Long postId, Long userId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new PostException(NOT_EXISTS_POST));

		if (!post.getUser().getId().equals(userId)) {
			throw new PostException(NOT_POST_OWNER);
		}
		return post;
	}

	private void validateManager(GroupMember member) {
		if (!member.getRole().equals(GroupMemberRole.MANAGER)) {
			throw new PostException(NOT_GROUP_MANAGER);
		}
	}
}
