package com.mysit.sbb;

import java.time.LocalDateTime;

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
	
	@Test
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

}
