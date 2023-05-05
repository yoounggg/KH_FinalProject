package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.MemberDTO;

public interface MypageMapper {

	// 매핑을 위해서 id 가져오기
	public MemberDTO getID(String id);
	
} // end interface
