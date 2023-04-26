package org.zerock.myapp.service;

import org.zerock.myapp.domain.MemberDTO;

public interface SocialMemberService {
	
	// 회원가입
	public void kakaoSignup(MemberDTO memberDTO) throws Exception;
	
	// 회원가입 추가 정보
	public void kakaoSignupAddInfo(MemberDTO memberDTO) throws Exception;
	
	public MemberDTO kakaoLogin(MemberDTO memberDTO) throws Exception;
	
} // end interface