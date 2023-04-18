package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.exception.ControllerException;
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
	
//	@GetMapping("/main")
//	public String userInfoMain() { // 메인페이지 보려고
//		
//		return "userInfo";
//	}
	
	@GetMapping("/main")
	public void userDetail(@RequestParam("id") String id, Model model) throws ControllerException{
		log.trace("userDetail({},{}) invoked.", id, model);
		
		
	}

}
