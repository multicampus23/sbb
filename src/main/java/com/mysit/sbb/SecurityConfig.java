package com.mysit.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	/* Spring Boot Security 설정 
		- 클래스 상단에 :  @EnableWebSecurity     : client   <== HTTP ====> Server 
		- 객체가 Spring 에 Bean 등록 되어야 함 : @Configuration
		- 필터 체인 : 
		
		 -- 설정을 하면 톰켓을 리부팅 해야 작동됨 
	*/ 
	
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http
			//http://localhost:8585/	: / 요청은 인증없이 접근 허용 
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
		
			//http://localhost:8585/h2-console  : csrf 보안키 없이도 접근을 허용  
			.csrf( (csrf) -> csrf
					.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))				
					)
			// 웹페이지의 프레임을 사용할 수 있도록 허용 
			.headers( (headers) -> headers
					.addHeaderWriter(new XFrameOptionsHeaderWriter(
							XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))					
					)
			// 인증 처리 시작 <== Controller에서 처리하지 않고 Security 에서 처리함. 
			.formLogin( (formLogin) -> formLogin
					// 로그인 : post 방식으로 cliet가 요청하는 주소 
					// @PostMapping("/user/login") 
					.loginPage("/user/login")
					
					// client 뷰페이지의 name = "username", name="password" 생략 가능 
					//.usernameParameter("username")
					//.passwordParameter("password")
					
					// 로그인 실패시 이동할 페이지
					.failureUrl("/user/error")
					// 로그인 성공시 이동 페이지 
					.defaultSuccessUrl("/")
					
					)
			
			
		; 
							
		return http.build(); 
	}
	
	// 암호화 객체 생성후 Spring 프레임워크에 객체를 등록 함. 
	@Bean
	PasswordEncoder passwordEncoder() {	
		return new BCryptPasswordEncoder(); 
	}
	
	
	
}
