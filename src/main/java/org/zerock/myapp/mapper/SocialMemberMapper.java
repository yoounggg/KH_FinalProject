package org.zerock.myapp.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.myapp.domain.MemberDTO;

public interface SocialMemberMapper {
	
//	[혁규] 카카오 -------------------------------------------------------------
	
	// 회원가입 추가 정보
	public void kakaoSignupAddInfo(MemberDTO memberDTO) throws Exception;

	// 회원가입 초기 정보 삽입
	public void insert(MemberDTO memberDTO) throws Exception;

	// 회원인지 확인
	public boolean isMember(MemberDTO memberDTO) throws Exception;

	// 중복 확인
	public MemberDTO idEmailCheck(String id, String email) throws Exception;
	
//	[셍나] 네이버 -------------------------------------------------------------

	// 회원 조회 #1
	public MemberDTO socialMemberCheck(@Param("id") String id, @Param("email") String email) throws Exception;
	
	// 회원 조회 #2
	public boolean searchMember(MemberDTO memberDTO) throws Exception;
	
	// 회원 가입
	public void insertNaverMember(MemberDTO memberDTO) throws Exception;
	
	// 회원 가입 추가 정보
	public void naverSignupAddInfo(MemberDTO memberDTO) throws Exception;

} // end interface
