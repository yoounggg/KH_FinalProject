package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.service.KakaoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2


@Controller
public class KakaoController {
	private KakaoService kakaoService;
	
	@GetMapping("/signup/main")
    public String signupMain() {
	log.trace("signupMain invoked");
		
        return "signup/main";
    }

    @GetMapping("/signup/kakao")
    public void kakaoLogin() throws Throwable {
    	log.trace("kakaoLogin() invoked");
        // 카카오 로그인 처리
    }

    @GetMapping("/signup/kakao/callback")
    public String kakaoLoginCallback(@RequestParam String code) throws Throwable {
    	log.trace("code : {} invoked",  code);
    	
    	String kakaoToken = kakaoService.getAccessToken(code);
    	
    	log.trace("kakaoToken : {} invoked", kakaoToken);
    	
        // 카카오 로그인 후 추가 정보 입력 페이지로 이동
        return "redirect:/signup/addinfo";
    }

    @GetMapping("/signup/addinfo")
    public void addInfo() throws Throwable {
        // 추가 정보 입력 처리
    }
	
} // end class
