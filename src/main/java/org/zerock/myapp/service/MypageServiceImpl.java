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
	
	// member_id를 매개변수 줘서 해당 회원에대한 주문정보 불러오기 위함(OrderDTO는 MYMG_ORDER테이블의 DTO) 
	@Override
	public List<OrderDTO> getOrder(String member_id) throws ServiceException {
	
		return mypageMapper.getOrder(member_id);
		
	} // getOrder

	@Override // ( 수정 필요, 지금 db에 저장된 모든 데이터 가져옴 )
	public List<OrderItemDTO> getOrderItemDTO(String member_id) throws ServiceException {
		
		return mypageMapper.getOrderItem(member_id);
		
	} // getOrderItemDTO

	// 주문상세정보 클릭시 해당 주문번호의 주문정보를 불러오기 위함 ( 그냥 id로 불러오면 회원이 여러개 주문한 정보 다 가져옴 )
	@Override	
	public OrderDTO getSelect(Integer no) throws ServiceException {
	     
	      try {
	        return this.mypageMapper.select(no);
	      } catch(Exception e) { // 비즈니스 계층에서 오류나면 serviceexception 던지기로 약속!
	         throw new ServiceException(e);
	      } // try-catch

	} //getSelect

	// 주문상세정보 클릭시 해당 주문번호의 주문상품정보를 불러오기 위함
	@Override
	public List<OrderItemDTO> getItemSelect(Integer order_no) throws ServiceException {
		
	      try {
	    	  // 주문번호의 주문상품정보가 필요하니 주문번호로 불러옴 
	    	  return this.mypageMapper.ItemSelect(order_no);
		  } catch(Exception e) { // 비즈니스 계층에서 오류나면 serviceexception 던지기로 약속!
		      throw new ServiceException(e);
		  } // try-catch
	      
	}

	// 주문상세정보 클릭시 해당 주문번호의 주문상품에 대한 이름, 가격을 가져오기 위함
	// 원래는 이름만 가져오려고 했는데 가격도 같이 가져옴 ( 변수이름 바꿀 필요o )
	@Override
	public List<ProductDTO> getProductName(Integer product_no) throws ServiceException {
			
		try {
			// 상품의 정보가 필하기 때문에 product_no를 가져옴 
			return this.mypageMapper.productName(product_no);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	} // getProductName
	
	
	
} // end class
