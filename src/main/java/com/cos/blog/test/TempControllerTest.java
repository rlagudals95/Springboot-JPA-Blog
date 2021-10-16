package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller// Controller는 file return RestController는 데이터 return
public class TempControllerTest {
	
	
	
	@GetMapping("/temp/home")
	public String tempHome() {
		// 파일리턴 기본경로 : src/main/resource/static
		// 리턴명 : /home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp") // jsp는 static 파일이 아니여서 인식못함
	public String tempJsp() {
		// 파일리턴 기본경로 : src/main/resource/static
		// 리턴명 : /home.html
		return "/test.jsp";
	}
	
	@GetMapping("/temp/jsp2") // jsp는 static 파일이 아니여서 인식못함
	public String tempJsp2() {
		// prefix : WEB-INF/views
		// suffix : .jsp
		// /WEB-INF/views//test.jsp
		return "test";
	}
}
