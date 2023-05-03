package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.AttachImageVO;
import org.zerock.myapp.domain.CartDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.AttachMapper;
import org.zerock.myapp.mapper.CartMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service("cartService") // == 스프링 컨테이너에서 빈으로 관리되게 해줌
public class CartServiceImpl implements CartService, InitializingBean { // pojo // 구현 클래스
   
   //cartmapper 객체의 메소드를 사용할거라서 의존성 주입
   @Setter(onMethod_= {@Autowired})
   private CartMapper mapper;
   
   
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
   
   
   // ** 0 = 등록실패 / 1 = 등록 성공 / 2 = 등록된 데이터 존재 /5 = 로그인 필요 **
   //1. 장바구니에 상품 추가
   @Override
   public Integer addProductsInCart(CartDTO cart) throws ServiceException { // 영속성에서는 addcart였음
      log.trace("addProductsInCart({}) invoked", cart);
      
      CartDTO checkcart = mapper.checkCart(cart); // mapper의 checkcart() 메소드 활용!
      
      if(checkcart != null) { // checkcart카트 확인해서 db에 이미 데이터가 존재하면 2를 반환
         return 2;
      } // if
      
      try { // 장바구니 등록이 이미 되어 있거나 에러가 나면 0 반환
         return mapper.addCart(cart); // 1
      }catch(Exception e) {
         return 0; // 0
      }
      
   } // addProductsInCart
 
   
   //2. 장바구니 정보 리스트  
   @Override
   public List<CartDTO> getCart(String member_id) {
//   public List<CartVO> getCart(String member_id) {
      log.trace("getCart({}) invoked", member_id);
      
      List<CartDTO> list = mapper.getCart(member_id);
      
      for(CartDTO cart : list) {
         cart.initPrice(); // 장바구니에 들어가는 종합 정보 초기화
      } // for
      
      return list; // 카트에 값이 모두 세팅된 게 나옴

   } // getCart
   
   
   //3. 장바구니 수량 수정
	@Override
	public Integer modifyCount(CartDTO cart) {
		log.trace("modifyProductsInCart({})invoked", cart);
		
		return mapper.modifyCount(cart);
	} // modifyCount

	//4. 장바구니 삭제
	@Override
	public Integer deleteCart(Integer no) {
		log.trace("deleteCart({})invoked", no);
		
		return mapper.deleteCart(no);
				
	} // getCart

} // end class