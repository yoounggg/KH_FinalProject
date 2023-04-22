package org.zerock.myapp.service;

import org.zerock.myapp.domain.SocialMemberDTO;

public interface SocialMemberService {
	
	public void kakaoSignup(SocialMemberDTO socialMemberDTO) throws Exception;
	
	public SocialMemberDTO kakaoLogin(SocialMemberDTO socialMemberDTO) throws Exception;
	
} // end interface