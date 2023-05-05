package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.exception.ServiceException;


public interface MypageService {
	
	//1.회원 상세 조회 (매퍼에서, SELECT)
	public abstract MemberDTO getID(String id) throws ServiceException;
	
	// 2. 회원 주문 조회 
	public abstract List<OrderDTO> getOrder(OrderDTO odt) throws ServiceException;
	// OrderDTO의 정보들을 List형태로 받음

} // end class
