package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.MemberDTO;

public interface SocialMemberMapper {

	// 회원가입
	public boolean kakaoSignup(MemberDTO memberDTO) throws Exception;

	// 회원가입 추가 정보
	public void kakaoSignupAddInfo(MemberDTO memberDTO) throws Exception;

	// 아이디 중복 체크
	public int checkId(String id) throws Exception;
	
	// 이메일 중복 체크
	public int checkEmail(String email) throws Exception;
	
	public MemberDTO findById(String id) throws Exception;
	
	public MemberDTO findByEmail(String email) throws Exception;

	public void insert(MemberDTO memberDTO) throws Exception;

} // end interface
