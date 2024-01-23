package com.mysit.sbb;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerCRUDTest {
	// Question : 부모 테이블 
	// Answer : 자식 테이블
	
	@Autowired
	QuestionRepository questionRepository; 
	@Autowired
	AnswerRepository answerRepository; 
	
	@Test
	void jpaInsert() {
		Optional<Question> op = questionRepository.findById(152); 
		Question q = null ; 
		if ( op.isPresent()) {
			q = op.get(); 
		}
		
		Answer a1 = new Answer() ; 
		a1.setContent("보이지않는 비즈니스 로직을 처리하는함"); 
		a1.setCreateDate(LocalDateTime.now());
		a1.setQuestion(q);     // 주의
		
		answerRepository.save(a1); 
		
		Answer a2 = new Answer() ; 
		a2.setContent("Spring boot"); 
		a2.setCreateDate(LocalDateTime.now());
		a2.setQuestion(q);     // 주의
		
		answerRepository.save(a2); 
		
		Answer a3 = new Answer() ; 
		a3.setContent("DJango"); 
		a3.setCreateDate(LocalDateTime.now());
		a3.setQuestion(q);     // 주의
		
		answerRepository.save(a3); 
		
	}
}
