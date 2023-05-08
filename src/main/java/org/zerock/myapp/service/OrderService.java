package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderPageItemDTO;
import org.zerock.myapp.exception.ServiceException;

public interface OrderService {
	
	// 주문정보
	public List<OrderPageItemDTO> getProductsInfo(List<OrderPageItemDTO> orders) throws ServiceException;
	// 주문페이지에서 상품을 조회하려는 인터페이스 
	// OrderPageItemDTO의 정보들을 List형태로 받음
	// 조회된 상품 정보를 OrderPageItemDTO 객체로 매핑하여 리스트 형태로 반환

	// 주문
	public void order(OrderDTO odt);
	// 주문한 상품 정보
//	public List<OrderItemDTO> getOrdersInfo(List<OrderItemDTO> ordersInfo) throws ServiceException;
	
	// 주문가격 주문내역에 보내주기
//	public List<OrderPageItemDTO> orderList(List<OrderPageItemDTO> orders) throws ServiceException;
	
	// 주문한 멤버 정보

	
}