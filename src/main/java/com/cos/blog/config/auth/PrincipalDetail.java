package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채 로그인 진행을하고 완료가 되면 UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션 저장소에 저장
public class PrincipalDetail implements UserDetails{
	private User user; // 컴포지션 - 객체를 품고 있음 extends로 가져오면 상속
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
	// 계정 만료여부 리턴
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	// 휴면 계정 여부
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	// 비밀번호 만료 리턴
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	// 계정 활성화 여부
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	// 계정 권한 리턴
	// 원래는 권한이 여러개 있을 수 있어서 loop를 돌아야 하는데 일단 한개만!
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { // TODO Auto-generated method stub
		 * return "ROLE_"+user.getRole(); // 스프링 시큐리트 권한 String 포맷이다 지켜주자 } });
		 */
		
		collectors.add(()->{ // GrantedAuthority는 메서드를 하나 가지고 있어 람다식으로
			return "ROLE_"+user.getRole(); 
		});
		
		// TODO Auto-generated method stub
		return collectors;
	}

}
