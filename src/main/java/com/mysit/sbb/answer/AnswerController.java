package com.mysit.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysit.sbb.question.Question;
import com.mysit.sbb.question.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final AnswerService answerService;
	private final QuestionService questionService; 
	
	
	// 답변 등록 처리 
	@PostMapping ("/answer/create/{id}")
	public String createAnswer(
			Model model, 
			@PathVariable("id") Integer id ,
//			@RequestParam(value="content") String content
			@Valid AnswerForm answerForm, BindingResult bindingResult			
			) {
		
		Question question = questionService.getQuestion(id); 
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("question", question); 
			return "question_detail"; 
			
			// 메세지 출력 안하고 새롭게 리다이렉트로 이동됨 
			//return String.format("redirect:/question/detail/%s", id);  
		}
		
		System.out.println("question id : " + id);
		System.out.println("content : " + answerForm.getContent());
		
		answerService.creatAnswer(id, answerForm.getContent()); 
		
		return String.format("redirect:/question/detail/%s", id) ; 
	}

}
