package org.zerock.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.service.KakaoService;
import org.zerock.myapp.service.MemberService;
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
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/signup/main")
	public String signupMain() {
		log.trace("signupMain invoked");

		return "signup/main";

	} // signupMain

	@GetMapping("/signup/kakao/callback")
	public String kakaoLoginCallback(@RequestParam String code, Model model, HttpServletRequest req) throws Throwable {

		log.trace(" code : {} invoked", code);

		// 토큰 저장
		String access_token = kakaoService.getAccessToken(code);

		// map 객체에 토큰 저장
		Map<String, Object> userinfo = kakaoService.getUserInfo(access_token);

		log.trace("access_token : {}  invoked", access_token);

		// SocialMemberDTO 객체 생성 후 API로부터 얻은 사용자 정보를 DTO에 저장
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId((String) userinfo.get("id"));
		memberDTO.setEmail((String) userinfo.get("email"));

		// 회원가입 여부 확인
		boolean isMember = socialMemberService.isMember(memberDTO);
		
		
		if (isMember == false) {

			// 카카오 로그인 후 추가 정보 입력 페이지로 이동됨
			log.trace("===============================insert====================================");
			socialMemberService.insert(memberDTO);
			return "redirect:/signup/addinfo";
			
		} else {
			// 중복된 아이디나 이메일이 있으면 로그인을 시킴
			log.trace("========================" + isMember + "==========================");
			log.trace("===============================login====================================");
			 // 로그인 세션 처리
			// 메소드 수행 이후 반환 받은 MemberDTO 인스턴스 주소를 MemberDTO 타입으로 저장
			MemberDTO loginMemberDTO = memberService.memberLogin(memberDTO);
	        HttpSession session = req.getSession();
	        session.setAttribute("member", loginMemberDTO);
			
			return "redirect:/main";
			
		} // if-else
	} // kakaoLoginCallback

	@GetMapping("/signup/addinfo")
	public String addInfoGet() throws Exception {
		log.trace("addInfo invoked");

		return "/signup/addinfo";
	} // addInfoGet

	@PostMapping("/signup/addinfo")
	public String addInfo(MemberDTO memberDTO) throws Throwable {
		log.trace("signupAddinfo() invoked(회원가입 추가정보 서비스 실행)");
		
		//추가정보 입력 
		socialMemberService.kakaoSignupAddInfo(memberDTO);

		log.trace("member : {} invoked 성공", memberDTO);

		return "redirect:/signup/socialComplete";
	} // addInfo

	@GetMapping("/signup/socialComplete")
	public void signupComplete() throws Exception {
		log.trace("signupComplete() invoked 완료화면 get");

	} // signupComplete
	
} // end class
