package com.cos.blog.test;


import java.awt.print.Pageable;
import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest { // 1. 스프링 메모리로 뜰때
	
	@Autowired // 2. 얘도 같이뜬다 의존성 주입 DI
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try { // 먼저 삭제하는 user가 있는지 시도
			userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e){
			return "해당 id 는 db에 존재하지 않습니다.";
		}
		
			
		return "삭제되었습니다. id : " + id;
	}
	
	
	// email, pw
	// json 데이터를 받기 위해 @RequestBody 필요
	@PutMapping("/dummy/user/save/{id}")
	public User updateUser (@PathVariable int id, @RequestBody User requestUser) { // json데이터를 => java object로 변환해서 받음
		
		System.out.println("id"+id);
		System.out.println("password"+requestUser.getPassword());
		System.out.println("email"+requestUser.getEmail());
		
		// 수정하기 전에 id로 user를 찾는다
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정실패");
		});
		
		// 받아온 데이터로 set
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// 이렇게 해줘야 null 값인 부분은 빼고 update(save)실행
		// save함수는 id를 전달하지 않으면 insert
		// id를 전달해주고 해당 데이터가 있으면 update 
		userRepository.save(user);
		return user;
	}
	
	// update하는 다른방법
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser2 (@PathVariable int id, @RequestBody User requestUser) { // json데이터를 => java object로 변환해서 받음
		
		System.out.println("id"+id);
		System.out.println("password"+requestUser.getPassword());
		System.out.println("email"+requestUser.getEmail());
		
		// 수정하기 전에 id로 user를 찾는다
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정실패");
		});
		
		// 받아온 데이터로 set
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
				
		return null;
	}
	
	// 전체유저
	@GetMapping("/dummy/users")
	public List<User> list(){ 
		
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터 리턴받기
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size= 2, sort="id", direction = Sort.Direction.DESC) Sort pageable){
		// id를 desc로 정렬해 데이터를 가져옴
		//List<User> user = userRepository.findAll();
		Page<User> PagingUser = (Page<User>) userRepository.findAll(pageable);
		
		List<User> users = PagingUser.getContent();
		return users;
	}
	
	// {id} 주소로 파라미터 전달
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		User user = userRepository.findById(id).orElseThrow(()-> { // 람다식
			return new IllegalArgumentException("해당 유저는 찾을 수 없습니다. id :" + id);
		});  // id가 일치하는 테이블이 없을 경우대비
		
		// 스프링부트는 자바 오브젝트를 리턴하게되면 MessageConverter가 jackson이라는 라이브러리를 호출해서 객체를 json으로 변환해서 던져준다.
		
		return  user;
	}
	
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
