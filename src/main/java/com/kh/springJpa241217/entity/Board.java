package com.kh.springJpa241217.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 게시글에 관한 Entity
@Entity
@Table(name = "board")
@Getter @Setter @ToString
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;
    @Column(nullable = false)
    private String title;   // 글제목
    @Lob
    @Column(length = 1000)
    private String content; // 글내용

    private String imgPath; // 게시글 이미지 경로
    private LocalDateTime regDate;  // 게시글 등록일자
    @PrePersist
    public void prePersist() {
        regDate = LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board")  // 주인이 아님을 의미, 즉 객체를 참조만 함.
    private List<Comment> comments = new ArrayList<>();
}
