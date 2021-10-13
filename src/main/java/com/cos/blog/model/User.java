package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // User 클래가 자동으로 MySql에 테이블 생성
@Data
@NoArgsConstructor // bean 생성자
@AllArgsConstructor
@Builder // 빌더 패턴
@DynamicInsert // null 것(role)은 제외하고 insert 그래야 role의 default값(user)이 들어감
public class User {
	
	@Id//Primary key 선언
	@GeneratedValue(strategy = GenerationType.IDENTITY)// jpa 넘버링전략이 아닌 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스 , auto_increment
	
	@Column(nullable = false, length = 30) // column의 조건
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)// 컬럼 기본값설정 "'name'"
	private RoleType role; // Enum을 쓰는게 좋다  admin, user, manager
	
	@CreationTimestamp // 시간 자동입력
	private Timestamp createDate;
	
}
