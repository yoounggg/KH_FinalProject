package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.AttachImageVO;
import org.zerock.myapp.domain.CategoryVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.domain.ProductVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.ProductMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2
@NoArgsConstructor

@Service("productService")
public class ProductServiceImpl implements ProductService, InitializingBean{

	@Setter(onMethod_= { @Autowired })
	private ProductMapper mapper;
	
	@Override
	public void afterPropertiesSet() throws ServiceException {   
		log.trace("afterPropertiesSet() invoked");
		
		try {
			Objects.requireNonNull(this.mapper);
			log.info("\t 1. this.mapper : {}", this.mapper);
		} catch(Exception e) {
			throw new ServiceException(e);
		} //try-catch

	}

	@Override
	public List<ProductVO> getList(Criteria cri) throws ServiceException {
		log.trace("\t getList({}) invoked", cri);
		
		try {
			return this.mapper.SelectAllList(cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getList
	
	@Override
	public Integer getRecodeCount() throws ServiceException {
		log.trace("\t getRecodeCount() invoked");
		
		try {
			return this.mapper.SelectRecodeAll();
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getRecodeCount
	
	
	@Override
	public List<ProductVO> getOrder(Criteria cri) throws ServiceException {
		log.trace("\t getMenuOrder() invoked");
		
		try {
			return this.mapper.SelectOrder(cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getMenuOrder
	
	
	@Override
	public ProductDTO getProductDetail(Integer pno) throws ServiceException {
		log.trace("\t getProductDetail({}) invoked", pno);
		
		try {
			return this.mapper.SelectDetail(pno);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} //getProductDetail

//	==================================================
//	[별이]
	
	/* 상품 등록 */
	@Override
	public Boolean register(ProductDTO dto) throws ServiceException {
	    log.trace("register({}) invoked.", dto);

	    try {
	        // 상품 등록
	        boolean isInserted = mapper.insert(dto) == 1;

	        if (isInserted == true) {
	            // 이미지 등록
	            for (AttachImageVO attach : dto.getImageList()) {
	                attach.setProduct_no(dto.getNo());
	                mapper.imageInsert(attach);
	            }
	        }

	        return isInserted;
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    } // try-catch
	    
	} // register

	
	/* 카테고리 등록 */
	@Override
	public List<CategoryVO> getCateList() throws ServiceException {
		log.trace("getCateList() invoked.");
		
		try {
			return this.mapper.cateList();
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	
	} // getCateList
		

} // end class
