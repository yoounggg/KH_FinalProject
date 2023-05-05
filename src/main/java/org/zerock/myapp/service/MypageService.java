package org.zerock.myapp.service;

import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ServiceException;


public interface MypageService {
	
	//1.회원 상세 조회 (매퍼에서, SELECT)
	public abstract MemberDTO getID(String id) throws ServiceException;

} // end class
