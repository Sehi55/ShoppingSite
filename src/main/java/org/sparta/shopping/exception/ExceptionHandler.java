package org.sparta.shopping.exception;

import org.sparta.shopping.dto.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler{

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>> illegalStateHandler(IllegalStateException e){
        return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> illegalArgumentHandler(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> methodArgumentNotValidHandler(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(ApiResponse.fail("유효하지 않는 입력값입니다."));
    }
}
