package com.mysit.sbb;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {
	
	//객체를 DI (주입) : 객체를 Spring Framework에 등록
	// 객체 생성시 : A a = new A(); 
	// @Autowired  <== Test 코드에서 주로 사용 
	
	@Autowired
	private QuestionRepository questionRepository ; 
	
	
	// Insert Test 
	//@Test
	void jpaInsertTest() {
	
		Question q1 = new Question(); 
		q1.setSubject("JPA 가 무엇인가요?");
		q1.setContent("JPA 가 구체적으로 무엇인지 알고 싶어요");
		q1.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q1); 		// DB에 저장됨 
		
		Question q2 = new Question(); 
		q2.setSubject("스프링 부트 가 무엇인가요?");
		q2.setContent("스프링 부트 가 구체적으로 무엇인지 알고 싶어요");
		q2.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q2); 
	}
	
	//@Test
	void jpaSelectListTest() {
		
		// 모든 레코드 
		List<Question> q = this.questionRepository.findAll(); 
		
		Question q1 = q.get(0); 
		Question q2 = q.get(1); 
		System.out.println(q1.getSubject());
		System.out.println(q2.getSubject());
				
		// assertEquals (기대치, 값);     기대치 = 값    같을때 정상 
		assertEquals (2, q.size()); 
		assertEquals ("JPA 가 무엇인가요?", q1.getSubject()); 
	
	}
	
	//@Test
	void jpaSelectTest () {
		
		//question 테이블의 레코드 1개만 가져옴
		// Optional 은 null 처리를 아주 쉽게해줌 
			// isPresent() : 값이 존재하면 true
			// isEmpty () : 값이 존재하지 않으면 true		
		// select * from question where id = 1 ; 
		Optional<Question> op = questionRepository.findById(1); 
		
		if ( op.isPresent()) {
			Question q = op.get();   // Null이 아닐때 Optional 내부의 Question 객체를 끄집어냄. 
			
			System.out.println("글 내용 출력 : " + q.getContent());
			System.out.println("글 제목 출력 : " + q.getSubject());
			assertEquals ("JPA 가 무엇인가요?", q.getSubject()); 
		}
			
	}
	
	//@Test
	void jpaSelectSubject() {
		List<Question> list = questionRepository.findBySubject("JPA 가 무엇인가요?"); 

		if ( list != null) {
			Question q = list.get(0); 		
			System.out.println(q.getSubject());			
			assertEquals ("JPA 가 무엇인가요?", q.getSubject() ) ; 
		}
	}
	
	//@Test
	void jpaSelectContent() {
		
		List<Question> list = 
				questionRepository.findByContent("스프링 부트 가 구체적으로 무엇인지 알고 싶어요");
		
		if ( list != null) {
			Question q = list.get(0); 
			
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
			
			assertEquals ("스프링 부트 가 구체적으로 무엇인지 알고 싶어요", q.getContent()); 
			
		}
		
	}
	
	@Test
	void jpaSelectSubjectLike() {
		
		//select * from question where subject like '%?%'; 
		List<Question> list = 
				questionRepository.findBySubjectLike("%JPA%"); 
		
		if ( list != null) {
			Question q = list.get(0); 
			
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
			
			assertEquals ("JPA 가 무엇인가요?", q.getSubject()); 
			
		}
	}
	
	

}
