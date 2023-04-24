package org.zerock.myapp.service;

import org.apache.ibatis.annotations.Param;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ServiceException;

public interface MemberService {
	
	// 회원가입 혁규
	public void memberSignup(MemberDTO memberDTO) throws Exception;
	
	// 로그인 셍나
    public MemberDTO memberLogin(MemberDTO memberDTO) throws ServiceException;
    
    // 휴대폰 인증 - 아이디 찾기 셍나
    public int idCheck(@Param("name") String name, @Param("tel") String tel) throws ServiceException;
    
    // 주문자 정보 (찬돌)
    public MemberDTO getMemberInfo(String memberId) throws ServiceException;
	
} // end class
