package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.SocialMemberDTO;
import org.zerock.myapp.mapper.SocialMemberMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SocialMemberServiceImpl implements SocialMemberService {

	@Autowired
	private SocialMemberMapper socialMemberMapper;
	
	@Override
	public void kakaoSignup(SocialMemberDTO socialMemberDTO) throws Exception {
		socialMemberMapper.kakaoSignup(socialMemberDTO);
		log.trace("kakaoSignup invoked");
		
	} // kakaoSignup
	
	@Override
	public void kakaoSignupAddInfo(SocialMemberDTO socialMemberDTO) throws Exception{
		socialMemberMapper.kakaoSignupAddInfo(socialMemberDTO);
		log.trace("kakaoSignupAddInfo invoked");
		
	} // kakaoSignupAddInfo

	@Override
	public SocialMemberDTO kakaoLogin(SocialMemberDTO socialMemberDTO) throws Exception {
		log.trace("kakaoLogin invoked");
		
		return socialMemberMapper.kakaoLogin(socialMemberDTO);
		
	} // kakaoLogin
	
	
} // end class