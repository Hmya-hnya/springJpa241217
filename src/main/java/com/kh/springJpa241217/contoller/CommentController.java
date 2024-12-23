package com.kh.springJpa241217.contoller;

import com.kh.springJpa241217.dto.CommentReqDto;
import com.kh.springJpa241217.dto.CommentResDto;
import com.kh.springJpa241217.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/register")
    public ResponseEntity<Boolean> registerComment(@RequestBody CommentReqDto commentReqDto) {
        boolean isSuccess = commentService.commentRegister(commentReqDto);
        if (isSuccess) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
    // 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId, String email) {
        boolean isSuccess = commentService.commentDelete(commentId, email);
        if (isSuccess) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
    // 댓글 목록 조회 (게시글 Id)
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<CommentResDto>> getCommentsByBoardId(@PathVariable Long boardId) {
        List<CommentResDto> commentList = commentService.getCommentsByBoardId(boardId);
        return ResponseEntity.ok(commentList);
    }
}
