package org.zerock.myapp.domain;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO { // maper 메서드에 필요한 장바구니 데이터를 전달하거나 반환받을 수 있도록 해줄 dto 클래스!
	
	//CART TABLE
	private Integer no; // <- cartNumber 
	private String member_id;
	private Integer count;	
	private Integer product_No; // ** product의 no (pk)를 fk로 가져옴

	
	//product TABLE
	private String name;
	private Integer price; // -> 상품 한 개의 원가 
	private Integer discount_price; // -> 상품 한 개의 최종 가격
	private String main_image;
	private Integer stock;
	
	//가격 계산 (cart_Count, price, discount_price-> 3개가 필요)
//	private Integer salePrice;
	private Integer totalPrice;
	
	//위에서 가격 있는 변수로 최종 가격 계산하는 메소드 ! 
	public void initPrice() {
//		this.salePrice = (int)(this.price * (1 - this.discount_price));
		this.totalPrice = this.discount_price*this.count;
	}
	
	@Override
	public String toString() { 
		return "CartDTO [no=" + no + ", member_id=" + member_id + ", product_no=" + product_No + ", count="
				+ count + ", name=" + name + ", price=" + price
				+ ", discount_price=" + discount_price + ", totalPrice=" + totalPrice + ", main_image=" + main_image+",stock=" + stock +"]";
	} // toString
	
} // end class
