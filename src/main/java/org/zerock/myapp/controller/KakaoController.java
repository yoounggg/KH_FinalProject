package org.zerock.myapp.controller;

import java.util.Map;

import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.service.KakaoService;
import org.zerock.myapp.service.SocialMemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2

@Controller
public class KakaoController {

	@Autowired
	private KakaoService kakaoService;
	@Autowired
	private SocialMemberService socialMemberService;

	@GetMapping("/signup/main")
	public String signupMain() {
		log.trace("signupMain invoked");

		return "signup/main";

	} // signupMain

	@GetMapping("/signup/kakao/callback")
	public String kakaoLoginCallback(@RequestParam String code, Model model) throws Throwable {

		log.trace(" code : {} invoked", code);

		String access_token = kakaoService.getAccessToken(code);

		Map<String, Object> userinfo = kakaoService.getUserInfo(access_token);

		log.trace("access_token : {}  invoked", access_token);

		// SocialMemberDTO 객체 생성 후 API로부터 얻은 사용자 정보를 DTO에 저장
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId((String) userinfo.get("id"));
		memberDTO.setEmail((String) userinfo.get("email"));
//		socialMemberDTO.setProfile((String) userinfo.get("profile"));
//		socialMemberDTO.setNickname((String) userinfo.get("nickname"));
		
		// SocialMemberDTO 객체를 이용하여 회원가입을 진행합니다.
		socialMemberService.kakaoSignup(memberDTO);

		// 카카오 로그인 후 추가 정보 입력 페이지로 이동
		return "redirect:/signup/addinfo";

	} // kakaoLoginCallback
	
	@GetMapping("/signup/addinfo")
	public String addInfoGet() throws Exception{
		log.trace("addInfo invoked");
		
		return "/signup/addinfo";
	} //addInfoGet
	
	@PostMapping("/signup/addinfo")
	public String addInfo(MemberDTO memberDTO) throws Throwable {
		log.trace("signupAddinfo() invoked(회원가입 서비스 실행)");

		socialMemberService.kakaoSignupAddInfo(memberDTO);
		
		log.trace("memberPOST : {} invoked 성공",memberDTO);
		
		return "redirect:/signup/socialComplete";
	} // addInfo


	@GetMapping("/complete")
	public void signupComplete() throws Exception{
		log.trace("signupComplete() invoked 완료화면 get");
				

	} // signupComplete
} // end class
