package com.mysit.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
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
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
					; 
							
		return http.build(); 
	}
	
	
}
