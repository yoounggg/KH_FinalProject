package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.MemberVO;

public interface UserInfoMapper {

	//memberVO, memberDTO 사용
	
	//1. 회원 상세 조회
	public abstract MemberDTO select(String id);
	
	//2. 회원 정보 수정
	public abstract Integer update(MemberDTO dto);
	
	//3. 회원 삭제 == 탈퇴
	public abstract Integer delete(String id);
	
	
} // end interface
