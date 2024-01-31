package com.mysit.sbb.question;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Question_Select_Test2 {

	@Autowired
	private QuestionRepository questionRepository; 
	
	@Test
	void question_Select_Test2() {
		// 모든 레코드 출력 
		List<Question> list = 
				questionRepository.findAll();
		
		//for 문을 사용해서 list 의 담긴 Question 객체를 끄집어 내서 출력 
		for ( int i = 0 ; i < list.size() ; i++) {
			System.out.println(list.get(i).getId());
			System.out.println(list.get(i).getSubject());
			System.out.println(list.get(i).getContent());
			System.out.println();		
		}
			
	}
}
