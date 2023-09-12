package gwangju.ssafy.backend.domain.board.service;

import gwangju.ssafy.backend.domain.board.dto.CreatePostRequest;
import gwangju.ssafy.backend.domain.board.dto.DeletePostRequest;
import gwangju.ssafy.backend.domain.board.dto.EditPostRequest;

public interface PostService {

	Long createPost(CreatePostRequest request);
	Long editPost(EditPostRequest request);
	Long deletePost(DeletePostRequest request);
}
