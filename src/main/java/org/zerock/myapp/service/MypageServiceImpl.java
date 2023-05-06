package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.MypageMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@Service
public class MypageServiceImpl implements MypageService {

	private OrderDTO orderDTO;
	
	@Setter(onMethod_= {@Autowired})
	private MypageMapper mypageMapper;

	
	// 매핑처리에 아이디 붙이려고 조회해서 가져오기
	@Override
	public MemberDTO getID(String id) {
		
		log.trace("findID({}) invoked.", id);
		
		return this.mypageMapper.getID(id);
		
	} // getID()
	
	@Override
	public List<OrderDTO> getOrder(String member_id) throws ServiceException {
		
//		OrderDTO order = mypageMapper.getOrder(OrderDTO order);
		
		return mypageMapper.getOrder(member_id);
		
	} // getOrder

	@Override // ( 수정 필요, 지금 db에 저장된 모든 데이터 가져옴 )
	public List<OrderItemDTO> getOrderItemDTO(String member_id) throws ServiceException {
		
		return mypageMapper.getOrderItem(member_id);
		
	} // getOrderItemDTO

	@Override
	public OrderDTO getSelect(Integer no) throws ServiceException {
	     
	      try {
	        return this.mypageMapper.select(no);
	      } catch(Exception e) { // 비즈니스 계층에서 오류나면 serviceexception 던지기로 약속!
	         throw new ServiceException(e);
	      } // try-catch

	} //getSelect

	@Override
	public List<OrderItemDTO> getItemSelect(Integer order_no) throws ServiceException {
		
	      try {
		        return this.mypageMapper.ItemSelect(order_no);
		  } catch(Exception e) { // 비즈니스 계층에서 오류나면 serviceexception 던지기로 약속!
		         throw new ServiceException(e);
		  } // try-catch
	      
	}

	@Override
	public ProductDTO getProductName(Integer product_no) throws ServiceException {
			
		try {
			return this.mypageMapper.productName(product_no);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	} // getProductName
	
	
	
} // end class
