package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// db에 insert
// Mybatis Dao와 비슷..
// 자동으로 bean으로 등록 @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> { // jpa 레포는 user테이블은 관리하고 pk는 integer 숫자다
	
}
