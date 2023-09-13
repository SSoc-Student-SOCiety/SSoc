package gwangju.ssafy.backend.domain.group.service.impl;

import gwangju.ssafy.backend.domain.group.dto.DeleteGroupSignInfoRequest;
import gwangju.ssafy.backend.domain.group.dto.GroupSignupInfo;
import gwangju.ssafy.backend.domain.group.dto.GetGroupSignupInfo;
import gwangju.ssafy.backend.domain.group.entity.GroupMember;
import gwangju.ssafy.backend.domain.group.entity.GroupSignup;
import gwangju.ssafy.backend.domain.group.entity.enums.GroupMemberRole;
import gwangju.ssafy.backend.domain.group.exception.GroupException;
import gwangju.ssafy.backend.domain.group.repository.GroupMemberRepository;
import gwangju.ssafy.backend.domain.group.repository.GroupSignupRepository;
import gwangju.ssafy.backend.domain.group.service.GroupSignupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static gwangju.ssafy.backend.domain.group.exception.GroupError.NOT_EXISTS_USER;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class GroupSignupServiceImpl implements GroupSignupService {

    private final GroupSignupRepository groupSignupRepository;

    private final GroupMemberRepository groupMemberRepository;


    // 해당 그룹에 가입 신청한 회원들 전체 조회
    @Override
    public List<GroupSignupInfo> searchAllGroupSignup() {
        // 가입 요청 대기중인 사람들만 추출 (BysignupStatus)
        List<GroupSignup> groupSignupList = groupSignupRepository.findAllBySignupStatus(false);
        List<GroupSignupInfo> groupSignupInfoList = new ArrayList<>();
        for (GroupSignup groupSignup : groupSignupList) {
            GroupSignupInfo groupSignupInfo = new GroupSignupInfo();
            groupSignupInfo.convert(groupSignup);
            log.info(groupSignupInfo.getUserName());
            groupSignupInfoList.add(groupSignupInfo);
        }
        return groupSignupInfoList;
    }

    // 해당 그룹에 가입 신청한 사람 가입 신청 거절
    @Override
    public GroupSignupInfo rejectSignup(DeleteGroupSignInfoRequest request) {
        GroupSignup groupSignup = groupSignupRepository.findById(request.getGroupSignUpId())
                .orElseThrow(() -> new GroupException(NOT_EXISTS_USER));
        groupSignupRepository.delete(groupSignup);
        GroupSignupInfo groupSignupInfo = new GroupSignupInfo();
        groupSignupInfo.convert(groupSignup);
        return groupSignupInfo;
    }


    // 그룹 내 해당 가입 신청 수락
    @Override
    public GroupSignupInfo ApproveGroupSignUp(GetGroupSignupInfo request) {
        GroupSignup groupSignup = groupSignupRepository.findById(request.getGroupSignUpId())
                .orElseThrow(() -> new GroupException(NOT_EXISTS_USER));
        groupSignup.signupApprove();    // 해당 가입신청 가입 처리 (update)

        GroupMember groupMember = GroupMember.builder()
                .group(groupSignup.getGroup())
                .user(groupSignup.getUser())
                .role(GroupMemberRole.MEMBER)
                .build();

        groupMemberRepository.save(groupMember);    // 해당 가입신청한 유저정보 및 그룹 정보 그룹원(소속원) DB에 저장
        GroupSignupInfo groupSignupInfo = new GroupSignupInfo();
        groupSignupInfo.convert(groupSignup);
        return groupSignupInfo;
    }


}
