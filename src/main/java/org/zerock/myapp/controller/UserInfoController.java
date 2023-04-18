package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage/userInfo/*")
@Controller
public class UserInfoController {
	
	@Setter(onMethod_=@Autowired) // 서비스 주입
	private UserInfoService service;
	
	
	@GetMapping("/main")
	public String userInfoMain() { // 메인페이지 보려고
		
		return "userInfo";
	}

}
