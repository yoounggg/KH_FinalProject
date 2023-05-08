package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.DeliveryConfirmDTO;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.Page_ProductDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.DeliveryConfirmService;
import org.zerock.myapp.service.UserInfoService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/mypage/deliveryConfirm")
public class DeliveryConfirmController {

	@Setter(onMethod_=@Autowired) // 서비스 주입
	private UserInfoService service;
	
	@Setter(onMethod_=@Autowired) // 서비스 주입
	private DeliveryConfirmService dcService;
	
	//1. 회원상세조회
	@GetMapping("/{id}")
	public String userDetail(@PathVariable("id") String id, Model model) throws ControllerException {
		log.trace("userDetail({},{}) invoked.", id, model);

		try {
			MemberDTO dto = this.service.userDetail(id);
			model.addAttribute("details", dto); // 모델 == details
			
			List<DeliveryConfirmDTO> dcDTO = this.dcService.getOrderList(id);
			log.info("\t dcDTO : {}",dcDTO);
			model.addAttribute("__OrderList__", dcDTO);
			
			return "mypage/deliveryConfirm";
			
		} catch (ServiceException e) {
			throw new ControllerException(e);
		} // try-catch

	} // userDetail
} // end class
