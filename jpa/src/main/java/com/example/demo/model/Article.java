package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date created;
    private Date updated;
    //private Long memberId;
    @ToString.Exclude
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
}
