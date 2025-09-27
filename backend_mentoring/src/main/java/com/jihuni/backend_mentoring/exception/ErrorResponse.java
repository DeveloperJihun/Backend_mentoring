package com.jihuni.backend_mentoring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime time;  // 발생 시간
    private String status;       // HTTP 상태
    private String message;      // 에러 메시지
    private String requestURI;   // 요청 경로
}
