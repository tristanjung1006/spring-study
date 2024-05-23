package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // MemberService의 테스트 코드르 작성하려면 Ctrl + Shift + T로 틀을 빠르게 만들 수 있다.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 회원은 안된다
        // 과거에는 if문으로 null의 여부를 판단하여 진행했지만 Optional을 사용해서 다양한 메소드를 사용할 수 있다

        validateDuplicateMember(member);
        // 위에서 메소드로 뽑는 것이 낫다.


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("already exists");
                });
    }

    /**
     * 전체 회원 조회
     */
    // 서비스는 기획자와 협업을 원활히 하기 위해서 비즈니스에 의존적으로 네이밍 및 설계를 하고
    // 리포지토리 같은 경우에는 개발스러운 용어를 선택한다.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
