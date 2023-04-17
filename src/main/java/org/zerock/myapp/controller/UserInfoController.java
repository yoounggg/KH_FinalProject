package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage")
@Controller
public class UserInfoController {
	
	//1. 회원정보수정 메인 페이지 
	@GetMapping("/userInfo")
	public String userInfoMain() {
		log.trace("userInfoMain() invoked(회원정보수정 페이지)");
		
		return "userInfo";
	}

}
