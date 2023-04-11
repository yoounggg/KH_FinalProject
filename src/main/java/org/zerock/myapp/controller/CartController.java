package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.domain.CartDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.CartService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("") // base uri
@Controller
public class CartController {
	
	@Autowired
	private CartService service;
	
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cartPageGET() {
		log.trace("cartPageGET() invoked(장바구니 페이지로 이동)");
		
		return "cart";
	} // cartPageGET
	
//	@RequestMapping(value = "/cart/{member_id}", method = RequestMethod.GET)
//	public String cartPageGET(@PathVariable("member_id") String member_id  , Model model) {
//		log.trace("cartPageGET() invoked(장바구니 페이지로 이동)");
//		
//		return "cart";
//	} // cartPageGET
	
	
	@PostMapping("/cart/add")
	@ResponseBody // 화면을 반환하는 것이 아니라 데이터를 반환하는 것이기 떄문
	public String addProductsInCartPOST(CartDTO cart, HttpServletRequest request) throws ControllerException {
										// 등록할 데이터 전달받아야 해서 DTO, 로그인 여부 확인하기 위해 SESSION 객체가 필요
		
		// 1. 로그인 체크
//		HttpSession session = request.getSession();
//		MemberVO vo = (MemberVO)session.getAttribute("member");
//		if(vo == null) {
//			return "5"; // 멤버 아니면 5반환 -> 로그인 필요함!
//		}
		
		
		// 2. 카트 등록
		try {
		int result = service.addProductsInCart(cart);
		
		return result + ""; // int -> string 하기 위해 빈문자열""을 더함
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // addProductsInCartPOST

}
