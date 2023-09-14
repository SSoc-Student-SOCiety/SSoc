package gwangju.ssafy.backend.domain.group.repository.impl;

import static gwangju.ssafy.backend.domain.group.entity.QGroup.group;
import static gwangju.ssafy.backend.domain.group.entity.QGroupMember.groupMember;
import static gwangju.ssafy.backend.domain.group.entity.QSchool.school;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangju.ssafy.backend.domain.group.dto.GroupSearchCond;
import gwangju.ssafy.backend.domain.group.dto.GroupSimpleInfo;
import gwangju.ssafy.backend.domain.group.dto.MyGroupSearchCond;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupCategory;
import gwangju.ssafy.backend.domain.group.repository.QueryDslGroupRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Repository
public class QueryDslGroupRepositoryImpl implements QueryDslGroupRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<GroupSimpleInfo> findAllBySearchCond(GroupSearchCond cond) {
		return jpaQueryFactory
			.select(Projections.fields(GroupSimpleInfo.class,
				group.id.as("groupId"),
				group.name.as("name"),
				group.aboutUs.as("aboutUs"),
				school.name.as("school"),
				group.thumbnail.as("thumbnail")
			))
			.from(group)
			.innerJoin(group.school, school)
			.where(ltGroupId(cond.getLastGroupId()),
				group.isActive.isTrue(),
				searchKeyword(cond.getKeyword()),
				filterGroupCategory(cond.getCategory()))
			.orderBy(group.id.desc())
			.limit(cond.getPageSize())
			.fetch();
	}

	@Override
	public List<GroupSimpleInfo> findMyGroups(MyGroupSearchCond cond) {
		return jpaQueryFactory
			.select(Projections.fields(GroupSimpleInfo.class,
				group.id.as("groupId"),
				group.name.as("name"),
				group.aboutUs.as("aboutUs"),
				school.name.as("school"),
				group.thumbnail.as("thumbnail")
			))
			.from(groupMember)
			.innerJoin(groupMember.group, group)
			.innerJoin(group.school, school)
			.where(
				ltGroupId(cond.getLastGroupId()),
				groupMember.user.id.eq(cond.getUserId()),
				group.isActive.isTrue(),
				filterGroupCategory(cond.getCategory()),
				searchKeyword(cond.getKeyword()))
			.orderBy(group.id.desc())
			.limit(cond.getPageSize())
			.fetch();
	}

	private BooleanExpression ltGroupId(Long groupId) {
		return groupId == null ? null : group.id.lt(groupId);
	}

	private BooleanExpression filterGroupCategory(GroupCategory category) {
		return category == null ? null
			: group.category.eq(category);
	}

	private BooleanBuilder searchKeyword(String keyword) {
		BooleanBuilder builder = new BooleanBuilder();

		if (keyword != null && !keyword.isEmpty()) {
			builder.and(
				group.name.contains(keyword)
					.or(group.aboutUs.contains(keyword))
					.or(school.name.contains(keyword)));
		}

		return builder;
	}

}
