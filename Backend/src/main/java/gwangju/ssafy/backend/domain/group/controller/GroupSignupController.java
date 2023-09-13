package gwangju.ssafy.backend.domain.group.controller;

import gwangju.ssafy.backend.domain.group.dto.DeleteGroupSignInfoRequest;
import gwangju.ssafy.backend.domain.group.dto.GetGroupSignupInfo;
import gwangju.ssafy.backend.domain.group.dto.GroupSignupInfo;
import gwangju.ssafy.backend.domain.group.service.GroupSignupService;
import gwangju.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/group/signup")
@RestController
public class GroupSignupController {
    private final GroupSignupService groupSignupService;

    // 해당 그룹 가입 신청 대기자들 조회
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<Message<List<GroupSignupInfo>>> searchAllGroupSignup() {
        List<GroupSignupInfo> groupSignupInfoList = groupSignupService.searchAllGroupSignup();
        return ResponseEntity.ok().body(Message.success(groupSignupInfoList));
    }

    // 해당 그룹 가입 신청 유저 거절 처리
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @DeleteMapping("/reject/{groupSignupId}")
    public ResponseEntity<Message<GroupSignupInfo>> rejectSignup(@RequestBody DeleteGroupSignInfoRequest request,
                                                                      @PathVariable("groupSignupId") Long groupSignupId) {
        request.setGroupSignUpId(groupSignupId);
        return ResponseEntity.ok().body(Message.success(groupSignupService.rejectSignup(request)));
    }

    // 해당 그룹 가입 신청 유저 수락 처리
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping("/approve/{groupSignupId}")
    public ResponseEntity<Message<GroupSignupInfo>> approveSignup(@RequestBody GetGroupSignupInfo request,
                                                                  @PathVariable("groupSignupId") Long groupSignupId) {
        request.setGroupSignUpId(groupSignupId);
        return ResponseEntity.ok().body(Message.success(groupSignupService.ApproveGroupSignUp(request)));
    }
}
