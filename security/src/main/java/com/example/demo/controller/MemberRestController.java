package com.example.demo.controller;

import com.example.demo.model.Member;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberRestController {
    private List<Member> members = List.of(
            new Member(1L, "윤서준", "SeojunYoon@hanbit.co.kr", null),
            new Member(2L, "윤광철", "KwangcheolYoon@hanbit.co.kr", null),
            new Member(3L, "공미영", "MiyeongKong@hanbit.co.kr", null),
            new Member(4L, "김도윤", "DoyunKim@hanbit.co.kr", null)
    );

    @GetMapping("/api/member/list")
    public List<Member> getMembers(Model model) {
        return members;
    }
}
