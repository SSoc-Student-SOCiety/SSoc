package gwangju.ssafy.backend.domain.board.service;

import gwangju.ssafy.backend.domain.board.dto.CreatePostRequest;
import gwangju.ssafy.backend.domain.board.dto.DeletePostRequest;
import gwangju.ssafy.backend.domain.board.dto.EditPostRequest;
import gwangju.ssafy.backend.domain.board.dto.PostInfo;
import gwangju.ssafy.backend.domain.board.dto.SearchPostRequest;
import java.util.List;

public interface PostService {

	Long createPost(CreatePostRequest request);
	Long editPost(EditPostRequest request);
	Long deletePost(DeletePostRequest request);

	List<PostInfo> searchPost(SearchPostRequest request);
}