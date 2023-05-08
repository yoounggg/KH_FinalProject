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
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;
import org.zerock.myapp.domain.OrderPageDTO;
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.MypageMapper;
import org.zerock.myapp.service.MypageService;
import org.zerock.myapp.service.OrderService;
import org.zerock.myapp.service.UserInfoService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage/*")
@Controller
public class MypageController {
	
	@Setter(onMethod_ = @Autowired)
	private MypageService mypageService;
	
	@Setter(onMethod_ = @Autowired)
	private UserInfoService userInfoService;

	@Setter(onMethod_ = @Autowired)
	private OrderService orderService;
	
	@Setter(onMethod_ = @Autowired)
	private MypageMapper mypageMapper;

	// OrderList(주문 내역) 페이지 단순 진입
	@RequestMapping("/orderList/{id}")
//	@RequestMapping(value = "/{orderList/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String orderList(@PathVariable("id") String id, OrderDTO dto, OrderItemDTO oit, OrderPageDTO opd , Model model) throws ControllerException, ServiceException {
		
		// aside바에 이름 띄우기
		MemberDTO memberDTO = this.userInfoService.userDetail(id);
		model.addAttribute("details", memberDTO); // 모델 == details
		
//		---------------------------------------------------------
		
		// 주문 정보 획득
		List<OrderDTO> orderDTO = mypageService.getOrder(id);
		
		log.trace("orderList({},{},{}) invoked.", id, orderDTO, model);

	    List<Integer> orderNoList = new ArrayList<>();

	    for (OrderDTO order : orderDTO) {
	        Integer orderNo = order.getNo();
	        orderNoList.add(orderNo);
	        // 이후 로직 처리
	    }
	    
	    List<Integer> totalPriceList = new ArrayList<>();
	    for (Integer orderNo : orderNoList) {
	        List<OrderItemDTO> itemList = mypageMapper.ItemPrice(orderNo);
	        int totalPrice = 0;
	        for (OrderItemDTO item : itemList) {
	            totalPrice += item.getCount() * item.getPrice() * (100 - item.getDiscount()) / 100;
	        }
	        totalPriceList.add(totalPrice);
	        
	    }
	    model.addAttribute("totalPriceList", totalPriceList);
		
		log.trace("******************* totalPriceList : {} ******************", totalPriceList);
		
		// 모델에 orderDTO(주문 정보 담아서 view에 전달)
		model.addAttribute("orderDTO", orderDTO);
		
//		model.addAttribute("orderList", orderService.getProductsInfo(opd.getOrders()));
		
//		log.trace("***********************Total price: {}************************", model.getAttribute("totalPrice"));
		
		return "mypage/OrderList";

	} // orderList()

	// OrderDetails(주문 상세 내역) 페이지 단순 진입
	@RequestMapping(value = "/orderDetails/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String orderDetails(@PathVariable("id") String id, @RequestParam("no") Integer no, Model model)
			throws ControllerException, ServiceException {
		
		// aside바에 이름 띄우기
		MemberDTO memberDTO = this.userInfoService.userDetail(id);
		model.addAttribute("details", memberDTO); // 모델 == details
		
//		---------------------------------------------------------------------
		
		// List로 모든 정보를 불러올게 아니라 OrderList에서 상세주문내역 클릭 시 어떻게 그 주문번호의 정보만 가져올지 생각중..
		// 주문 정보 획득
		List<OrderDTO> orderDTO = mypageService.getOrder(id);

		// 주문상품 정보 획득 ( 수정 필요 )
//		List<OrderItemDTO> orderItemDTO = mypageService.getOrderItemDTO(id);

		log.trace("orderDetails({},{}) invoked.", id, model);

		// no는 view단에서 id와 함께 전송하도록 해놓음( OrderList 상세주문내역 버튼 )
		// OrderDTO( 주문 상세 조회하려고 해당 주문 번호만(no) 가져와서 해당 주문번호에 대해서만 조회함 )
		OrderDTO Infos = this.mypageService.getSelect(no);
		
		// ================================================================
		// OrderItemDTO( 주문 상품 상세 조회하려고 no(MYMG_ORDER) 가져와서 해당 주문번호의 상품에 대해서만 조회함 )
		List<OrderItemDTO> ItemInfos = this.mypageService.getItemSelect(no);
		
		Integer totalPrice = 0;
		Integer totalDiscount = 0;
		Integer finalPrice = 0;

		for (OrderItemDTO item : ItemInfos) {
		    int count = item.getCount();
		    int price = item.getPrice();
		    int discount = item.getDiscount();

		    // 상품의 총합(price*count) 계산
		    Integer total = price * count;
		    totalPrice += total; // 총합 누적

		    // 할인비용 계산 (%로 계산)
		    Integer discountAmount = total * discount / 100;
		    totalDiscount += discountAmount; // 할인비용 누적

		    // 최종 결제금액 계산 (총합+배송비-할인비용)
		    Integer finalPriceItem = total - discountAmount; // 배송비는 변수가 정의되어 있지 않아 추후 수정이 필요합니다.
		    finalPrice += finalPriceItem; // 최종 결제금액 누적
		}

		// 모델에 변수들 추가
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("totalDiscount", totalDiscount);
		model.addAttribute("finalPrice", finalPrice);
		
		// ================================================================

		// 상품명 가져오기 (매개변수 수정 필요 -> 값 안들어감)
//		ProductDTO productName = this.mypageService.getProductName();

		log.trace("****************************  orderDetails({},{},{}) invoked. *****************************",
				Infos, ItemInfos, orderDTO);

		// 상품 명 가져오기
		List< List<ProductDTO> > productList = new ArrayList<>();
		
		for (OrderItemDTO item : ItemInfos) {
			int productNo = item.getProduct_no();
			
			log.trace(">>>>>>>>>>>>>>>>>>>>>>>> productNo : {} <<<<<<<<<<<<<<<<<<<<<", productNo);
			List<ProductDTO> product = this.mypageService.getProductName(productNo);
			productList.add(product);
		} // enhanced for
		
		// 상품명 리스트를 model에 추가
		model.addAttribute("productNames", productList);
		

		log.trace("****************************  orderDetails({},{}) invoked. *****************************", Infos,
				ItemInfos);

		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("info", Infos);
		model.addAttribute("ItemInfo", ItemInfos);
		model.addAttribute("orderDTO", orderDTO);
//		model.addAttribute("orderItemDTO", orderItemDTO);

		return "mypage/OrderDetails";

	} // orderDetails()

} // end class
