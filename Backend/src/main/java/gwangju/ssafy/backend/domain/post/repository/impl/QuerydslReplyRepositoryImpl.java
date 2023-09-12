package gwangju.ssafy.backend.domain.post.repository.impl;

import static gwangju.ssafy.backend.domain.post.entity.QComment.comment;
import static gwangju.ssafy.backend.domain.post.entity.QReply.reply;
import static gwangju.ssafy.backend.domain.user.entity.QUser.user;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangju.ssafy.backend.domain.post.dto.ReplyInfo;
import gwangju.ssafy.backend.domain.post.dto.cond.SearchReplyCond;
import gwangju.ssafy.backend.domain.post.repository.QuerydslReplyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QuerydslReplyRepositoryImpl implements QuerydslReplyRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<ReplyInfo> findAllInCommentByCond(Long commentId, SearchReplyCond cond) {
		return jpaQueryFactory
			.select(Projections.constructor(ReplyInfo.class,
				reply.id.as("replyId"),
				user.id.as("userId"),
				reply.isAnonymous.when(true)
					.then("익명")
					.otherwise(user.userNickname).as("nickname"),
				reply.createdAt,
				reply.content
			))
			.from(reply)
			.innerJoin(reply.user, user)
			.where(reply.comment.id.eq(commentId),
				reply.isDeleted.isFalse()
				, ltReplyId(cond.getLastReplyId()))
			.orderBy(reply.id.desc())
			.limit(cond.getPageSize())
			.fetch();
	}

	private BooleanExpression ltReplyId(Long replyId) {
		return replyId == null ? null : reply.id.lt(replyId);
	}

}
