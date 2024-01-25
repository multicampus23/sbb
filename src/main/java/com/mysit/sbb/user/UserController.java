package com.mysit.sbb.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService; 
	
	//회원 가입 폼을 전송 : 주의 : th:object="${userCreateForm}" 
	@GetMapping("/user/signup")
	public String signup (UserCreateForm userCreateForm ) {
		
		return "signup_form"; 
	}
	
	// 회원 가입 정보 전송 
	@PostMapping("/user/signup")
	public String signup (@Valid UserCreateForm userCreateForm, 
			BindingResult bindingResult,			
			Model model) {
		/*
		System.out.println(userCreateForm.getUsername());
		System.out.println(userCreateForm.getPassword1());
		System.out.println(userCreateForm.getPassword2());
		System.out.println(userCreateForm.getEmail());
		*/ 
		
		// 유효성 체크에 오류가 발생 되었을때 signup_form.html 에 그대로 머물면서 오류 코드를 출력 
		if (bindingResult.hasErrors() ) {
			return "signup_form"; 
		}
		
		// password1, password2 필드의 값이 같은지 확인후 같으면 
		if (! userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", 
					"두개의 패스워드가 일치하지 않습니다"); 
			return "signup_form"; 
		}
		
		// 유효성 검증을 통과 하면 DB에 저장 				
		userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(), 
				userCreateForm.getEmail()); 
		
		
		
		// 회원 등록이 완료 되면 "/" 로 이동 
		return "redirect:/" ; 
	}
	
	

}
