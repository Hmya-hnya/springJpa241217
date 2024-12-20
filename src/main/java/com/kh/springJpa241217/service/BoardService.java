package com.kh.springJpa241217.service;

import com.kh.springJpa241217.dto.BoardReqDto;
import com.kh.springJpa241217.dto.BoardResDto;
import com.kh.springJpa241217.entity.Board;
import com.kh.springJpa241217.entity.Member;
import com.kh.springJpa241217.repository.BoardRepository;
import com.kh.springJpa241217.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    // 게시글 등록
    @Transactional
    public boolean saveBoard(BoardReqDto boardReqDto) {
        try {
            // 회원 정보가 존재하는지 확인 -> 프론트엔드에서 전달한 이메일로 회원 정보를 가져옴
            Member member = memberRepository.findByEmail(boardReqDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
            Board board = new Board();
            board.setTitle(boardReqDto.getTitle());
            board.setContent(boardReqDto.getContent());
            board.setImgPath(boardReqDto.getImgPath());
            board.setMember(member);
            boardRepository.save(board);    // insert
            return true;
        } catch (Exception e) {
            log.error("게시글 등록실패: {}", e.getMessage());
            return false;
        }
    }
    // 게시글 상세조회
    public BoardResDto findByBoardId(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
        return convertEntityToDto(board);
    }
    // 게시글 전체조회
    public List<BoardResDto> findAllBoard() {
        List<Board> boards = boardRepository.findAll(); // DB에 있는 모든 게시글 가져오기
        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for(Board board : boards) {
            // convertEntityToDto를 통해서 BoardResDto를 반환 받아서 List에 추가
            boardResDtoList.add(convertEntityToDto(board));
        }
        return boardResDtoList;
    }
    // 게시글 검색
    public List<BoardResDto> searchBoard(String keyword) {
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for(Board board : boards) {
            boardResDtoList.add(convertEntityToDto(board));
        }
        return boardResDtoList;
    }
    // 게시글 페이지 수 조회 (사이즈 설정은 front에서)
    public int getBoardPageCount(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return boardRepository.findAll(pageRequest).getTotalPages();
    }

    // 게시글 페이징
    public List<BoardResDto> pagingBoardList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Board> boards = boardRepository.findAll(pageable).getContent();
        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for(Board board : boards) {
            boardResDtoList.add(convertEntityToDto(board));
        }
        return boardResDtoList;
    }
    // 게시글 삭제
    public boolean deleteBoard(Long id) {
        if (boardRepository.existsById(id)) { // 게시글 존재 여부 확인
            boardRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // 게시글 수정
    public boolean updateBoard(Long id, BoardReqDto boardReqDto) {
        return boardRepository.findById(id).map(board -> {
            board.setTitle(boardReqDto.getTitle());
            board.setContent(boardReqDto.getContent());
            board.setImgPath(boardReqDto.getImgPath());
            boardRepository.save(board); // 업데이트 (save 메서드는 수정된 내용을 저장)
            return true;
        }).orElse(false); // 게시글이 존재하지 않을 경우 false 반환
    }

    // 게시글 검색(제목과 내용)
    public List<BoardResDto> searchOrBoard(String keyword) {
        List<Board> boards = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        List<BoardResDto> boardResDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardResDtoList.add(convertEntityToDto(board));
        }
        return boardResDtoList;
    }

    private BoardResDto convertEntityToDto(Board board) {
        BoardResDto boardResDto = new BoardResDto();
        boardResDto.setBoardId(board.getId());
        boardResDto.setTitle(board.getTitle());
        boardResDto.setContent(board.getContent());
        boardResDto.setImgPath(board.getImgPath());
        boardResDto.setEmail(board.getMember().getEmail());
        boardResDto.setRegDate(board.getRegDate());
        return boardResDto;
    }
}