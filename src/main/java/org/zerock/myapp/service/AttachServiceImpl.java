package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.AttachImageVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.AttachMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
@Log4j2
@NoArgsConstructor

//[별이] 생성 4/19
@Service("AttachService")
public class AttachServiceImpl implements AttachService, InitializingBean {

	@Setter(onMethod_= {@Autowired})
	private AttachMapper mapper;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("afterPropertiesSet() invoked.");
		
		try {
	         Objects.requireNonNull(this.mapper);
	         log.info("\t+this.dao:{}", this.mapper);
	      }catch(Exception e) {
	         throw new ServiceException(e);
	      } // try-catch		
	
	} //afterPropertiesSet()

	
	@Override
	public List<AttachImageVO> getAttachList(Integer product_no) throws ServiceException {
		log.trace("get({}) invoked.", product_no);
		
		try {
			return this.mapper.getAttachList(product_no);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} //getAttachList

	
	
} // end class
