package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.NoticeMapper;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2
@AllArgsConstructor

@Service ("NoticeService")
public class NoticeServiceImpl implements NoticeService, InitializingBean{

	private NoticeMapper mapper; // 영속성으로 등록한 매퍼를 가져와서 서비스랑 이어줌

	@Override
	public void afterPropertiesSet() throws Exception { 
		// 빈 주입이 잘 됐는지 확인, 1회성 전처리
		log.trace("afterPropertiesSet() invoked.");
		// 빈(dao) 주입 잘 받았는지 확인해보기
		
		try {
			Objects.requireNonNull(this.mapper);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	} //afterPropertiesSet()

	@Override
	public List<NoticeDTO> getList() throws ServiceException {
		log.trace("getList() invoked.");
		
		try {
			return this.mapper.selectAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // getList()


	@Override
	public List<NoticeDTO> getListPaging(Criteria cri) throws ServiceException {
		
		try {
			return this.mapper.getListPaging(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // getListPaging
	
	
	@Override
	public NoticeDTO get(Integer no) throws ServiceException {
		log.trace("get({}) invoked.", no);
		
		try {
			return this.mapper.select(no);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	
	} // get

	
	@Override
	public Boolean register(NoticeDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return this.mapper.insert(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	
	} // get

	
	@Override
	public Boolean remove(Integer no) throws ServiceException {
		log.trace("remove({}) invoked.", no);
		
		try {
			return this.mapper.delete(no) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // remove()

	
	@Override
	public Boolean modify(NoticeDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return this.mapper.update(dto) == 1;
		} catch(Exception e) {
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