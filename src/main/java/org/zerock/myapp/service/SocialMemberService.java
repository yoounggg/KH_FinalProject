package org.zerock.myapp.service;

import org.zerock.myapp.domain.MemberDTO;

public interface SocialMemberService {
	// 회원가입 추가 정보
	public void kakaoSignupAddInfo(MemberDTO memberDTO) throws Exception;

	// 회원가입 초기 정보 삽입
	public void insert(MemberDTO memberDTO) throws Exception;

	// 회원인지 확인
	public boolean isMember(MemberDTO memberDTO) throws Exception;
	
	// 중복 확인
	public MemberDTO idEmailCheck(String id, String email) throws Exception;
	
} // end interface