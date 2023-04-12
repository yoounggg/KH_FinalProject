package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login") 
@Controller
public class LoginController {

	@RequestMapping(value="", method = RequestMethod.GET)
	public String loginPageGET() { // 단순히 로그인 페이지 입장
		
		log.trace("loginPage() invoked");
		
		// login 폴더에 login.jsp!
		return "login/login";
		
	} // loginPageGET()
	
} // end class