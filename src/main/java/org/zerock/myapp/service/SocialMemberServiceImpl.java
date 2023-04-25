package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.mapper.SocialMemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SocialMemberServiceImpl implements SocialMemberService {

	@Autowired
	private SocialMemberMapper socialMemberMapper;
	
	@Override
	public void kakaoSignup(MemberDTO memberDTO) throws Exception {
		socialMemberMapper.kakaoSignup(memberDTO);
		log.trace("kakaoSignup invoked");
		
	} // kakaoSignup
	
	@Override
	public void kakaoSignupAddInfo(MemberDTO socialMemberDTO) throws Exception{
		socialMemberMapper.kakaoSignupAddInfo(socialMemberDTO);
		log.trace("kakaoSignupAddInfo invoked");
		
	} // kakaoSignupAddInfo

	@Override
	public MemberDTO kakaoLogin(MemberDTO socialMemberDTO) throws Exception {
		log.trace("kakaoLogin invoked");
		
		return socialMemberMapper.kakaoLogin(socialMemberDTO);
		
	} // kakaoLogin
	
	
} // end class