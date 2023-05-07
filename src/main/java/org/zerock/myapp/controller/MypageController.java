package org.zerock.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;
import org.zerock.myapp.domain.ProductDTO;
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
	@RequestMapping("/orderList/{id}")
	public String orderList(@PathVariable("id") String id, OrderDTO dto, OrderItemDTO oit, Model model) throws ControllerException, ServiceException {
		
		// 주문 정보 획득
		List<OrderDTO> orderDTO = mypageService.getOrder(id);
		
		// 주문상품 정보 획득 ( 수정 필요 -> 모든 정보 불러와서 빼놓음 )
//		List<OrderItemDTO> orderItemDTO = mypageService.getOrderItemDTO(id);

//		log.trace("orderList() invoked.");
		log.trace("orderList({},{},{},{}) invoked.", id, orderDTO, model);
		
//		Map<String, Object> dataMap = new HashMap<>(); // Map 제대로 안되는듯
//		
//		dataMap.put("OrderDTO", orderDTO);
//		dataMap.put("orderItemDTO", orderItemDTO);
//		
//		model.addAttribute("orderInfo", dataMap);
		
		// 모델에 orderDTO(주문 정보 담아서 view에 전달)
		model.addAttribute("orderDTO", orderDTO);
		
		// totalbPrice는 제대로 안들어간다..
		model.addAttribute("totalPrice", dto.getTotalPrice());
		
		return "mypage/OrderList";

	} // orderList()
	
	// OrderDetails(주문 상세 내역) 페이지 단순 진입
	@RequestMapping(value = "/orderDetails/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String orderDetails(@PathVariable("id") String id, @RequestParam("no") Integer no, Model model) throws ControllerException, ServiceException {
		
		// List로 모든 정보를 불러올게 아니라 OrderList에서 상세주문내역 클릭 시 어떻게 그 주문번호의 정보만 가져올지 생각중..
		// 주문 정보 획득
		List<OrderDTO> orderDTO = mypageService.getOrder(id);
		
		// 주문상품 정보 획득 ( 수정 필요 )
//		List<OrderItemDTO> orderItemDTO = mypageService.getOrderItemDTO(id);
		
		log.trace("orderDetails({},{}) invoked.", id, model);

		// no는 view단에서 id와 함께 전송하도록 해놓음( OrderList 상세주문내역 버튼 )
		// OrderDTO( 주문 상세 조회하려고 해당 주문 번호만(no) 가져와서 해당 주문번호에 대해서만 조회함  )
		OrderDTO Infos = this.mypageService.getSelect(no);
		// OrderItemDTO( 주문 상품 상세 조회하려고 no(MYMG_ORDER) 가져와서 해당 주문번호의 상품에 대해서만 조회함 )
		List<OrderItemDTO> ItemInfos = this.mypageService.getItemSelect(no);
		
		// 상품명 가져오기 (매개변수 수정 필요 -> 값 안들어감)
		ProductDTO productName = this.mypageService.getProductName(oit.getProduct_no());

		log.trace("****************************  orderDetails({},{},{},{}) invoked. *****************************", Infos, ItemInfos, productName, orderDTO);
		
		//상품 명 가져오기
		for (OrderItemDTO item : ItemInfos) {
		    int productNo = item.getProduct_no();
		    List<ProductDTO> productName = this.mypageService.getProductName(productNo);
		    // 상품명 리스트를 model에 추가
			model.addAttribute("productName", productName);
			log.trace("******************* productName({}) invoked.", productName);
		}
		
		log.trace("****************************  orderDetails({},{}) invoked. *****************************", Infos, ItemInfos);

		model.addAttribute("info", Infos);
		model.addAttribute("ItemInfo", ItemInfos);
		model.addAttribute("orderDTO", orderDTO);
//		model.addAttribute("orderItemDTO", orderItemDTO);

		return "mypage/OrderDetails";
		
	} // orderDetails()
	
} // end class
