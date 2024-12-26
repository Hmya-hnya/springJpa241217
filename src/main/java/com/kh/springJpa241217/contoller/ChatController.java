package com.kh.springJpa241217.contoller;

import com.kh.springJpa241217.dto.ChatRoomReqDto;
import com.kh.springJpa241217.dto.ChatRoomResDto;
import com.kh.springJpa241217.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:8111")
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    // 채팅방 개설
    @PostMapping("/new")
    public ResponseEntity<String> createRoom(@RequestBody ChatRoomReqDto chatRoomDto) {
        log.info("charRoomDto: {}", chatRoomDto);
        ChatRoomResDto chatRoomResDto = chatService.createRoom(chatRoomDto.getName());
        return ResponseEntity.ok(chatRoomResDto.getRoomId());
    }
    // 채팅방 개설 목록을 전달
    @GetMapping("/list")
    public List<ChatRoomResDto> findAllRoom() {
        return chatService.findAllRoom();
    }
    // 특정 아이디로 개설된 채팅방 목록
    @GetMapping("/room/{roomId}")
    public ChatRoomResDto findRoomById(@PathVariable String roomId){
        return chatService.findRoomById(roomId);
    }
}