package gwangju.ssafy.backend.domain.post.service;

import gwangju.ssafy.backend.domain.post.dto.CreatePostRequest;
import gwangju.ssafy.backend.domain.post.dto.DeletePostRequest;
import gwangju.ssafy.backend.domain.post.dto.EditPostRequest;
import gwangju.ssafy.backend.domain.post.dto.PostInfo;
import gwangju.ssafy.backend.domain.post.dto.SearchPostRequest;
import java.util.List;

public interface PostService {

	Long createPost(CreatePostRequest request);
	Long editPost(EditPostRequest request);
	Long deletePost(DeletePostRequest request);

	List<PostInfo> searchPost(SearchPostRequest request);
}
