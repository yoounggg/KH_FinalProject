package org.zerock.myapp.domain;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//[별이-04/13] 이거 Data 아닌가용 VO랑 바뀐 것 같아서 둘이 바꿔놓겠습니다~
@Data
public class ProductDTO {

	
//	[별이-04/13]데이터베이스랑 이름 좀 다시 맞췄어요~~
	
//	[별이-04/13]pno->no로 수정함
	/* 상품 번호(자동부여됨)*/
	private Integer no;			
	
	/* 상품 카테고리 */	
	private String category;
	
//	[별이-04/13]pname->name으로 수정함
	/* 상품 이름*/
	private String name;
	
	/* 상품 가격*/
	private Integer price;
	
	/* 상품 할인 퍼센트*/
	private Integer discount;
	
//	[별이-04/13]disprice->discount_price으로 수정함
	private Integer discount_price;
	
	/* 상품 농장이름인가여? */
	private String brandname;
	
	/* 상품 원산지->근데이거 그냥 오아시스 따라한건데 어차피 국내산이지 않나요? 없애도 되는건지 의견주세요*/	
	private String origin;

	/* 상품 중량 */	
	private String weight;
	
	/* 상품 재고 수량 */	
	private Integer stock;
	
	/* 상품 메인 이미지 */
	private String main_image;
	
	/* 상품 메인 썸네일 이미지 */
	private String main_image2;
	
//	[별이-04/13]subimage->sub_image1,2,3,4으로 수정함
	/* 상품 메인 서브 썸네일 이미지 */	
	private String sub_image1;
	private String sub_image2;
	private String sub_image3;
	private String sub_image4;
	
//	[별이-04/13]insert_ts->reg_date으로 수정함
	/* 상품 등록 날짜 */
	private Timestamp reg_date;
	
	
	/* 상품 내용*/
	private String content;
	
//	[별이-04/13]image->content_image으로 수정함
	/* 상품 내용 이미지*/
	private String content_image;
	
	/* 상품 농가업체번호(사업자아님 모야모과에 등록된 고유번호임) 
	 * 농가업체가 무슨물건 파는지 fk 준 것 */	
	private Integer farm_no;
	
	/* 상품 수정 날짜 */		
	private Timestamp update_date;
	
	/* 조회수 */
	private Integer hit;
//	====================================================================
//	====================================================================    
	
//	[별이-04/18]필드 추가함
	
	/* 이미지 정보 */
	private List<AttachImageVO> imageList;

	@Override
	public String toString() {
		return "ProductDTO [category="+category+", name="+name+", price="+price+", discount="+discount+", discount_price="+discount_price+" ,weight="
				+weight+", origin="+origin+", stock="+stock+", farm_no="+farm_no+", main_image"+main_image+", main_image2"+main_image2+", sub_image1="+sub_image1+", sub_image2="+sub_image2+", sub_image3="+sub_image3+", sub_image4="+sub_image4+
				", content="+content+", content_image="+content_image+ ", imageList="+imageList+"]";
	} // toString
	
	
} // end class
