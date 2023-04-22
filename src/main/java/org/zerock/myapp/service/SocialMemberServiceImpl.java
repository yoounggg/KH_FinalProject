package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.SocialMemberDTO;
import org.zerock.myapp.mapper.SocialMemberMapper;

@Service
public class SocialMemberServiceImpl implements SocialMemberService {

	@Autowired
	private SocialMemberMapper socialMemberMapper;
	
	@Transactional
	@Override
	public void kakaoSignup(SocialMemberDTO socialMemberDTO) throws Exception {
		socialMemberMapper.kakaoSignup(socialMemberDTO);
	} // kakaoSignup
	
	@Transactional(readOnly=true)
	@Override
	public SocialMemberDTO kakaoLogin(SocialMemberDTO socialMemberDTO) throws Exception {
		return socialMemberMapper.kakaoLogin(socialMemberDTO);
	} // kakaoLogin
	
} // end class