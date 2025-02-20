package com.kh.springJpa241217.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// 채팅방을 관리하는 Dto
@Getter @Setter
@Slf4j
@NoArgsConstructor
@ToString
public class ChatRoomResDto {
    private String roomId;  // 채팅방 Id
    private String name;    // 채팅방 이름
    private LocalDateTime regDate;  // 채팅방 생성 시간
    @JsonIgnore // 직렬화 방지
    private Set<WebSocketSession> sessions; // 채팅방에 입장한 세션 정보를 담음
    // 채팅방에 포함된 세션이 비어있는지 확인
    public boolean isSessionEmpty() {
        return this.sessions.isEmpty();
    }
    @Builder    // 빌더 패턴 적용
    public ChatRoomResDto(String roomId, String name, LocalDateTime regDate) {
        this.roomId = roomId;
        this.name = name;
        this.regDate = regDate;
        // 동시성 문제를 해결하기 위해 ConcurrentHashMap 사용
        this.sessions = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }
}