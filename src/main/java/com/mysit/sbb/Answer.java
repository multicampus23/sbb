package com.mysit.sbb;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Answer {
	// 답변을 저장하는 테이블 : 질문에 대한 답변을 저장하는 테이블 
	//  Question : 부모 , Answer : 자식 (Question 테이블의 ID 컬럼을 참조해서 답변을 저장 ) 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 20000)
	private String content; 
	
	private LocalDateTime createDate; 	// 컬럼이름 : create_date
	
	//Foreign Key : Answer 테이블은 Question 테이블을 참조함. 
	// @MayToOne : JPA에서 테이블 간의 관계 설정 , 자식 테이블(Answer)에서 부모 테이블(Question)을 참조 
	// question 컬럼은 Question 테이블의 Primary Key 의 값이 들어옴 
	// question 컬럼이름이 question_id 로 이름이 바뀜. 
	@ManyToOne
	private Question question ; 

}
