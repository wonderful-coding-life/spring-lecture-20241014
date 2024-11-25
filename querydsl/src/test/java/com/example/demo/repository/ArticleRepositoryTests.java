package com.example.demo.repository;

import com.example.demo.model.Article;
import com.example.demo.model.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ArticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void doBeforeEach() {
        var member1 = Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").build();
        var member2 = Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").build();
        var member3 = Member.builder().name("공미영").email("MiyeongKong@hanbit.co.kr").build();
        var member4 = Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").build();
        var members = List.of(member1, member2, member3, member4);
        memberRepository.saveAll(members);

        var article1 = Article.builder().title("방학 첫날 날씨 맑음").description("방학 첫날이다. 신난다.").member(member1).build();
        var article2 = Article.builder().title("방학 둘째날 날씨 흐림").description("방학 둘째날이다. 신난다.").member(member2).build();
        var article3 = Article.builder().title("방학 세째날 날씨 맑음").description("방학 세째날이다. 즐겁다.").member(member1).build();
        var article4 = Article.builder().title("방학 네째날 날씨 흐림").description("방학 네째날이다. 즐겁다.").member(member2).build();

//        var article1 = Article.builder().title("방학 첫날 날씨 맑음").description("방학 첫날이다. 신난다.").build();
//        var article2 = Article.builder().title("방학 둘째날 날씨 흐림").description("방학 둘째날이다. 신난다.").build();
//        var article3 = Article.builder().title("방학 세째날 날씨 맑음").description("방학 세째날이다. 즐겁다.").build();
//        var article4 = Article.builder().title("방학 네째날 날씨 흐림").description("방학 네째날이다. 즐겁다.").build();


        var articles = List.of(article1, article2, article3, article4);
        articleRepository.saveAll(articles);
    }

    @AfterEach
    public void doAfterEach() {
        articleRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void queryByKeyword() {
        var articles = articleRepository.queryByKeyword("맑음", ArticleQueryDslRepository.SearchType.TITLE, 0, 10);
        assertThat(articles.size()).isEqualTo(2);
        articles = articleRepository.queryByKeyword("즐겁다", ArticleQueryDslRepository.SearchType.DESCRIPTION, 0, 10);
        assertThat(articles.size()).isEqualTo(2);
        articles = articleRepository.queryByKeyword("즐겁다", ArticleQueryDslRepository.SearchType.BOTH, 0, 10);
        assertThat(articles.size()).isEqualTo(2);
    }

    @Test
    public void queryAll() {
        var articles = articleRepository.queryAll();
        assertThat(articles.size()).isEqualTo(4);
    }
}
