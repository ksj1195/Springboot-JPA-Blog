package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : com.cos.blog.handler
 * fileName : GlobalExceptionHandler
 * author : 강수정
 * date : 2022-06-06
 * description : Exception 처리 페이지
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-06         강수정          최초 생성
 */

// @ControllerAdvice : 어디에서든 Exception이 발생하면 해당 클래스로 오게 함.
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // IllegalArgumentException이 발생을 하면 Exception에 대한 에러를 해당 함수에 전달.
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseDto<String> handleAgumentException(IllegalArgumentException e) {
        return new ResponseDto<String >(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
