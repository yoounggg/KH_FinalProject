package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.CategoryVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FarmVO;
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.domain.ProductVO;
import org.zerock.myapp.exception.ServiceException;

public interface ProductService {

	// 1. 전체 상품 목록 조회
	public abstract List<ProductVO> getList(Criteria cri) throws ServiceException; 
	
	// 2. 전체 레코드 개수 조회
	public abstract Integer getRecodeCount() throws ServiceException;

	// 3. 상단 메뉴타입 순서대로 상품 목록 조회
	public abstract List<ProductVO> getOrder(Criteria cri) throws ServiceException;
	
	// 4. 상품 상세 조회
	public abstract ProductDTO getProductDetail(Integer pno) throws ServiceException;
	
	
//	----------------------------------
//	[별이 4/14]
	
	// 1. 전체 상품 목록 조회
	public abstract List<ProductDTO> getList() throws ServiceException;
	
	// 1-1. 상품 목록 조회(페이징)
	public abstract List<ProductDTO> getListPaging(Criteria cri) throws ServiceException;
		
	// 2. 상품 상세 조회
	public abstract ProductDTO get(Integer no) throws ServiceException;
		
	// 3. 상품 등록
	public abstract Boolean register(ProductDTO dto) throws ServiceException;
	
	// 4. 상품 수정
	public abstract Boolean modify(ProductDTO dto) throws ServiceException;
	
	// 5. 상품 삭제
	public abstract Boolean remove(Integer no) throws ServiceException;
	
	// 6. 카테고리 등록
	public abstract List<CategoryVO> getCateList() throws ServiceException;
	
	// 7. 게시글 건수 반환
	public abstract Integer getTotal() throws ServiceException;
	
	
} // end interface
