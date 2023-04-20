package org.zerock.myapp.controller;

import java.util.Map;

import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.service.KakaoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2

//@RequestMapping("/signup")
@Controller
public class KakaoController {
	
	@Autowired
	private KakaoService kakaoService;
	
	@GetMapping("/signup/main")
    public String signupMain() {
	log.trace("signupMain invoked");
		
        return "signup/main";
    }

    @GetMapping("/signup/kakao")
    public String kakaoLogin() throws Throwable {
    	log.trace("kakaoLogin() invoked");
        
    	return "kakaoCI/login";
    }

    @GetMapping("/signup/kakao/callback")
    public String kakaoLoginCallback(@RequestParam String code, Model model) throws Throwable {
    	log.trace(" code : {} invoked",  code);
    	
    	String access_token = kakaoService.getAccessToken(code);
    	
    	Map<String, Object> userinfo = kakaoService.getUserInfo(access_token);
    	
    	log.trace("access_token :  invoked", access_token);
    	log.trace("userinfo : ",userinfo.toString());	// 사업자 필요
    	
        // 카카오 로그인 후 추가 정보 입력 페이지로 이동
        return "redirect:/signup/addinfo";
//    	return "/signup/addinfo";
    }

    @GetMapping("/signup/addinfo")
    public void addInfo() throws Throwable {
        // 추가 정보 입력 처리
    }
	
} // end class
