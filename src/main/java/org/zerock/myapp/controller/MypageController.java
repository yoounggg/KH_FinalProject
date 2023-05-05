package org.zerock.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.MypageService;
import org.zerock.myapp.service.OrderService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage/*")
@Controller
public class MypageController {
	
	@Setter(onMethod_=@Autowired)
	private MypageService mypageService;
	
	@Setter(onMethod_=@Autowired)
	private OrderService orderService;
	
	// OrderList(주문 내역) 페이지 단순 진입
//	@GetMapping("/orderList")
	@RequestMapping("/orderList/{id}")
//	public String orderList() {
	public String orderList(@PathVariable("id") String id, OrderDTO dto, OrderItemDTO oit, Model model) throws ControllerException, ServiceException {
		
		// 주문 정보 획득
		List<OrderDTO> orderDTO = mypageService.getOrder(id);
		
		// 주문상품 정보 획득 ( 수정 필요 )
		List<OrderItemDTO> orderItemDTO = mypageService.getOrderItemDTO(id);

//		log.trace("orderList() invoked.");
		log.trace("orderList({},{},{},{}) invoked.", id, orderDTO, orderItemDTO, model);
		
//		Map<String, Object> dataMap = new HashMap<>(); // Map 제대로 안되는듯
//		
//		dataMap.put("OrderDTO", orderDTO);
//		dataMap.put("orderItemDTO", orderItemDTO);
//		
//		model.addAttribute("orderInfo", dataMap);
		
		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("orderItemDTO", orderItemDTO);
		
		return "mypage/OrderList";

	} // orderList()
	
	// OrderDetails(주문 상세 내역) 페이지 단순 진입
//	@GetMapping("/orderDetails")
	@GetMapping("/orderDetails/{id}")
//	public String orderDetails() {
	public String orderDetails(@PathVariable("id") String id, Model model) throws ControllerException {
		
//		log.trace("orderDetails() invoked.");
		log.trace("orderDetails({},{}) invoked.", id, model);

		return "mypage/OrderDetails";
		
	} // orderDetails()
	
} // end class
