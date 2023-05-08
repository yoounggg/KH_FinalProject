package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;
import org.zerock.myapp.domain.ProductDTO;

public interface MypageMapper {

	// 매핑을 위해서 id 가져오기
	public MemberDTO getID(String id);
	
	// 주문 가져오기
	public List<OrderDTO> getOrder(String member_id);
	
	// 주문상품 정보 가져오기 ( 수정 필요, 지금 db에 저장된 모든 데이터 가져옴 )
	public List<OrderItemDTO> getOrderItem(String member_id);
	
	// 주문 상세 조회 (상품 상세 정보에서 해당 주문번호의 '정보'만 가져오기 위함)
	public abstract OrderDTO select(Integer no);
	
	// 주문상품 상세 조회 ( 상품 상세 정보에서 해당 주문번호의 '상품정보'만 가져오기 위함 )
	public abstract List<OrderItemDTO> ItemSelect(Integer order_no);
	
	// 조인으로 상품 이름 가져오기 
	public abstract List<ProductDTO> productName(Integer Product_no);
	
	// Order_List테이블의 수량,가격,할인가 가져오기
	public List<OrderItemDTO> ItemPrice(Integer order_no);
	
	// 주문 상품정보 가져오기
//	public OrderDTO getOrderItemDTO(OrderItemDTO oit);
	
} // end interface
