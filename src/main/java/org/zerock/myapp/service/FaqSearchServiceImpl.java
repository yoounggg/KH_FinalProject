package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FaqDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.FaqSearchMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service("FaqSearchService")
public class FaqSearchServiceImpl implements FaqSearchService, InitializingBean{
	
		@Setter(onMethod_= {@Autowired})
		private FaqSearchMapper mapper;
	   
		@Override
		public void afterPropertiesSet() throws ServiceException {
			log.trace("afterPropertiesSet() invoked");

			try {
				Objects.requireNonNull(this.mapper);
			}catch(Exception e) {
				throw new ServiceException(e);
			} // try-catch
			
		} // afterPropertiesSet

		@Override
		public List<FaqDTO> faqSearchList(Criteria cri) throws ServiceException {
			log.trace("afterPropertiesSet() invoked");

			try {
				return this.mapper.faqSearchList(cri);
			} catch (Exception e) {
				throw new ServiceException(e);
			} // try-catch
		}
	
	
		@Override
		public Integer totalFaq(Criteria cri) throws ServiceException {
			log.trace("afterPropertiesSet() invoked");

			try {
				return this.mapper.totalFaq(cri);
			} catch (Exception e) {
				throw new ServiceException(e);
			} // try-catch
		} // totalProduct
	   
	   
} // end class
