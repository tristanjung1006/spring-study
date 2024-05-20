package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {


    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore(); // 메모리 클리어
    }

    // 테스트 코드는 실제 코드가 아니므로 한글로 작성해도 된다
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

    // 테스트는 정상플로우도 중요하지만 예외플로우도 중요하다.
    @Test
    public void 중복_회원_예외() {
        //given
        Member member = new Member();
        member.setName("hello");

        Member member2 = new Member();
        member2.setName("hello2");

        //when
        memberService.join(member);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException ignored) {
//            assertTrue(true);
//        }
//        memberService.join(member);

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}