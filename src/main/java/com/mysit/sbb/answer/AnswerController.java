package com.mysit.sbb.answer;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysit.sbb.question.Question;
import com.mysit.sbb.question.QuestionService;
import com.mysit.sbb.user.SiteUser;
import com.mysit.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final AnswerService answerService;
	private final QuestionService questionService; 
	private final UserService userService; 
	
	
	// 답변 등록 처리
	@PreAuthorize("isAuthenticated()")
	@PostMapping ("/answer/create/{id}")
	public String createAnswer(
			Model model, 
			@PathVariable("id") Integer id ,
//			@RequestParam(value="content") String content
			@Valid AnswerForm answerForm, BindingResult bindingResult, 
			Principal principal
			) {
		
		//뷰에서 인증된 사용자 정보를 가지고 오는 객체
		// 인증된 계정 정보가 출력 
		//System.out.println("뷰에서 인증된 계정 정보를 출력 : " + principal.getName());
		
		
		Question question = questionService.getQuestion(id); 
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("question", question); 
			return "question_detail"; 
			
			// 메세지 출력 안하고 새롭게 리다이렉트로 이동됨 
			//return String.format("redirect:/question/detail/%s", id);  
		}
		
		System.out.println("question id : " + id);
		System.out.println("content : " + answerForm.getContent());
		
		// principal.getName() : 현재로그인 한 사용자 정보가 넘어옴. 
		// 수정 추가됨 
		SiteUser siteUser = userService.getUser( principal.getName() ) ; 
		
		// 수정됨 
		answerService.creatAnswer(id, answerForm.getContent() , siteUser); 
		
		return String.format("redirect:/question/detail/%s", id) ; 
	}
	
	// 답변을 수정할 수 있는 뷰 페이지전송 
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/answer/modify/{id}")
	public String answerModify(
			AnswerForm answerForm, 
			@PathVariable("id") Integer id, 
			Principal principal
			) {
		// id 갑에 해당하는 answer 객체를 찾아온다. 
		Answer answer = 
				answerService.getAnswer(id); 
		
		// 현재 로그온한 사용자가 자신이 작성한 답변이 아닌 경우 예외(오류)발생 
		// 자신이 작성한 글이 아닐 경우 : 강제 오류 발생  
		if ( ! principal.getName().equals(answer.getAuthor().getUsername())) {
			// 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "당신은 수정권한이 없습니다.");
		}
		
		answerForm.setContent(answer.getContent()); 
		
		return "answer_form" ; 
	}
	
	
	// 수정 폼에서 넘어오는 값을 받아서 저장 
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/answer/modify/{id}")
	public String answerModify(
			@PathVariable("id") Integer id , 
			AnswerForm answerForm, 
			Principal principal
			) {
		
		Answer answer = 
				answerService.getAnswer(id); 
		
		//수정 완료 
		answerService.modify(answer, answerForm.getContent()); 
			
		//수정 완료후 이동 페이지 
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId()) ; 
	}
	
	// answer 삭제
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/answer/delete/{id}")
	public String answerDelete(
			@PathVariable("id") Integer id , 
			Principal principal 			
			) {
		// id 로 Answer 객체를 반환 
		Answer answer = answerService.getAnswer(id); 
		
		// 현재 로그온한 계정이 DB에 저장된 계정과 동일할 경우 삭제 
		if ( ! principal.getName().equals(answer.getAuthor().getUsername()) ) {
			// 강제로 오류 발생 : 예외 처리 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"); 			
		}
		
		
		// 삭제 
		answerService.delete(answer); 
		
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId()) ; 
	}
	
	
	
	

}
