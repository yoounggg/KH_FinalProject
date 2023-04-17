package org.zerock.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	
	// 셍나 로그인 테스트용 DTO
	private String id;
	private String password;
//	private String name;
//	private Integer adminCk;

	// rememberMe = check-box 
	// -> check-box가 켜져있는지 꺼져있는지만 보기! = boolean 타입으로
	private Boolean rememberMe;
	
} // end class
