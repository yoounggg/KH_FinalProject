package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.CartDTO;
import org.zerock.myapp.domain.CartVO;

public interface CartMapper { // 인터페이스!!
	//**메소드 선언
	//-> cartMapper.xml 에 쿼리 작성함
	
//	카트 추가
	public abstract Integer addCart(CartDTO cart) throws Exception; // cartdto가 매개변수로! -> 테이블의 row를 추가해주는 메소드! 한번에 값을 다 가져오기 위해 cartdto 사용
	                                             // throws exception은 메서드를 호출한 주체에게 메서드에서 발생한 예외를 던져주는 역할! 
	//-> 이 메서드에서 throws exception이 없다면 예외상황이 발생하면 실행이 멈춘다! addcart메소드를 호출한 주체 입장에서 어떠한 상황이 터졌는지 알수가 없기 떄문에 이 예외를 던져서 
	//메소드를 호출한 주체가 addcart메소드에서 예외상황이 발생했을 떄 어떠한 예외상황인지를 전달받을 수 있게 됨
	
//	카트 삭제
	public abstract Integer deleteCart(Integer no); // 장바구니 번호!로 어떤 ROW 삭제할지 지정하기
	
//	카트 수량 수정 
	public abstract Integer modifyCount(CartDTO cart); // 지정한 ROW의 수량 변경! 
	//어떤 ROW인지 지정하기 위한 NO와 몇 개로 변경할지에 대한 COUNT가 필요-> 두 값을 한번에 가져오기 위해 CARTDTO 선언
	
//	카트 목록 확인
//	public abstract List<CartVO> getCart(String member_id);
	public abstract List<CartDTO> getCart(String member_id);	// 회원의 모든 ROW를 가져오기! 어떤 회원인지 알아야 해서 member테이블의 id가 필요
	//한개 이상의 장바구니 데이터를 반환받아야 해서 list<cartdto>
	
//	카트 확인
	public abstract CartDTO checkCart(CartDTO cart); //회원정보(memberid)와 상품정보(product_no) 넘겨서 해당하는 row있는지 확인해서 한번에 넘기려고 cartdto 선언 
	
	/* 카트 제거(주문) -> 찬돌 */ 
	public Integer deleteOrderCart(CartDTO dto);
} //end class
