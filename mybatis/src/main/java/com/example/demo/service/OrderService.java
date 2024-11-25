package com.example.demo.service;

import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberMapper memberMapper;
    private final ArticleMapper articleMapper;

    @Transactional
    public void order(int productId, int count) {
        // insert ... into a
        // update ... into b
        // delete ... from c
        var member = Member.builder()
                .name("정혁5")
                .email("HyeokJung5@hanbit.co.kr")
                .age(10).build();
        memberMapper.insert(member);

        member = Member.builder()
                .name("정혁6")
                .email("HyeokJung5@hanbit.co.kr")
                .age(10).build();
        memberMapper.insert(member);
    }
}
