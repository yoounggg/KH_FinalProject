package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.MemberVO;

public interface MemberMapper {

	// 회원 가입  혁규
	public void memberSignup(MemberVO memberVO);

	// 로그인
	// MemberVO로 하면 MemberVO 어노테이션 @Value라서 객체 생성이 안댐!
	// DTO 따로 생성해서 @Data 값 주고 사용하기
	public LoginDTO memberLogin(LoginDTO loginDTO);
	
	
} // end class
