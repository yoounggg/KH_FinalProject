package org.zerock.myapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.OrderPageDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.MemberService;
import org.zerock.myapp.service.OrderService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/order")
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
//	@GetMapping("/orderpage")			// 주문페이지 이동 mapping 임시
//	public String orderPage() {
//		log.trace("payPage() invoked");
//	
//		return "order";
//	}; // orderPage
	
	
	// 테스트123
	@GetMapping("/orderpage") // 주문페이지로 이동 mapping
	public String orderPageGet(String id, OrderPageDTO opd, Model model) throws ServiceException {
		
		log.trace("memberId : " + id);
		log.trace("orders : " + opd.getOrders());
		
		if(opd == null) {
			opd = new OrderPageDTO();
		}

		// opd.getOrders()가 null인 경우, 새로운 ArrayList를 생성하여 opd에 저장합니다.
		if(opd.getOrders() == null) {
			opd.setOrders(new ArrayList<>());
		}
		// Model객체의 addAttribute 메서드를 사용하여 상품정보, 회원정보를 만들어 내는\
		// Service 메서드를 호출하여 반환받은 값들을 View단으로 전송
		
		model.addAttribute("orderList", orderService.getProductsInfo(opd.getOrders()));
		model.addAttribute("memberInfo", memberService.getMemberInfo(id));
		
		return "order";
		
	} // orderPageGet

	
}
