package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor // bean 생성자
@AllArgsConstructor
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
	
	@ManyToOne(fetch = FetchType.EAGER) 
	// EAGE전략 1:1의 정보 무조건 들고와야하는 정보 
	// ManyToOne = Board, User = One 한명의 유저는 여러 게시글 작성할 수 있음을 선언! 
	@JoinColumn(name="userId")
	// userId라는 key로 들어감
	private User user; // DB는 오브젝트 저장불가 Foreign key, 자바는 오브젝트를 저장할 수 있다. 자바는 DB에 맞춰 key값을 
	
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER)
	// 댓글 펼치기 버튼이 있이 있으면 LAZY전략 > 없으면 데이터를 안들고옴
	// mappedBy가 있으면 연관관계의 주인이 아니다 db에 컬럼을 만들지 말라고 선언 
	// fk는 reply테이블의 board
	// board를 select 할때 join문을 통해 값을 얻기 위해 있다
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
