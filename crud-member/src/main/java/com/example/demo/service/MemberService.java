package com.example.demo.service;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse create(MemberRequest memberRequest) {
        var member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .password("password")
                .enabled(true).build();
        return mapToMemberResponse(memberRepository.save(member));
    }

    @Transactional
    public List<MemberResponse> createBatch(List<MemberRequest> memberRequests) {
        return memberRequests.stream().map(this::create).toList();
    }

    public List<MemberResponse> findAll(String name) {
        if (name == null || name.isBlank()) {
            return memberRepository.findAll().stream().map(this::mapToMemberResponse).toList();
        } else {
            return memberRepository.findByNameContaining(name).stream().map(this::mapToMemberResponse).toList();
        }
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToMemberResponse(member);
    }

    private MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge()).build();
    }
}
