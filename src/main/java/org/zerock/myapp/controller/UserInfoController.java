package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.UserInfoService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
//@AllArgsConstructor

@RequestMapping("/mypage/userInfo/*")
@Controller
public class UserInfoController {
	
	@Setter(onMethod_=@Autowired) // 서비스 주입
	private UserInfoService service;
	
//	@GetMapping("/userInfo")
//	public String userDetail(String id) throws ControllerException{
//		log.trace("userDetail({})invoked", id);
//		
//		return "userInfo";
//	}
	
	//1. 회원상세조회
	@GetMapping("/{id}")
	public String userDetail(@PathVariable("id") String id, Model model) throws ControllerException {
		log.trace("userDetail({},{}) invoked.", id, model);

		try {
			MemberDTO dto = this.service.userDetail(id);
			model.addAttribute("details", dto); // 모델 == details
			
			return "userInfo";
			
		} catch (ServiceException e) {
			throw new ControllerException(e);
		} // try-catch
//		return "userInfo";
	} // userDetail
	
//	@GetMapping("/{id}") -> 모델을 없애니까 회원 상세에 회원 정보가 딸려오지 않음..
//	public String userDetail(@PathVariable("id") String id) throws ControllerException {
//		log.trace("userDetail({},{}) invoked.", id);
//
//		try {
//			MemberDTO dto = this.service.userDetail(id);
//			
//			return "userInfo";
//			
//		} catch (ServiceException e) {
//			throw new ControllerException(e);
//		} // try-catch
////		return "userInfo";
//	} // userDetail
	
	
	//2. 회원 정보 수정
	@PostMapping("/update")
	public String updateUser(MemberDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("updateUser({},{}) invoked.", dto, rttrs);
		
		try {
			boolean success = this.service.updateUser(dto);
			log.info("\t+success:{}", success);
			
			rttrs.addAttribute("result", (success)? "success" : "failure");
			
			return "redirect:/mypage/userInfo/" + dto.getId();
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-with
	} // updateUser
	
	@GetMapping("/update") //-> 상세조회화면도 get 해야함
	public void updateUser() {
		log.trace("updateUser() invoked.");
	} // updateUser
	
	
	//3. 회원 정보 삭제
	


} // end class
