package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CartDTO { // maper 메서드에 필요한 장바구니 데이터를 전달하거나 반환받을 수 있도록 해줄 dto 클래스!
   
   //CART TABLE
   private Integer no; // <- cartNumber 
   private String member_id;
   private Integer product_no;
   private Integer count;
//   private Date cartDate; // ORACLE에 아직 없음 필요하면 추가해야함
   
   //product TABLE
   private String name;
   private Integer price;
   private Integer discount_price;
   
   //가격 계산 (cart_Count, price, discount_price-> 3개가 필요)
   private Integer salePrice;
   private Integer totalPrice;
   
   @Override
   public String toString() { // 우선 + ", cartDate=" + cartDate  제외함 
      return "CartDTO [no=" + no + ", member_id=" + member_id + ", product_no=" + product_no + ", count="
            + count + ", name=" + name + ", price=" + price
            + ", discount_price=" + discount_price + ", salePrice=" + salePrice + ", totalPrice=" + totalPrice +"]";
   } // toString
   
} // end class