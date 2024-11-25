package com.example.demo;

import lombok.*;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode(of = {"name", "price"})
public class Product {
    @Getter
    @Setter
    private String name;
    private final Integer price;
    private String description;
    private String provider;
}
