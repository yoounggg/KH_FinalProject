B
import lombok.Data;

@Data
public class OrderPageItemDTO {
	
<<<<<<< HEAD
	private Integer product_No;
	private Integer count;
	private String name;
	private Integer price;
	private Integer discount_price;
	private Integer totalPrice;
	public void initSaleTotal() {
//		this.salePrice = (int)(this.price * (1 - this.discount_price));
		this.totalPrice = this.discount_price*this.count;
	}
=======
	/* view로부터 전달받을 값 */
//	private String productId; // 이거 productId로 해야할지.. No로 해야할지..? -> View에서 오는값이니 productId해도될듯
	private Integer productId; // 상품테이블에 Pk가 Number라서 Integer로 함
	
	private Integer productCount;

	/* productId를 통해 DB에서 꺼내올 데이터 */
	private String Name;			// 상품이름 productName
	
	private Integer Price;			// 상품가격 productPrice
	
	private double Discount;		// 할인 productDiscount
	
	/* 만들어 낼 값 */

	private Integer salePrice; 			// 할인 가격
	
	private Integer totalPrice;			// 총 가격
	
	public void initSaleTotal() {
		this.salePrice = (int) (this.Price * (1-this.Discount));
		this.totalPrice = this.salePrice*this.Price;
	} //initSaleTotal
>>>>>>> :7f525edda9ff9ad4b68fdb8f1bab5e5bbc535dc7
	
	@Override
	public String toString() {
		return "OrderPageItemDTO [product_No=" + product_No + ", count=" + count + ", productName=" + name
				+ ", price=" + price + ", discount_price=" + discount_price + ", totalPrice=" + totalPrice + "]";
	}
	
//	/* view로부터 전달받을 값 */
////	private String productId; // 이거 productId로 해야할지.. No로 해야할지..? -> View에서 오는값이니 productId해도될듯
//	private Integer productId; // 상품테이블에 Pk가 Number라서 Integer로 함
//	
//	private Integer productCount;
//
//	/* productId를 통해 DB에서 꺼내올 데이터 */
//	private String Name;			// 상품이름 productName
//	
//	private Integer Price;			// 상품가격 productPrice
//	
//	private double Discount;		// 할인 productDiscount
//	
//	/* 만들어 낼 값 */
//	private Integer salePrice; 			// 할인 가격
//	
//	private Integer totalPrice;			// 총 가격
//	
//	public void initSaleTotal( ) {
//		this.salePrice = (int) (this.Price * (1-this.Discount));
//		this.totalPrice = this.salePrice*this.Price;
//	} //initSaleTotal
//	
//	@Override
//	public String toString() {
//		return "OrderPageItemDTO [productId=" + productId + ", productCount=" + productCount + ", productName=" + Name
//				+ ", productPrice=" + Price + ", productDiscount=" + Discount + ", salePrice=" + salePrice
//				+ ", totalPrice=" + totalPrice ;
//	}
	
} // endclass
