package com.mysit.sbb.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	// Spring Security 에서 인증을 처리하는 서비스 
	
	private final UserRepository userRepository; 
	
	// UserDetailsService 인터페이스에서 선언된 메소드를 구현하겠다. 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		///user/login 요청을 Spring Security 에서 필터링 하고, 
		// 메소드의 username 을 매개변수로 입력 
		
		//System.out.println("Spring Surity 에서 넘어오는 Username : " + username);
		
		
		Optional <SiteUser> _siteUser = 
				userRepository.findByusername(username); 
		
		// _sitUser 에 객체가 존재하지 않는 경우 : DB에 사용자 이름이 존재하지 않는 경우
		if ( _siteUser.isEmpty()) {
			
			// 강제로 예외를 발생 시킴 
				// throws : 예외를 호출하는 곳으로 미루기  
				// throw : 예외를 강제로 발생 시킴 
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다."); 	
		}
		
		// 사용자가 존재하는 경우 : Optional 에 저장된 SiteUser객체를 끄집어 냄 
		// siteUser.getUsername 
		SiteUser siteUser = _siteUser.get(); 
		/*
		System.out.println("사용자 ID : " + siteUser.getUsername());
		System.out.println("암호 : " + siteUser.getPassword());
		System.out.println("메일주소 : " + siteUser.getEmail());
		*/
		
		// 인증되면 사용자 세션(서버의 RAM)에 권한을 부여함. 
		
		// 권한을 부여하는 객체를 저장하는 List 생성 
		// GrantedAuthority : 세션에 적용할 권한을 넣어줌 
		List<GrantedAuthority> authorities = new ArrayList<>(); 
		
		// 조건을 부여해서 Site_User 테이블의 username 필드의 값이 admin 라면 세션에 
		//     ADMIN("ROLE_ADMIN") 
		if ( "admin".equals(username) ) {
			// ADMIN 부여하고
			authorities.add (new SimpleGrantedAuthority(UserRole.ADMIN.getValue()) ); 
			
		}else {
			// USER 부여함. 
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()) ); 
		}
		
		return new User(siteUser.getUsername(), siteUser.getPassword(),authorities);
	}

}
