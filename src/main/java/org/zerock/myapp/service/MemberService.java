package org.zerock.myapp.service;

import org.apache.ibatis.annotations.Param;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ServiceException;

public interface MemberService {
	
	// 회원가입 혁규
	public void memberSignup(MemberDTO memberDTO) throws Exception;
	
	// [셍나] 로그인
    public MemberDTO memberLogin(MemberDTO memberDTO) throws ServiceException;
    
    // [셍나] 아이디 찾기 (이름, 전화번호로 회원 정보 존재 확인) - 휴대폰 인증
    public int idCheck(@Param("name") String name, @Param("tel") String tel) throws ServiceException;
    
    // [셍나] 아이디 찾기 (이름, 이메일로 회원 정보 존재 확인) - 이메일 인증
    public int idCheck_e(@Param("name") String name, @Param("email") String email) throws ServiceException;
    
    // [셍나] 비밀번호 변경 - 아이디 조회
    public int idSearch(@Param("id") String id) throws ServiceException;
    
    // 주문자 정보 (찬돌)
    public MemberDTO getMemberInfo(String memberId) throws ServiceException;
	
} // end class
