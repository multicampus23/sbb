package com.mysit.sbb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	// client ==> controller ==> service ==> Repository 
	
	private final UserRepository userRepository; 
	private final PasswordEncoder passwordEncoder; 
			// SecurityConfig.java 에서 객체 등록됨 
	
	
	// 사용자 정보를 추가하는 메소드 생성 
	
	public SiteUser create(String username, String password, String email ) {
		SiteUser user = new SiteUser(); 
		user.setUsername(username);
		user.setEmail(email);
		
		// password를 DB에 컬럼에 저장할때 암호화 해서 저장 
		// 암호화 방식이 변경되면 문제가 발생될 수 있음 , 
		// 암호화하는 객체 생성은 Spring Security  에서 생성해서 가지고 와서 적용함 .  
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
			
		user.setPassword(passwordEncoder.encode(password));
		
		// DB에 저장 
		userRepository.save(user); 
			
		return user ; 
	}
	
}
