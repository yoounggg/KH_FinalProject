package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.SocialMemberDTO;

public interface SocialMemberMapper {
	
	// 회원가입
	public void kakaoSignup(SocialMemberDTO socialMemberDTO);
	
	// 회원가입 추가 정보
	public void kakaoSignupAddInfo(SocialMemberDTO socialMemberDTO);
	
	public SocialMemberDTO kakaoLogin(SocialMemberDTO socialMemberDTO);
    
} // end interface
