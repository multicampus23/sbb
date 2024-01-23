package com.mysit.sbb.question;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service		// 빈등록 : 클래스를 객체화 해서 spring framework에 등록  
public class QuestionService {
	// controller ==> service ==> repository 
	
	private final QuestionRepository questionRepository; 
	
	// question테이블의 모든 레코드를 가져와서 리턴 
	public List<Question> getList () {
		return questionRepository.findAll(); 
	}
	
	

}
