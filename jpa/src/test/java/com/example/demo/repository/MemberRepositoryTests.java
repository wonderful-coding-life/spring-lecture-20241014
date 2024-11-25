package com.example.demo.repository;

import com.example.demo.dto.MemberStats;
import com.example.demo.dto.MemberStatsNative;
import com.example.demo.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.data.domain.Sort.Direction.DESC;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:/test-member.sql"})
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    //@BeforeEach
    public void before() {
        Member member = Member.builder()
                .name("공미영")
                .email("mm@hanbit.co.kr")
                .age(10).build();
        memberRepository.save(member);
    }

    //@AfterEach
    public void after() {
        var members = memberRepository.findByName("공미영");
        for (Member member : members) {
            memberRepository.delete(member);
        }
    }

    @Test
    @Disabled
    @Transactional
    public void findAll() {
        var members = memberRepository.findAll();
        log.info("{}", members.get(0));
//        for (Member member : members) {
//            log.info("{}", member);
//        }
        assertThat(members.size()).isEqualTo(4);
    }

    @Test
    @Disabled
    public void findMemberStatsObject() {
        List<Object[]> memberStatsList = memberRepository.getMemberStatsObject();
        for (Object[] ob : memberStatsList){
            String name = (String)ob[0];
            String email = (String)ob[1];
            Long count = (Long)ob[2];
            log.info("name={} email={} count={}", name, email, count);
        }
    }

    @Test
    @Disabled
    public void getMemberStats() {
        List<MemberStats> memberStatsList = memberRepository.getMemberStats();
        for (MemberStats memberStats : memberStatsList) {
            log.info("{}", memberStats);
        }
    }

    @Test
    @Disabled
    public void getMemberStatsNativeObject() {
        List<Object[]> memberStatsList = memberRepository.getMemberStatsNativeObjects();
        for (Object[] ob : memberStatsList){
            String name = (String)ob[0];
            String email = (String)ob[1];
            Long count = (Long)ob[2];
            log.info("{} {} {}", name, email, count);
        }
    }

    @Test
    public void getMemberStatsNative() {
        List<MemberStatsNative> memberStatsList = memberRepository.getMemberStatsNative();
        for (MemberStatsNative memberStats : memberStatsList) {
            log.info("{} {} {}", memberStats.getName(), memberStats.getEmail(), memberStats.getCount());
        }
    }

    @Test
    @Disabled
    public void jpaRepository() {
        Member member = Member.builder()
                .name("공미영")
                .email("mm@hanbit.co.kr")
                .age(10).build();
        memberRepository.save(member);

        var members = memberRepository.findAll();
        for (Member m : members) {
            log.info("{}", m);
        }

        member = memberRepository.findById(1L).orElseThrow();
        memberRepository.delete(member);

        memberRepository.deleteById(member.getId());

        memberRepository.existsById(1L);

        var memberCount = memberRepository.count();
        log.info("{}", memberCount);
    }

    @Test
    @Disabled
    public void queryMethod() {
        var members = memberRepository.findByName("공미영");
        log.info("{}", members);

        members = memberRepository.findByAgeGreaterThanEqual(11);
        log.info("{}", members);
    }

    @Test
    @Disabled
    public void testSort() {
        Sort sort = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("age"));
        var members = memberRepository.findByAgeGreaterThanEqual(10, sort);
        for (Member member : members) {
            log.info("{}", member);
        }
    }

    @Test
    @Disabled
    public void testPagination() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(DESC, "age"));
        var members = memberRepository.findByNameContaining("윤", pageable);
        for (Member member : members) {
            log.info("{}", member);
        }
    }

    @Test
    @Disabled
    public void testPage() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(DESC, "age"));
        var members = memberRepository.findPageByNameContaining("윤", pageable);

        log.info("{} {} {}", members.getTotalElements(), members.getTotalPages(), members.getNumber());
        for (Member member : members) {
            log.info("{}", member);
        }
    }
}
