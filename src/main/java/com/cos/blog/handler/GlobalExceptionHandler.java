package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어디서든 예외가 발생했을 때 여기로 온다
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value= Exception.class)//스프링부트의 예외 처리를 이 함수에 전달
	public String handleArgumentException(Exception e) {
		return "<h1>" + e.getMessage()+ "<h1>";
	}

}