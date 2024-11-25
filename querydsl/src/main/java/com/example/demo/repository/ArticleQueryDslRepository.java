package com.example.demo.repository;

import com.example.demo.model.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleQueryDslRepository {
    enum SearchType {
        TITLE,
        DESCRIPTION,
        BOTH
    }

    List<Article> queryAll();

    List<Article> queryByKeyword(String keyword, SearchType searchType, long offset, long limit);
}
