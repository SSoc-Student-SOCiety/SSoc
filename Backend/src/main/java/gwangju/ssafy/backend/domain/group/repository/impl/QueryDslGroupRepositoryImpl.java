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
				school.name.as("school")
				))
			.from(group)
			.innerJoin(group.school, school)
			.where(searchGroupCond(cond))
			.limit(cond.getLimit())
			.offset(cond.getPageNumber() * cond.getLimit())
			.fetch();
	}

	@Override
	public List<GroupSimpleInfo> findMyGroups(MyGroupSearchCond cond) {
		return jpaQueryFactory
			.select(Projections.fields(GroupSimpleInfo.class,
				group.id.as("groupId"),
				group.name.as("name"),
				group.aboutUs.as("aboutUs"),
				school.name.as("school")
			))
			.from(groupMember)
			.innerJoin(groupMember.group, group)
			.innerJoin(group.school, school)
			.where(searchMyGroupCond(cond))
			.limit(cond.getLimit())
			.offset(cond.getPageNumber() * cond.getLimit())
			.fetch();
	}

	private BooleanExpression filterGroupCategory(GroupCategory category) {
		return category == null ? null
			: group.category.eq(category);
	}

	private BooleanExpression searchName(String name) {
		return name == null ? null
			: group.name.contains(name);
	}

	private BooleanExpression searchAboutUs(String aboutUs) {
		return aboutUs == null ? null
			: group.aboutUs.contains(aboutUs);
	}

	private BooleanExpression searchSchool(String schoolName) {
		return school == null ? null
			: school.name.contains(schoolName);
	}


	private BooleanBuilder searchGroupCond(GroupSearchCond cond) {
		return new BooleanBuilder()
			.and(group.isActive.eq(true))
			.and(filterGroupCategory(cond.getCategory()))
			.and(searchName(cond.getWord()).or(searchAboutUs(cond.getWord())).or(searchSchool(cond.getWord())));
	}

	private BooleanBuilder searchMyGroupCond(MyGroupSearchCond cond) {
		return new BooleanBuilder()
			.and(groupMember.user.id.eq(cond.getUserId()))
			.and(group.isActive.eq(true))
			.and(filterGroupCategory(cond.getCategory()))
			.and(searchName(cond.getWord()).or(searchAboutUs(cond.getWord())).or(searchSchool(cond.getWord())));
	}

}
