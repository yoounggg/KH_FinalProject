package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ProductDTO;

public interface SearchService { // 인터페이스
	
	//1. 상품 리스트
	public List<ProductDTO> productList(Criteria cri);
	
	//2. 상품 총 개수
	public Integer totalProduct(Criteria cri);
	
	
} // end interface
