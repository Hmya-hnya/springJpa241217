package com.kh.springJpa241217.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class TokenDto {
    private String grantType;   // 인증방식
    private String accessToken; // 엑세스 토큰
    private Long accessTokenExpiresIn;    // 엑세스 토큰 만료시간
    private String refreshToken;    // 리플레시 토큰
    private Long refreshToKenExpiresIn; // 리플레시 토큰 만료시간
}
