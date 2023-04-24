package org.zerock.myapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Data
public class OrderItemDTO {
	// product 테이블의 DTO??    // 하나의 주문상품정보를 담음
	private String orderId; // 주문번호
	private Integer productId; // 상품 번호
	private Integer productCount; // 주문 수량
	private Integer no; // MYMG_ORDER (PK) 이 테이블이 맞는지 확실하지 않음 확인 필요
	private Integer price; // 상품 한개 가격 - OrderPageItemDTO클래스와 맞춰봄
	private Integer discount;
	
	// DB 테이블에 존재하지 않는 데이터
	private Integer salePrice; // 할인되는 가격 
	private Integer discountedPrice; // OrderPageItemDTO와 이름 맞춤 -> 할인 적용된 가격
	private Integer totalPrice; // 총가격 (할인 적용된 가격 * 주문 수량)
	
	public void initSaleTotal() {
		this.discountedPrice = (int) (totalPrice * (1 - this.discount / 100.0));
		this.salePrice = totalPrice - discountedPrice;
		this.totalPrice = this.productCount * this.discountedPrice;
	}

	public String toString() {
		return "OrderItemDTO [orderId=" + orderId + ", productId=" + productId + " productCount=" + productCount + ", orderItemId="
				+ no + ", price=" + price + ", discount=" + discount + ", salePrice=" + salePrice + ", discountedPrice=" + discountedPrice + ", totalPrice="
				+ totalPrice + "]";
	} // toString
	
} // endclass
