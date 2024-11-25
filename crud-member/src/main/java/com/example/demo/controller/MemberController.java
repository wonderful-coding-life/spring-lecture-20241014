package com.example.demo.controller;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository repository;
    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> get(@RequestParam(name="name", required = false) String name) {
        return memberService.findAll(name);
    }

    @GetMapping("/{id}")
    public MemberResponse getById(@PathVariable("id") Long id) {
        return memberService.findById(id);
        //return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse post(@RequestBody MemberRequest memberRequest) {
        return memberService.create(memberRequest);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MemberResponse> postBatch(@RequestBody List<MemberRequest> memberRequests) {
        return memberService.createBatch(memberRequests);
    }

    @PutMapping("/{id}")
    public Member put(@PathVariable("id") Long id, @RequestBody Member member) {
        if (repository.existsById(id)) {
            member.setId(id);
            return repository.save(member);
        }
        throw new NotFoundException();
    }

    @PatchMapping("/{id}")
    public Member patch(@PathVariable("id") Long id, @RequestBody Member member) {
        var old = repository.findById(id).orElseThrow(NotFoundException::new);
        if (member.getName() != null) old.setName(member.getName());
        if (member.getEmail() != null) old.setEmail(member.getEmail());
        if (member.getAge() != null) old.setAge(member.getAge());
        if (member.getPassword() != null) old.setPassword(member.getPassword());
        if (member.getEnabled() != null) old.setEnabled(member.getEnabled());
        return repository.save(old);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException();
    }

}
