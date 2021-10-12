package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100) 
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	@ColumnDefault("0")//조회수
	private int count;
	
	@ManyToOne // Manu = Board, User = One 한명의 유저는 여러 게시글 작성할 수 있음을 선언! 
	@JoinColumn(name="userId") // userId라는 key로 들어감
	private User user; // DB는 오브젝트 저장불가 Foreign key, 자바는 오브젝트를 저장할 수 있다. 자바는 DB에 맞춰 key값을 
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
