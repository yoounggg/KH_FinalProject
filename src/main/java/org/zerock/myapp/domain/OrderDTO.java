package org.zerock.myapp.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class OrderDTO {
	
	//private String orderId; // 주문번호			-> MYMG_ORDER 테이블의 No 컬럼 인가..?
	private Integer no; // 주문번호			-> MYMG_ORDER 테이블의 No 컬럼 인가..
	private String receiver_name; // 배송 받는사람
	private String member_id; // 주문 회원 아이디
	private Integer product_no;
//	private String memberAddr1; // 우편번호
	private Integer receiver_address1; // 우편번호
	private String receiver_address2; // 회원 주소
	private String receiver_address3; // 회원 상세주소
	private String receiver_tel; // 받는사람 주소
	private String delivery_comment; // 배송요청사항
	private String delivery_state; // 주문 상태
	private List<OrderItemDTO> orders;	// 주문 상품
	private int delivery_cost; // 배송비
//	private Date order_date; // 주문 날짜
	private String order_date;
	
	// DB테이블 존재 하지 않는 데이터

	private int totalPrice;	// 총 가격
	private int orderSalePrice; // 할인된 적용 상품가격
	private int salePrice; // 할인되는 가격
	private int orderFinalSalePrice; // 최종 상품 가격

	// 주문작업에 필요한 데이터 세팅
	public void getOrderPriceInfo() {
		
		// 상품 비용
		for(OrderItemDTO order : orders) {
		    int quantity = order.getCount(); // 상품의 수량
		    int price = order.getPrice(); // 상품의 가격
		    int itemTotalPrice = price * quantity; // 상품의 가격 * 수량
		    totalPrice += itemTotalPrice; // 주문된 상품의 총 가격
		    log.trace("\t+ ******************************totalPrice = {}*********************************", totalPrice);
		}
		
		// 할인적용된 상품 비용
			for(OrderItemDTO order : orders) {
				orderSalePrice += order.getDiscountedPrice();
			}
			//할인되는 가격	
			salePrice = totalPrice - orderSalePrice;
			
		// 배송비
			if(orderSalePrice >= 30000) {
				delivery_cost = 0;
			} else if (orderSalePrice == 0) {       
				delivery_cost = 0;   
	        } else {
	        	delivery_cost = 3000;
			}
			
		// 최종 비용(상품 비용 + 배송비 - 할인가격) 
			orderFinalSalePrice = orderSalePrice + delivery_cost;
	}
	
} // endclass
