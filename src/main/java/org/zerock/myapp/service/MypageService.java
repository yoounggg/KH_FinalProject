package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;
import org.zerock.myapp.exception.ServiceException;


public interface MypageService {
	
	//1.회원 상세 조회 (매퍼에서, SELECT)
	public abstract MemberDTO getID(String id) throws ServiceException;
	
	// 2. 회원 주문 조회 
	public abstract List<OrderDTO> getOrder(String member_id) throws ServiceException;
	// OrderDTO의 정보들을 List형태로 받음
	
	// 3. 회원 주문상품 조회 ( 수정 필요, 지금 db에 저장된 모든 데이터 가져옴 )
	public abstract List<OrderItemDTO> getOrderItemDTO(String member_id) throws ServiceException;

} // end class
