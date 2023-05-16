package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.SearchMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service("SearchService") // == 스프링 컨테이너에서 빈으로 관리되게 해줌
public class SearchServiceImpl implements SearchService, InitializingBean{
	
	   @Setter(onMethod_= {@Autowired})
	   private SearchMapper mapper; // SearchMapper -> 매퍼 주입
	   
	   @Override
	   public void afterPropertiesSet() throws ServiceException {
	      log.trace("afterPropertiesSet() invoked");
	      //1회성 전처리 -> 위에서 빈 (dao)을 잘 주입받았는지 체크해보기
	      try {
	         Objects.requireNonNull(this.mapper);
	         log.info("\t+this.dao:{}", this.mapper);
	      }catch(Exception e) {
	         throw new ServiceException(e);
	      } // try-catch
	   } // afterPropertiesSet

	   
	@Override
	public List<ProductDTO> productList(Criteria cri) {
		log.trace("productList() invoked");
		
		cri.setAmount(12);
		cri.setPagesPerPage(3);
		
		return mapper.productList(cri);
	} // productList

	
	@Override
	public Integer totalProduct(Criteria cri) {
		log.trace("totalProduct() invoked");
		
		return mapper.totalProduct(cri);
	} // totalProduct
	   
	   
} // end class
