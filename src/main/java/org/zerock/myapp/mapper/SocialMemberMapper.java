package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.MemberDTO;

public interface SocialMemberMapper {
	
	// 회원가입
	public void kakaoSignup(MemberDTO memberDTO);
	
	// 회원가입 추가 정보
	public void kakaoSignupAddInfo(MemberDTO memberDTO);
	
	public MemberDTO kakaoLogin(MemberDTO memberDTO);
    
} // end interface
