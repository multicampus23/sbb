package com.mysit.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service		// 빈등록 : 클래스를 객체화 해서 spring framework에 등록  
public class QuestionService {
	// controller ==> service ==> repository 
	
	private final QuestionRepository questionRepository; 
	
	// question테이블의 모든 레코드를 가져와서 리턴 
	// 리스트 페이지 
	public List<Question> getList () {
		return questionRepository.findAll(); 
	}
	
	//상세 페이지 
	public Question getQuestion(Integer id) {
		Optional<Question> op = 
				questionRepository.findById(id); 	
		return op.get(); 
	}
	
	// question 테이블에 값 insert 
	public void create(String subject, String content) {
		
		Question q = new Question(); 
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q); 
		
	}
	

}
