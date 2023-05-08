package org.zerock.myapp.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.domain.CartDTO;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.CartService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/cart") // base uri
@Controller
public class CartController {
	
	@Setter(onMethod_=@Autowired) // 서비스 주입
	private CartService service;
	
	
	@GetMapping("/")
	public String cartPageGET() { // 로그인 안 했을 때! -> CartInterceptor때문에 장바구니 진입 불가
		log.trace("cartPageGET() invoked(장바구니 페이지로 이동)");
		
		return "cart";
	} // cartPageGET
	
	// 1. 장바구니 조회는 회원만 할 수 있으니까 member_id데이터를 얻기 위해 파라미터 추가
	// 장바구니 데이터를 뷰에 넘길 때 model상자에 담아서 넘기기
	@GetMapping("/{member_id}")   
	public String cartMainPage(@PathVariable("member_id") String member_id, Model model) {
		log.trace("cartPageGET() invoked(장바구니 메인 페이지로 이동)");
		
		Objects.requireNonNull(this.service);
		log.info("\t+this.service:{}", this.service);

		model.addAttribute("cartInfo", service.getCart(member_id));
		
		return "cart";
	} // cartMainPage
	
	//2. 장바구니 상품 추가
	@PostMapping("/add")
	@ResponseBody // 화면을 반환하는 것이 아니라 데이터를 반환하는 것이기 떄문
	public String addProductsInCart(CartDTO cart, HttpSession session) throws ControllerException {
										// 등록할 데이터 전달받아야 해서 DTO, 로그인 여부 확인하기 위해 SESSION 객체가 필요
		
		log.trace("addProductsInCart() invoked(장바구니 상품 추가/등록)");
		
		// (1) 로그인 체크
		MemberDTO dto = (MemberDTO)session.getAttribute("member");
		if(dto == null) {
			return "5"; // 멤버 아니면 5반환 -> 로그인 필요함!
		}
		
		
		// (2) 카트 등록
		try {
		int result = service.addProductsInCart(cart);
		
		return result + ""; // int -> string 하기 위해 빈문자열""을 더함
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // addProductsInCartPOST

	
	//3. 장바구니 수량 수정
	@PostMapping("/update")
	public String modifyCount(CartDTO cart) {
		log.trace("modifyProductsInCart() invoked(장바구니 수량 변경)");

		service.modifyCount(cart);			

		return "redirect:/cart/" + cart.getMember_id();
	} // modifyCount
	
	
	//4. 장바구니 삭제
	@PostMapping("/delete")
	public String deleteCart(CartDTO cart) {
		
		log.trace("deleteCart() invoked(장바구니 수량 삭제)");
		service.deleteCart(cart.getNo());
		return "redirect:/cart/" + cart.getMember_id();
	} // deleteCart
	
} // end class
