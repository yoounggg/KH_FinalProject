package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;

public interface MypageMapper {

	// 매핑을 위해서 id 가져오기
	public MemberDTO getID(String id);
	
	// 주문 가져오기
	public List<OrderDTO> getOrder(String member_id);
	
	// 주문상품 정보 가져오기 ( 수정 필요 )
	public List<OrderItemDTO> getOrderItem(String member_id);
	
	// 주문 상품정보 가져오기
//	public OrderDTO getOrderItemDTO(OrderItemDTO oit);
	
} // end interface
