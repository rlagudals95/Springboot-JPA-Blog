package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest { // 1. 스프링 메모리로 뜰때
	
	@Autowired // 2. 얘도 같이뜬다 의존성 주입 DI
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(User user) { // object로 받을 수 있다
		System.out.println("id : "+ user.getId());
		System.out.println("role : "+ user.getRole());
		System.out.println("createDate : "+ user.getCreateDate());
		System.out.println("password : "+ user.getPassword());
		System.out.println("email : "+ user.getEmail());
	
		user.setRole(RoleType.USER); // Enum 값을 디폴트로 가져옴
		userRepository.save(user); // insert
		return "회원가입이 완료 되었습니다!";
	}
}
