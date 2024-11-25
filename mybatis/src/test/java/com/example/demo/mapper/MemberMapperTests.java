package com.example.demo.mapper;

import com.example.demo.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper mapper;

    @Test
    public void findAll() {
        var members = mapper.findAll();
        for (Member member : members) {
            System.out.println("Member = " + member);
        }
    }

    @Test
    public void findAllOrderBy() {
//        List<Member> members = mapper.findAllOrderBy("age", "desc; DELETE FROM member;--");
        List<Member> members = mapper.findAllOrderBy("age", "desc");

        for (Member member : members) {
            System.out.println("Member = " + member);
        }
    }

    @Test
    @Disabled
    public void findById() {
        var member = mapper.findById(10L).orElseThrow();
        log.info("{}", member);
    }

    @Test
    @Disabled
    public void findByName() {
        var members = mapper.findByName("공미영");
        for (Member member : members) {
            System.out.println("Member = " + member);
        }
    }

    @Test
    @Disabled
    public void findAllOrderByAgeAsc() {
        var members = mapper.findAllOrderByAgeAscNameAsc();
        for (Member member : members) {
            System.out.println("Member = " + member);
        }
    }

    @Test
    @Disabled
    public void insert() {
        var member = Member.builder()
                .name("정혁2")
                .email("HyeokJung2@hanbit.co.kr")
                .age(10).build();
        int count = mapper.insert(member);
        log.info("count={} member={}", count, member);
    }
}
