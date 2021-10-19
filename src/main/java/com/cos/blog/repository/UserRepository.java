package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// db에 insert
// Mybatis Dao와 비슷..
// 자동으로 bean으로 등록 @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> { // jpa 레포는 user테이블은 관리하고 pk는 integer 숫자다
	
	// select * from user where username = ?;
	Optional<User> findByUsername(String username);
	
	// jpa naming 쿼리전략
	// select * from user where username = ? and password = ? 적용됨
	// findBy뒤에 대문자와 And를 기점으로 위와같이 select 문이 만들어짐
	//User findByUsernameAndPassword(String username, String password);
	
	//@Query(value= "select * from user where username = ? and password = ?", nativeQuery= true  ) //이게 있으면 이렇게 작성해도 됨 mybatis가 필요있나?
	//User login(String username, String password);
	
}



