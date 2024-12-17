package com.kh.springJpa241217.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="member")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Member {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Primary Key

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pwd;

    private String name;
    private LocalDateTime regDate;
    private String imgPath;
}
