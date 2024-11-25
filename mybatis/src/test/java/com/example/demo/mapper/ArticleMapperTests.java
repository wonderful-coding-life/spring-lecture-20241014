package com.example.demo.mapper;

import com.example.demo.model.Article;
import com.example.demo.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ArticleMapperTests {
    @Autowired
    private ArticleMapper mapper;

    @Test
    public void findAll() {
        var articles = mapper.findAll();
        for (Article article : articles) {
            log.info("{}", article);
        }
    }

    @Test
    public void insert() {
        Article article = Article.builder()
                .title("방학 첫날")
                .description("신난다. 방학이다.")
                .memberId(1L).build();
        int insertedCount = mapper.insert(article);
        log.info("insert {}", insertedCount);
        log.info("inserted {}", article);
    }

    @Test
    public void update() {
        var updated = mapper.update(1L, "방학 둘째날", "신난다, 방학 둘째날이다.");
        log.info("updated {}", updated);
        var article = mapper.findById(1L);
        log.info("{}", article);
    }

    @Test
    public void delete() {
        var deleted = mapper.deleteById(5L);
        log.info("deleted {}", deleted);
        var article = mapper.findById(5L).orElse(null);
        log.info("{}", article);
    }
}
