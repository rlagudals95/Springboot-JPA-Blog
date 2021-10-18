package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)// 특정 주소로 접근하면 권한 및 인증을 미리체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean // ioc가 된다
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder(); // 리턴 값을 스프링이 관리함
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/auth/**") // 여기로 오는 요청은
				.permitAll() // 인증 없이 가능
				.anyRequest() // 이외의 요청은
				.authenticated() // 인증되야해
			.and()
			.csrf().disable();
				
		
	}
}
