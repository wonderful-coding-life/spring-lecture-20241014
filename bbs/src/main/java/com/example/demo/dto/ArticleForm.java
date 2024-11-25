package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleForm {
    private Long id;
    @NotBlank(message = "게시글 제목을 입력하세요")
    private String title;
    @NotBlank(message = "게시글 본문을 입력하세요")
//    @Email
//    @Size(min=100, max=2000)
    private String description;
//    @Min(value=5)
//    @Max(value=29)
//    @Positive
//    @PositiveOrZero
//    @Negative
//    @NegativeOrZero
//    private Integer age;
}
