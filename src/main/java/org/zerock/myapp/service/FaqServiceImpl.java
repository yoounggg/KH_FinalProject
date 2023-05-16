package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FaqDTO;
import org.zerock.myapp.domain.FaqVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.FaqMapper;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2
@AllArgsConstructor

@Service("FaqService")
public class FaqServiceImpl implements FaqService, InitializingBean {
	
	private FaqMapper mapper;


	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("afterPropertiesSet");
		
		try {
			Objects.requireNonNull(this.mapper);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	} // afterPropertiesSet
	
	
	@Override
	public List<FaqVO> getList() throws ServiceException {
		log.trace("getList() invoked.");
		
		try {
			return this.mapper.selectAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		

	} //getList
	
	
	
	@Override
	public List<FaqVO> getListPaging(Criteria cri) throws ServiceException {
		
		try {
			return this.mapper.getListPaging(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getListPaging
	
	
	
	

	@Override
	public FaqVO get(Integer no) throws ServiceException {
		log.trace("get({}) invoked.", no);
		
		try {
			return this.mapper.select(no);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	
	} // get
	
	

	@Override
	public Boolean register(FaqDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return this.mapper.insert(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // register
	
	

	@Override
	public Boolean remove(Integer no) throws ServiceException {
		log.trace("remove({}) invoked.", no);
		
		try {
			return this.mapper.delete(no) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} //remove
	
	

	@Override
	public Boolean modify(FaqDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return this.mapper.update(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // modify
	
	
	
	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getTotal() invoked.");
		try {
			return this.mapper.getTotal();
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	
	} // getTotal




} // end class