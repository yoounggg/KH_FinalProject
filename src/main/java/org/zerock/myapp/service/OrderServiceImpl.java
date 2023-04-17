package org.zerock.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.OrderPageItemDTO;
import org.zerock.myapp.mapper.OrderMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class OrderServiceImpl implements OrderService {
	
	@Setter(onMethod_=@Autowired)
	private OrderMapper orderMapper;	//orderMapper 주입

	@Override
	public List<OrderPageItemDTO> getProductsInfo(List<OrderPageItemDTO> orders) {

		List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();
		
		for(OrderPageItemDTO ord : orders) {	// 장바구니 페이지(Veiw)에서 전달 받은 정보 List객체의 수 만큼 반복해주는 for문 작성
			//  상품 정보를 만들어내는 OrderMapper의 getProductsInfo() 메서드를 호출 , productsInfo 변수에 저장
			OrderPageItemDTO productsInfo = orderMapper.getProductsInfo(ord.getProductId());
			
			//현재의 productsInfo 변수에 있는 상품 정보 객체는 productCount에 대한 정보는 없기 때문에 뷰로부터 전달받은 productCount 값을 대입
			productsInfo.setProductCount(ord.getProductCount());
			
			//OrderPageItemDTO객체에 만들어 둔 initSaleTotal() 메서드를 호출하여 그 값들을 세팅
			productsInfo.initSaleTotal();
			
			//List객체인 reuslt에 요소로 추가
			result.add(productsInfo);
		
		} // for
		
		return result;
		
	} // getProductsInfo

}// end class
