package com.kh.springJpa241217.entity;

import com.kh.springJpa241217.constant.Authority;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // 해당 클래스가 Entity 임을 나타냄
@Table(name="member") // 테이블 이름 지정, 테이블 이름은 소문자, 카멜 표기법 -> 스네이크 표기법으로 변경됨
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString // 오버라이딩
public class Member {
    @Id // 해당 필드를 기본키로 지정
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성전략, JPA가 자동으로 생성전략을 정함
    private Long id; // Primary Key

    @Column(nullable = false, length = 50) // null값이 올 수 없다는 제약조건 생성
    private String email;
    @Column(nullable = false)
    private String pwd;
    @Column(length = 50)
    private String name;
    @Column(name = "register_date")
    private LocalDateTime regDate;
    @Column(name = "image_path")
    private String imgPath;
//    @PrePersist // JPA의 콜백 메서드로 엔티티가 저장되기 전에 실행, DB에 데이터가 삽입되기 전에 자동 설정
//    protected void onCreate() {
//        this.regDate = LocalDateTime.now();
//    }
    // 게시글 목록에 대한 OntoMany
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email, String name, String pwd, String img, Authority authority) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.imgPath = img;
        this.authority = authority;
        this.regDate = LocalDateTime.now();
    }
}