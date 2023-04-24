package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.OrderItemDTO;
import org.zerock.myapp.domain.OrderPageItemDTO;

public interface OrderMapper {
	
	// 상품정보를 얻기 위해 db에 요청
	public OrderPageItemDTO getProductsInfo(Integer productId);
	
	// 주문 상품 정보 담는 mapper(주문처리) // 주문상품DTO에서 가져옴
	public OrderItemDTO getOrderInfo(Integer productId);

}
