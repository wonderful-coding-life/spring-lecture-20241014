package com.example.demo.repository;

import com.example.demo.model.Article;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.model.QArticle.article;

@Repository
@RequiredArgsConstructor
public class ArticleQueryDslRepositoryImpl implements ArticleQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Article> queryAll() {
        return jpaQueryFactory
                .selectFrom(article)
                .fetch();
    }

    @Override
    public List<Article> queryByKeyword(String keyword, SearchType searchType, long offset, long limit) {
        var query = jpaQueryFactory.selectFrom(article);
        if (keyword != null && searchType != null) {
            if (searchType == SearchType.TITLE) {
                query.where(article.title.contains(keyword));
            } else if (searchType == SearchType.DESCRIPTION) {
                query.where(article.description.contains(keyword));
            } else {
                query.where(article.title.contains(keyword).or(article.description.contains(keyword)));
            }
        }
        return query
                .orderBy(article.created.desc(), article.id.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}
