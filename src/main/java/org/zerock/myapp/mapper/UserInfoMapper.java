package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.MemberDTO;

public interface UserInfoMapper {

	//memberVO, memberDTO 사용
	
	//1. 비밀번호 수정
	public abstract Integer changePw(MemberDTO dto);
	
	
	
} // end interface
