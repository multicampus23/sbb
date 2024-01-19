package com.mysit.sbb;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question {		

	//@Entity : DB의 테이블과 매핑 
		//Question : 객체명 = 테이블명 
		//  필드 : 테이블의 컬럼과 연결   
	//@Id : 필드위에 할당 , Primary Key (중복된 값을 못 넣도록 설정 ), 테이블에 반드시 1개가 적용  
	//@GeneratedValue : 자동으로 값을 증가해서 생성해줌 , @ID 같이 부여 
	//@Column : 컬럼의 제약 사항을 적용 
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ; 
	
	@Column(length=200)
	private String subject; 
	
	@Column(length=20000)
	private String content; 
	private LocalDateTime createDate; 
	

}
