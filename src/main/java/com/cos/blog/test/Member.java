package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@RequiredArgsConstructor // fianl이 붙은 것에 생성자 만들어줌
@Data // getter setter 자동생성
//@AllArgsConstructor // 생성자 생성
@NoArgsConstructor // 빈 생성자(파라미터 모두 입력 안해도 됨)
public class Member{
	
	private int id; // final로 불변성 유지
	private String username;
	private String password;
	private String email;
	
	@Builder // Member m =  new Member.builder().username("ssar").password.("1234").email("ssar@naver.com").build();
	// id가 빠져있어 0이 나온다 생성자의 순서를 안지켜도 된다. id, username, password, email
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
};
