package com.example.demo.repository;

import com.example.demo.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.demo.repository.ProductQueryDslRepository.SearchType.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProducRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void beforeEach() {
        var product1 = Product.builder().title("갤럭시 와치").description("삼성의 스마트 와치").build();
        var product2 = Product.builder().title("애플 와치").description("애플의 스마트 와치").build();
        var product3 = Product.builder().title("샤오미 와치").description("샤오미의 스마트 와치").build();
        var product4 = Product.builder().title("캠퍼스 와치").description("캠퍼스의 스마트 와치").build();
        var products = List.of(product1, product2, product3, product4);
        productRepository.saveAll(products);
    }

    @AfterEach
    public void afterEach() {
        productRepository.deleteAll();
    }

    @Test
    //use @Transactional for stream
    public void queryByKeyword() {
        var products = productRepository.queryByKeyword("와치", TITLE, 0, 10);
        assertThat(products.size()).isEqualTo(4);
        products = productRepository.queryByKeyword("애플", DESCRIPTION, 0, 10);
        assertThat(products.size()).isEqualTo(1);
        products = productRepository.queryByKeyword("스마트", BOTH, 0, 10);
        assertThat(products.size()).isEqualTo(4);
    }
}
