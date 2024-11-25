package com.example.demo.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("회원 생성 테스트")
    @Disabled
    public void findAll() {
        assertThat(repository.findAll().size()).isEqualTo(0);
    }

    @DisplayName("회원 삭제 테스트")
    @RepeatedTest(3)
    public void findAll2() {
        assertThat(repository.findAll().size()).isEqualTo(0);
        assertThat(repository.findAll().size()).isEqualTo(0);
    }
}
