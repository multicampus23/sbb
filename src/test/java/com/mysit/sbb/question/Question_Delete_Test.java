package com.mysit.sbb.question;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Question_Delete_Test {
	
	@Autowired
	private QuestionRepository questionRepository; 
	
	@Test
	void Question_Delete_Test() {
		
		Optional <Question> op = 
				questionRepository.findById(2302); 
		
		Question q = op.get(); 
		
		questionRepository.delete(op.get());
		
	}

}
