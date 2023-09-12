package gwangju.ssafy.backend.domain.post.service;

import gwangju.ssafy.backend.domain.post.dto.CreateReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.DeleteReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.EditReplyRequest;
import gwangju.ssafy.backend.domain.post.dto.ReplyInfo;
import gwangju.ssafy.backend.domain.post.dto.SearchReplyRequest;
import java.util.List;

public interface ReplyService {

	Long createReply(CreateReplyRequest request);
	Long editReply(EditReplyRequest request);
	Long deleteReply(DeleteReplyRequest request);

	List<ReplyInfo> searchReply(SearchReplyRequest request);

}
