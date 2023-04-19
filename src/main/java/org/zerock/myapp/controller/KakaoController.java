package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2


@Controller
public class KakaoController {

	@GetMapping("/signup/mainc")
	public void kakaoLogin(@RequestParam(value = "code", required = false) String code ) throws Throwable{
		
		log.trace("code : {} invoked.", code);
		
		
		
	} // kakaoLogin
	
} // end class
