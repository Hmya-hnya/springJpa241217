package com.kh.springJpa241217.contoller;

import com.kh.springJpa241217.dto.BoardReqDto;
import com.kh.springJpa241217.dto.BoardResDto;
import com.kh.springJpa241217.dto.CommentReqDto;
import com.kh.springJpa241217.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    // 게시글 등록
    @PostMapping("/save")
    public ResponseEntity<Boolean> saveBoard(@RequestBody BoardReqDto boardReqDto) {
        boolean isSuccess = boardService.saveBoard(boardReqDto);
        return ResponseEntity.ok(isSuccess);
    }

    // 게시글 상세조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResDto> findBoardById(@PathVariable Long id) {
        try {
            BoardResDto board = boardService.findByBoardId(id);
            return ResponseEntity.ok(board);
        } catch (Exception e) {
            log.error("게시글 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // 게시글 전체조회
    @GetMapping("/all")
    public ResponseEntity<List<BoardResDto>> getAllBoards() {
        List<BoardResDto> boards = boardService.findAllBoard();
        return ResponseEntity.ok(boards);
    }

    // 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<List<BoardResDto>> searchBoard(@RequestParam String keyword) {
        List<BoardResDto> boards = boardService.searchBoard(keyword);
        return ResponseEntity.ok(boards);
    }

    // 게시글 페이지 수 조회
    @GetMapping("/pages")
    public ResponseEntity<Integer> getBoardPageCount(@RequestParam int page, @RequestParam int size) {
        int totalPages = boardService.getBoardPageCount(page, size);
        return ResponseEntity.ok(totalPages);
    }

    // 게시글 페이징 처리
    @GetMapping("/paging")
    public ResponseEntity<List<BoardResDto>> getPagingBoardList(@RequestParam int page, @RequestParam int size) {
        List<BoardResDto> boards = boardService.pagingBoardList(page, size);
        return ResponseEntity.ok(boards);
    }
    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable Long id) {
        boolean isDeleted = boardService.deleteBoard(id);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(404).body(false);
        }
    }
    // 게시글 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateBoard(@PathVariable Long id, @RequestBody BoardReqDto boardReqDto) {
        boolean isUpdated = boardService.updateBoard(id, boardReqDto);
        return isUpdated ? ResponseEntity.ok(true) : ResponseEntity.status(404).body(false);
    }
    // 제목내용 게시글 검색
    @GetMapping("/searchOr")
    public ResponseEntity<List<BoardResDto>> searchOrBoard(@RequestParam String keyword) {
        List<BoardResDto> boardResDtoList = boardService.searchOrBoard(keyword);
        if (boardResDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boardResDtoList);
    }
    // 댓글 추가
    @PostMapping("/{boardId}/comments")
    public ResponseEntity<Boolean> addComment(@PathVariable Long boardId, @RequestBody CommentReqDto commentReqDto) {
        boolean isSuccess = boardService.addComment(boardId, commentReqDto);
        return ResponseEntity.ok(isSuccess);
    }
    // 댓글 삭제
    @DeleteMapping("{boardId}/comments/{commentId}")
    public ResponseEntity<Boolean> removeComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        boolean isRemove = boardService.removeComment(boardId, commentId);
        return ResponseEntity.ok(isRemove);
    }
}
