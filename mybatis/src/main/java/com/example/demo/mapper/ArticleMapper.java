package com.example.demo.mapper;

import com.example.demo.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {
    @Select("SELECT * FROM article")
    @Results(id="articleResult", value={
            @Result(property="title", column="title"),
            @Result(property="description", column="description"),
            @Result(property="memberId", column="member_id")
    })
    List<Article> findAll();

    @Select("SELECT * FROM article WHERE id=#{id}")
    @ResultMap("articleResult")
    Optional<Article> findById(@Param("id") Long id);

    @Insert("""
            INSERT INTO article(title, description, created, updated, member_id)
            VALUES(#{article.title}, #{article.description}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, #{article.memberId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("article") Article article);

    @Update("""
        UPDATE article
            SET title=#{title}, description=#{description}, updated=CURRENT_TIMESTAMP
            WHERE id=#{id}
    """)
    int update(@Param("id") Long id, @Param("title") String title, @Param("description") String description);

    @Delete("DELETE FROM article WHERE id=#{id}")
    int deleteById(@Param("id") Long id);
}
