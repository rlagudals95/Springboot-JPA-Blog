package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipaDetaillService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)// 특정 주소로 접근하면 권한 및 인증을 미리체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipaDetaillService principaDetaillService; // 로그인 시 유저네임 db에서 찾고 패스워드 비교
	
	@Bean // ioc가 된다
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder(); // 리턴 값을 스프링이 관리함
	}
	
	// 시큐리티가 대신 로그인 해주는데 password를 가로채기를 한다
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principaDetaillService).passwordEncoder(encodePWD()); // db에서 일치하는 username이 있나 확인후 일치하는 암호화된 패스워드도 있나 확인
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/auth/**") // 여기로 오는 요청은
				.permitAll() // 인증 없이 가능
				.anyRequest() // 이외의 요청은
				.authenticated() // 인증되야해
			.and()
			.formLogin()
				/*
				 * .usernameParameter("username") .passwordParameter("password")
				 */
			.loginProcessingUrl("/auth/login"); // 해당 주소로 요청이 오면 스프링 시큐리티가 로그인을 가로챈다. 대신 로그인
			
	}
}
