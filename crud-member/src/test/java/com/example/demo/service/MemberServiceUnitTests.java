package com.example.demo.service;

import com.example.demo.common.MockBeanFactory;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberServiceUnitTests {

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void findById() {
        MockBeanFactory.setMockBean(memberRepository);
//        when(memberRepository.findById(1L)).thenReturn(
//                Optional.ofNullable(Member.builder()
//                        .id(1L)
//                        .name("윤서준")
//                        .email("ysj@hanbit.co.kr")
//                        .age(10).build()));
//        when(memberRepository.findById(2L)).thenReturn(
//                Optional.ofNullable(Member.builder()
//                        .id(2L)
//                        .name("윤서준")
//                        .email("ysj@hanbit.co.kr")
//                        .age(10).build()));
//        when(memberRepository.findById(3L)).thenReturn(
//                Optional.ofNullable(Member.builder()
//                        .id(3L)
//                        .name("윤서준")
//                        .email("ysj@hanbit.co.kr")
//                        .age(10).build()));
        var memberResponse = memberService.findById(1L);
        assertThat(memberResponse.getAge()).isEqualTo(10);
    }
}
