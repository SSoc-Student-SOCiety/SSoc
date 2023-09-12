package gwangju.ssafy.backend.domain.post.repository.impl;

import static gwangju.ssafy.backend.domain.post.entity.QComment.comment;
import static gwangju.ssafy.backend.domain.user.entity.QUser.user;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangju.ssafy.backend.domain.post.dto.CommentInfo;
import gwangju.ssafy.backend.domain.post.dto.cond.SearchCommentCond;
import gwangju.ssafy.backend.domain.post.repository.QuerydslCommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QuerydslCommentRepositoryImpl implements QuerydslCommentRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<CommentInfo> findAllInPostByCond(Long postId, SearchCommentCond cond) {
		return jpaQueryFactory
			.select(Projections.constructor(CommentInfo.class,
				comment.id.as("commentId"),
				user.id.as("userId"),
				comment.isAnonymous.when(true)
					.then("익명")
					.otherwise(user.userNickname).as("nickname"),
				comment.createdAt,
				comment.content
			))
			.from(comment)
			.innerJoin(comment.user, user)
			.where(comment.post.id.eq(postId),
				comment.isDeleted.isFalse()
				, ltCommentId(cond.getLastCommentId()))
			.orderBy(comment.id.desc())
			.limit(cond.getPageSize())
			.fetch();
	}

	private BooleanExpression ltCommentId(Long commentId) {
		return commentId == null ? null : comment.id.lt(commentId);
	}

}
