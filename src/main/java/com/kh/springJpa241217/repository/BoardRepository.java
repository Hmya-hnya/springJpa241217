package com.kh.springJpa241217.repository;

import com.kh.springJpa241217.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String keyword);  // 제목검색
    List<Board> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);
}
