package org.zerock.myapp.domain;

import lombok.Data;


@Data
public class OrderPageItemDTO {
	
	/* view로부터 전달받을 값 */
//	private String productId; // 이거 productId로 해야할지.. No로 해야할지..? -> View에서 오는값이니 productId해도될듯
	private String productId; // 상품테이블에 Pk가 Number라서 Integer로 함
	
	private Integer productCount;

	/* productId를 통해 DB에서 꺼내올 데이터 */
	private String Name;			// 상품이름 productName
	
	private Integer Price;			// 상품가격 productPrice
	
	private double Discount;		// 할인 productDiscount
	
	/* 만들어 낼 값 */
	private Integer salePrice; 			// 할인 가격
	
	private Integer totalPrice;			// 총 가격
	
	public void initSaleTotal() {
		this.salePrice = (int) (this.Price * (1 - this.Discount));
		this.totalPrice = this.salePrice * this.productCount;
	} //initSaleTotal
	
	@Override
	public String toString() {
		return "OrderPageItemDTO [productId=" + productId + ", productCount=" + productCount + ", productName=" + Name
				+ ", productPrice=" + Price + ", productDiscount=" + Discount + ", salePrice=" + salePrice
				+ ", totalPrice=" + totalPrice;
	}
	
} // endclass
