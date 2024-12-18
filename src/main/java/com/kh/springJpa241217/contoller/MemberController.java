package com.kh.springJpa241217.contoller;

import com.kh.springJpa241217.dto.MemberReqDto;
import com.kh.springJpa241217.dto.MemberResDto;
import com.kh.springJpa241217.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원 전체 조회
    @GetMapping("/memberList")
    public ResponseEntity<List<MemberResDto>> getAllMembers() {
        List<MemberResDto> members = memberService.getMemberList();
        return ResponseEntity.ok(members);
    }

    //회원 상세 조회
    @GetMapping("/{email}")
    public ResponseEntity<MemberResDto> getMemberDetail(@PathVariable String email) {
        try {
            MemberResDto member = memberService.getMemberDetail(email);
            return ResponseEntity.ok(member);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //회원 정보 수정
    @PutMapping("/editUser")
    public ResponseEntity<String> updateMember(@RequestBody MemberReqDto memberReqDto) {
        boolean isUpdated = memberService.modifyMember(memberReqDto);
        if (isUpdated) {
            return ResponseEntity.ok("회원 정보가 수정되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("회원 정보 수정에 실패했습니다."); // 400 Bad Request
        }
    }

    // 회원 삭제
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteMember(@PathVariable String email) {
        boolean isDeleted = memberService.deleteMember(email);
        if (isDeleted) {
            return ResponseEntity.ok("회원이 삭제되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("회원 삭제에 실패했습니다.");
        }
    }
}
