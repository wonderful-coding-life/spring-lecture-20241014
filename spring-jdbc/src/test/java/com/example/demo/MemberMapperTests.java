package com.example.demo;

import com.example.demo.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
    public void findByName() {
        var members = mapper.findByName("공미영");
        for (Member member : members) {
            System.out.println("Member = " + member);
        }
    }

    @Test
    public void insert() {
        var member = Member.builder()
                .name("김희선")
                .email("spiderman@hanbit.co.kr")
                .build();
        System.out.println("insert return " + mapper.insert(member));
    }

    @Test
    public void delete() {
        System.out.println("insert return " + mapper.delete(5L));
    }
}
