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

	@GetMapping("/signup/main")
    public String signupMain() {
        return "signup/main";
    }

    @GetMapping("/signup/kakao")
    public void kakaoLogin() throws Throwable {
        // 카카오 로그인 처리
    }

    @GetMapping("/signup/kakao/callback")
    public String kakaoLoginCallback(@RequestParam(value = "code", required = false) String code) throws Throwable {
        // 카카오 로그인 후 추가 정보 입력 페이지로 이동
        return "redirect:/signup/addinfo";
    }

    @GetMapping("/signup/addinfo")
    public void addInfo() throws Throwable {
        // 추가 정보 입력 처리
    }
	
} // end class
