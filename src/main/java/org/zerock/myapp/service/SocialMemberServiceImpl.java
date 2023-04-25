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
	public boolean kakaoSignup(MemberDTO memberDTO) throws Exception {
		boolean result = false;

//		socialMemberMapper.kakaoSignup(memberDTO);
		log.trace("kakaoSignup invoked");

		// id 중복 체크

		if (socialMemberMapper.findById(memberDTO.getId()) != null) {
			result = true;

		} // if

		// email 중복 체크
		if (socialMemberMapper.findByEmail(memberDTO.getEmail()) != null) {
			result = true;

		} // if

		// 중복된 아이디나 이메일이 없다면 회원가입 수행
		if (!result) {
			socialMemberMapper.insert(memberDTO);
			result = true;
		} // if

		return result;

	} // kakaoSignup

	@Override
	public void kakaoSignupAddInfo(MemberDTO memberDTO) throws Exception {
		log.trace("kakaoSignupAddInfo invoked");

		socialMemberMapper.kakaoSignupAddInfo(memberDTO);
		

	} // kakaoSignupAddInfo

	@Override
	public int checkId(String id) throws Exception {
		log.trace("checkId invoked");

		return socialMemberMapper.checkId(id);
		
	} // checkId

	@Override
	public int checkEmail(String email) throws Exception {
		log.trace("checkEmail invoked");
		
		return socialMemberMapper.checkEmail(email);
		
	} // checkEmail

	@Override
	public MemberDTO findById(String id) throws Exception {
		log.trace("findById invoked");
		
		return socialMemberMapper.findById(id);
		
	} //findById

	@Override
	public MemberDTO findByEmail(String email) throws Exception {
		log.trace("findByEmail invoked");
		
		return socialMemberMapper.findByEmail(email);
		
	} // findByEmail

	@Override
	public void insert(MemberDTO memberDTO) throws Exception {
		log.trace("insert invoked");
		
		socialMemberMapper.insert(memberDTO);
	} // insert
	
} // end class