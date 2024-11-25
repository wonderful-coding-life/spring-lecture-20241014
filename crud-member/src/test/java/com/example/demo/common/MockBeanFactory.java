package com.example.demo.common;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class MockBeanFactory {
    public static void setMockBean(MemberRepository memberRepository) {
        when(memberRepository.findById(1L)).thenReturn(
                Optional.ofNullable(Member.builder()
                        .id(1L)
                        .name("윤서준")
                        .email("ysj@hanbit.co.kr")
                        .age(10).build()));
        when(memberRepository.findById(2L)).thenReturn(
                Optional.ofNullable(Member.builder()
                        .id(2L)
                        .name("윤서준")
                        .email("ysj@hanbit.co.kr")
                        .age(10).build()));
        when(memberRepository.findById(3L)).thenReturn(
                Optional.ofNullable(Member.builder()
                        .id(3L)
                        .name("윤서준")
                        .email("ysj@hanbit.co.kr")
                        .age(10).build()));
    }
}
