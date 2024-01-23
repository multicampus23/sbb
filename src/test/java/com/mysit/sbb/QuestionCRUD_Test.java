package com.mysit.sbb;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionCRUD_Test {
	
	// Question 테이블의 CRUD 
	
	//Question 테이블을 CRUD 하는 레파지토리를 객체 주입 : @Autowired  <== Test
	//QuestionRepository questionRepository = new QuestionRepository();    <== 개발자가 객체생성
	
	@Autowired
	QuestionRepository questionRepository;
	
	// Insert 테스트 
	//@Test
	void jpaInsert() {
		
		Question q1 = new Question(); 
		
		q1.setSubject("백엔드가 무엇인가요?");
		q1.setContent("백엔드가 구체적으로 무엇인가요?");
		q1.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q1); 	//insert 
		
	}
	
	// Update 테스트 : 기존의 레코드를 가지고 오고 setter를 통해서 수정후 저장 
	//@Test
	void jpaUpdate() {
		Optional<Question> op = 
				questionRepository.findById(102); 
		
		Question q = null ; 
		if ( op.isPresent()) {	// op 의 Question 객체를 끄집어 낼때 존재할때 (null 이아닐때) 
			 q = op.get(); 		
		}
		
		// setter를 사용해서 수정할 내용을 필드에 입력 
		q.setSubject("수정된 제목입니다" );
		
		questionRepository.save(q); 			// Update
			
	}
	
	//Delete 테스트 : delete() , delete할 레코드를 가지고 와서 
	@Test
	void jpaDelete() {
		Optional<Question> op = 
				questionRepository.findById(102); 
		
		Question q = null ; 
		if ( op.isPresent()) {	// op 의 Question 객체를 끄집어 낼때 존재할때 (null 이아닐때) 
			 q = op.get(); 		
		}
		
		questionRepository.delete(q);
	}
	
	
	

}
