package gwangju.ssafy.backend.domain.board.repository.impl;

import static gwangju.ssafy.backend.domain.board.entity.QPost.post;
import static gwangju.ssafy.backend.domain.user.entity.QUser.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangju.ssafy.backend.domain.board.dto.PostInfo;
import gwangju.ssafy.backend.domain.board.dto.cond.SearchPostCond;
import gwangju.ssafy.backend.domain.board.entity.QPost;
import gwangju.ssafy.backend.domain.board.entity.enums.PostCategory;
import gwangju.ssafy.backend.domain.board.repository.QuerydslPostRepository;
import gwangju.ssafy.backend.domain.user.entity.QUser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QuerydslPostRepositoryImpl implements QuerydslPostRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<PostInfo> findAllInGroupByCond(Long groupId, SearchPostCond cond) {
		return 	jpaQueryFactory
			.select(Projections.constructor(PostInfo.class,
				post.id.as("postId"),
				Expressions.asNumber(groupId).as("groupId"),
				post.title,
				post.isAnonymous.when(true)
					.then("익명")
					.otherwise(user.userNickname).as("nickname"),
				post.createdAt,
				post.content
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
