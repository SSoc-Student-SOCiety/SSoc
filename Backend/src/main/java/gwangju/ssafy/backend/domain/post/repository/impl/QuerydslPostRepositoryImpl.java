package gwangju.ssafy.backend.domain.post.repository.impl;

import static com.querydsl.core.types.ExpressionUtils.count;
import static gwangju.ssafy.backend.domain.post.entity.QComment.comment;
import static gwangju.ssafy.backend.domain.post.entity.QPost.post;
import static gwangju.ssafy.backend.domain.user.entity.QUser.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangju.ssafy.backend.domain.post.dto.PostInfo;
import gwangju.ssafy.backend.domain.post.dto.cond.SearchPostCond;
import gwangju.ssafy.backend.domain.post.entity.enums.PostCategory;
import gwangju.ssafy.backend.domain.post.repository.QuerydslPostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QuerydslPostRepositoryImpl implements QuerydslPostRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<PostInfo> findAllInGroupByCond(Long groupId, SearchPostCond cond) {
		return jpaQueryFactory
			.select(Projections.constructor(PostInfo.class,
				post.id.as("postId"),
				Expressions.asNumber(groupId).as("groupId"),
				post.title,
				post.isAnonymous.when(true)
					.then("익명")
					.otherwise(user.userNickname).as("nickname"),
				post.createdAt,
				post.content,
				ExpressionUtils.as(
					JPAExpressions.select(count(comment.id))
						.from(comment)
						.where(comment.post.id.eq(post.id),
							comment.isDeleted.isFalse()),
					"studentCount"),
				user.userImageUrl.as("profileImg"),
				user.id.as("userId"),
				post.category
			))
			.from(post)
			.innerJoin(post.user, user)
			.where(post.group.id.eq(groupId),
				post.isDeleted.isFalse()
				, ltPostId(cond.getLastPostId())
				, eqCategory(cond.getCategory())
				, searchKeyword(cond.getKeyword()))
			.orderBy(post.id.desc())
			.limit(cond.getPageSize())
			.fetch();
	}

	private BooleanExpression ltPostId(Long postId) {
		return postId == null ? null : post.id.lt(postId);
	}

	private BooleanExpression containsTitle(String title) {
		return title == null ? null : post.title.contains(title);
	}

	private BooleanExpression containsUserNickname(String nickname) {
		return nickname == null ? null : user.userNickname.contains(nickname);
	}

	private BooleanExpression containsContent(String content) {
		return content == null ? null : post.content.contains(content);
	}

	private BooleanExpression eqCategory(PostCategory category) {
		return category == null ? null : post.category.eq(category);
	}
	private BooleanBuilder searchKeyword(String keyword) {
		if(keyword == null) return null;
		return new BooleanBuilder().and(containsContent(keyword).or(
			containsTitle(keyword).or(containsUserNickname(keyword))));
	}

}
