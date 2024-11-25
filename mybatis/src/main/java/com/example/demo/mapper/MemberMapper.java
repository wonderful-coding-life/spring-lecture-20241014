package com.example.demo.mapper;

import com.example.demo.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    List<Member> findAll();
    List<Member> findAllOrderBy(@Param("order") String order, @Param("dir") String dir);
    Optional<Member> findById(@Param("id") Long id);
    List<Member> findByName(@Param("name") String name);
    List<Member> findAllOrderByAgeAscNameAsc();
    int insert(@Param("member") Member member);
}
