package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.DeliveryConfirmDTO;
import org.zerock.myapp.exception.ServiceException;

public interface DeliveryConfirmService {

	// 1. 전체 주문 내역
	public abstract List<DeliveryConfirmDTO> getOrderList(String id) throws ServiceException; 
	
	public abstract List<DeliveryConfirmDTO> getDeliveryList(Integer order_no) throws ServiceException; 
	
	// 3. DB 등록
	public abstract boolean setDeliveryInsert(DeliveryConfirmDTO dto) throws ServiceException; 
		
} // end interface
