package com.example.demo.repository;

import com.example.demo.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.print.DocFlavor;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:/test-member.sql"})
public class ArticleRepositoryTests {

    @Autowired
    private ArticleRepository repository;

    @Test
    public void findAll() {
        var articles = repository.findAll();
        for (Article article : articles) {
            log.info("{}", article);
        }
        articles.get(0).setTitle("제목을 바꾸었습니다. 2");
        articles.get(1).setDescription("본문을 바꾸었습니다.");
        repository.saveAll(articles);
    }
}
