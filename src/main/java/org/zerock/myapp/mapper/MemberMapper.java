package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.MemberVO;

public interface MemberMapper {

	// 회원 가입  혁규
	public void memberSignup(MemberVO memberVO);

	// 로그인
	// MemberVO로 하면 MemberVO 어노테이션 @Value라서 객체 생성이 안댐!
	// DTO 따로 생성해서 @Data 값 주고 사용하기
	public MemberVO memberLogin(LoginDTO loginDTO);
	
	// 주문자 주소 정보 (찬돌)
	public MemberDTO getMemberInfo(String ID); // memberId 가 테이블에 ID로 되어있고 String임
	
	
} // end class
