package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.DeliveryConfirmDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.DeliveryConfirmMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service("DeliveryConfirmService")
public class DeliveryConfirmServiceImpl implements DeliveryConfirmService, InitializingBean {

	@Setter(onMethod_= { @Autowired })
	private DeliveryConfirmMapper	 mapper;
	
	@Override
	public void afterPropertiesSet() throws ServiceException {   
		log.trace("afterPropertiesSet() invoked");
		
		try {
			Objects.requireNonNull(this.mapper);
		} catch(Exception e) {
			throw new ServiceException(e);
		} //try-catch
	} // afterPropertiesSet
	
	
	@Override
	public List<DeliveryConfirmDTO> getOrderList(String id) throws ServiceException {
		log.info("getOrderList()");
		
		try {
			return this.mapper.SelectOrderList(id);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // getOrderList
	
	@Override
	public List<DeliveryConfirmDTO> getDeliveryList(Integer order_no) throws ServiceException {
		log.info("getDeliveryList()");
		
		try {
			return this.mapper.SelectDeliveryList(order_no);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public boolean setDeliveryInsert(DeliveryConfirmDTO dto) throws ServiceException {
		log.trace("setDeliveryInsert({}) invoked.", dto);
		
		try {
			return this.mapper.DeliveryConfirmInsert(dto) == 1;
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // setDeliveryInsert


} // end class
