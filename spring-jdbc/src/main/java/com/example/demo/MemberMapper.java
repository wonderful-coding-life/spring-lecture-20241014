package com.example.demo;

import com.example.demo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberMapper {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MemberRowMapper memberRowMapper;

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", memberRowMapper);
    }

    public List<Member> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM member WHERE name=?", memberRowMapper, name);
    }

    public int insert(Member member) {
        return jdbcTemplate.update("INSERT INTO member(name, email, age) VALUES(?, ?, ?)", member.getName(), member.getEmail(), member.getAge());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM member WHERE id=?", id);
    }
}
