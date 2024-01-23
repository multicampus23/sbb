package com.mysit.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	// @Autowired : 타입으로 객체를 주입함. 동일한 타입이 주입될 수 있다. , JUnit Test 
	
	// 생성자를 통한 객체 주입 : 
	//private final QuestionRepository questionRepository; 
	
	private final QuestionService questionService; 
	
	//http://localhost:8585/question/list
	@GetMapping("/question/list")
	//@ResponseBody
	public String list(Model model) {
		
		//Model : 서버의 데이터를 client view 페이지로 전송 
		// 메소드 인풋 값으로 선언되면 객체가 자동으로 생성됨 
		
		// client 요청에 대한 비즈니스 로직 처리 : question 테이블의 list 를 출력 하라는 요청 
		List<Question> questionList = questionService.getList();  
		
		// model 에 담아서 client view 페이지로 전송 
							
		model.addAttribute("questionList", questionList); 
				
		//templates/question_list.html  
		// thymeleaf 라이브러리 설치시 view 페이지가 위치할 곳, .html 
		return "question_list" ; 	
	}

}
