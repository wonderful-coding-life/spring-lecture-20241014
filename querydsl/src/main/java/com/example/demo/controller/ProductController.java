package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.repository.ProductQueryDslRepository.SearchType.BOTH;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @PostMapping
    Product post(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    List<Product> get(@RequestParam(value="keyword", required = false) String keyword, @PageableDefault(size = 10) Pageable pageable)  {
        return productRepository.queryByKeyword(keyword, BOTH, pageable.getOffset(), pageable.getPageSize());
    }
}
