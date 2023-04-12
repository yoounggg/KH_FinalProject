package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class LoginDTO {
	
	// 셍나 로그인 테스트용 DTO
	private String id;
	private String password;

	// remeberMe = check-box 
	// -> check-box가 켜져있는지 꺼져있는지만 보기! = boolean 타입으로
	private Boolean rememberMe;
	
} // end class
