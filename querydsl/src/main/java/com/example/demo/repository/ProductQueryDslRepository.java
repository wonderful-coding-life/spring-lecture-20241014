package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductQueryDslRepository {
    enum SearchType {
        TITLE,
        DESCRIPTION,
        BOTH
    }
    List<Product> queryByKeyword(String keyword, SearchType searchType, long offset, long limit);
}
