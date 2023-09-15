package gwangju.ssafy.backend.domain.group.service;

import gwangju.ssafy.backend.domain.group.dto.DeleteGroupSignInfoRequest;
import gwangju.ssafy.backend.domain.group.dto.GroupSignupInfo;
import gwangju.ssafy.backend.domain.group.dto.GetGroupSignupInfo;

import java.util.List;

public interface GroupSignupService {
    // 그룹 관리자 입장
    // 그룹 가입에서 해당 그룹에 가입 신청 대기중인 리스트 가져오기
    List<GroupSignupInfo> searchAllGroupSignup(Long groupId);

    // 그룹에서 해당 유저 가입 거절
    GroupSignupInfo rejectSignup(DeleteGroupSignInfoRequest request);

    // 그룹 내 해당 가입 신청 수락
    GroupSignupInfo ApproveGroupSignup(GetGroupSignupInfo request);

    // 그룹에 아직 가입되어 있지 않은 유저가 해당 그룹에 가입 신청
    GroupSignupInfo requestSignup(Long userId, Long groupId);

}
