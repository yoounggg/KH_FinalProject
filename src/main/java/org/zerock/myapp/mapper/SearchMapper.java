package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ProductDTO;

public interface SearchMapper { // 메인페이지에서 검색
	
	//상품검색
	public List<ProductDTO> productList(Criteria cri);
	
	//상품 총 개수
	public Integer totalProduct(Criteria cri);
	
} // end interface
