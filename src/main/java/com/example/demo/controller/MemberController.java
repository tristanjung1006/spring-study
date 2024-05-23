package com.example.demo.controller;

import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // MemberController가 생성될 때 스프링 빈에 등록되어있는 MemberService를 객체를 가져다가 넣어준다

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
