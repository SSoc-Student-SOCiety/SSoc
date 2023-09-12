package gwangju.ssafy.backend.domain.board.service;

import gwangju.ssafy.backend.domain.board.dto.CreatePostRequest;

public interface PostService {

	Long createPost(CreatePostRequest request);
}
