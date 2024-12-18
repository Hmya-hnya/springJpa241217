package com.kh.springJpa241217.service;

import com.kh.springJpa241217.dto.LoginReqDto;
import com.kh.springJpa241217.dto.MemberReqDto;
import com.kh.springJpa241217.dto.MemberResDto;
import com.kh.springJpa241217.entity.Member;
import com.kh.springJpa241217.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j // 로그 정보를 출력하기 위해 사용
@Service // 스프링 container에 빈(객체)등록 (의존성 주입만 받아 사용 할 수 있도록?)
@RequiredArgsConstructor // 생성자를 생성
@Transactional // 여러개의 작업을 하나의 논리적인 단위로 묶어줌
public class AuthService {
    // 생성자를 통한 의존성 주입, 생성자를 통해서 의존성 주입을 받는 경우 Autowired 생략
    private final MemberRepository memberRepository;
    // 회원가입 여부
    public boolean isMember(String email) {
        return memberRepository.existsByEmail(email); // 이메일이 존재하면 true, 없으면 false
    }
    // 회원가입
    public boolean signUp(MemberReqDto memberReqDto) {
        try {
            Member member = convertDtoToEntity(memberReqDto);
            memberRepository.save(member); // 회원가입, save() insert, update 역할
            return true;
        } catch (Exception e) {
            log.error("회원가입 실패: {}", e.getMessage());
            return false;
        }
    }
    // 로그인
    public boolean login(LoginReqDto loginReqDto) {
        try {
            Optional<Member> member = memberRepository
                    .findByEmailAndPwd(loginReqDto.getEmail(), loginReqDto.getPwd());
            return member.isPresent(); // 해당 객체가 있음을 의미
        } catch (Exception e) {
            log.error("로그인 실패: {}", e.getMessage());
            return false;
        }
    }

    // 회원가입 DTO -> Entity
    private Member convertDtoToEntity(MemberReqDto memberReqDto) {
        Member member = new Member();
        member.setEmail(memberReqDto.getEmail());
        member.setName(memberReqDto.getName());
        member.setPwd(memberReqDto.getPwd());
        return member;
    }
    // 모든 회원 조회
    public List<MemberResDto> findAllMembers() {
        List<Member> members = memberRepository.findAll(); // JpaRepository 기본 메서드 활용
        return members.stream()
                .map(this::convertEntityToDto) // Entity -> DTO 변환
                .collect(Collectors.toList());
    }

    // 특정 이메일로 회원 조회
    public MemberResDto findMemberByEmail(String email) {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        return memberOpt.map(this::convertEntityToDto).orElse(null); // Optional 처리
    }

    // Entity -> DTO 변환 (생성자 사용)
    private MemberResDto convertEntityToDto(Member member) {
        // 생성자를 사용하여 DTO 객체 생성
        return new MemberResDto(
                member.getEmail(),
                member.getName(),
                member.getImgPath(),
                member.getRegDate()
        );
    }

}