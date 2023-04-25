package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.CategoryVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.domain.ProductVO;

public interface ProductMapper {

	@Select("""
			SELECT /* index_asc(product_list) */ no, name, content, content_image, price, discount,
			brandname, reg_date, hit, (price - (price * (discount / 100))) - mod(price -
			(price * (discount /100)), 10) as discount_price
			FROM product_list OFFSET ( #{currPage} -1) * #{amount} ROWS FETCH NEXT #{amount} ROWS ONLY
			""")
	public abstract List<ProductVO> SelectAllList(Criteria cri); // 전체목록 가져오기

	@Select("SELECT count(pno) FROM product_list")
	public abstract Integer SelectRecodeAll(); // 전체 레코드 갯수 가져오기

	public abstract List<ProductVO> SelectOrder(Criteria cri); // 상단 메뉴타입 순서대로 상품 목록 조회

	public abstract ProductDTO SelectDetail(Integer pno); // 상품 상세 조회

//	==============================================================
//	[별이]
	
	/* 상품 전체 조회 */
	@Select("SELECT /*+ index_desc(Product) */ * FROM PRODUCT")
	public abstract List<ProductVO> selectAll();
	
	//-------------------------- XML 매핑 방식 ------------------------------//
	
	/* 상품 상세 조회 */
	public abstract ProductVO select(Integer no);
	
	/* 상품 등록 */
	public abstract Integer insert(ProductDTO dto);
	
	/* 상품 수정 */
	public abstract Integer update(ProductDTO dto);
	
	/* 상품 삭제 */
	public abstract Integer delete(Integer no);
	
	
	/* 카테고리 리스트 */
	public abstract List<CategoryVO> cateList();

} // end interface
