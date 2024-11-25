package com.example.demo.repository;

import com.example.demo.dto.MemberStats;
import com.example.demo.dto.MemberStatsNative;
import com.example.demo.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);
    List<Member> findByNameLike(String name);
    List<Member> findByNameContaining(String name);
    List<Member> findByNameContaining(String name, Pageable pageable);
    Page<Member> findPageByNameContaining(String name, Pageable pageable);
    Member findByEmail(String email);
    List<Member> findByAge(Integer age);
    List<Member> findByAgeGreaterThan(Integer age);
    List<Member> findByAgeGreaterThanEqual(Integer age);
    List<Member> findByAgeGreaterThanEqual(Integer age, Sort sort);
    List<Member> findByAgeLessThan(Integer age);
    List<Member> findByAgeLessThanEqual(Integer age);
    List<Member> findByAgeIsNull();
    List<Member> findByAgeIsNotNull();
    List<Member> findByNameOrderByNameAsc(String name);
    List<Member> findByNameOrderByNameAscAgeDesc(String name);
    List<Member> findByNameAndEmail(String name, String email);

    @Query(value = "SELECT * FROM shop WHERE ST_DISTANCE(latitude, longitude, :distance) <= 5", nativeQuery = true)
    List<Member> findOnlyVIP(@Param("distance") Double distance);

    @Query("SELECT m.name, m.email, COUNT(a.id) as count FROM Member m LEFT JOIN Article a ON a.member = m GROUP BY m ORDER BY count DESC")
    List<Object[]> getMemberStatsObject();

    @Query(value="SELECT m.name, m.email, count(a.id) AS count FROM member m LEFT JOIN article a ON m.id = a.member_id GROUP BY m.id ORDER BY count DESC", nativeQuery = true)
    List<Object[]> getMemberStatsNativeObjects();

    @Query("SELECT new com.example.demo.dto.MemberStats(m.name, m.email, COUNT(a.id) as count) FROM Member m LEFT JOIN Article a ON a.member = m GROUP BY m ORDER BY count DESC")
    List<MemberStats> getMemberStats();

    @Query(value="SELECT m.name, m.email, count(a.id) AS count FROM member m LEFT JOIN article a ON m.id = a.member_id GROUP BY m.id ORDER BY count DESC", nativeQuery = true)
    List<MemberStatsNative> getMemberStatsNative();
}
