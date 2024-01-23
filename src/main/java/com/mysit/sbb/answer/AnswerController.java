package com.mysit.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final AnswerService answerService; 
	
	// 답변 등록 처리 
	@PostMapping ("/answer/create/{id}")
	public String createAnswer(
			Model model, 
			@PathVariable("id") Integer id ,
			@RequestParam(value="content") String content
			) {
		
		System.out.println("question id : " + id);
		System.out.println("content : " + content);
		
		answerService.creatAnswer(id, content); 
		
		return String.format("redirect:/question/detail/%s", id) ; 
	}

}
