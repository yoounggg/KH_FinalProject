package org.zerock.myapp.service;

import org.zerock.myapp.domain.SocialMemberDTO;

public interface SocialMemberService {
	
	// 회원가입
	public void kakaoSignup(SocialMemberDTO socialMemberDTO) throws Exception;
	
	// 회원가입 추가 정보
	public void kakaoSignupAddInfo(SocialMemberDTO socialMemberDTO) throws Exception;
	
	public SocialMemberDTO kakaoLogin(SocialMemberDTO socialMemberDTO) throws Exception;
	
} // end interface