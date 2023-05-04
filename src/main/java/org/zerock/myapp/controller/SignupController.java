package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.service.MemberService;
import org.zerock.myapp.service.NaverService;
import org.zerock.myapp.service.SocialMemberService;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/signup") 
@Controller
@Service
public class SignupController { // 회원가입 페이지 호출
	
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;		
	
	//회원가입 페이지로 이동만!
	@GetMapping("/info")
	public void signupGET() {
		log.trace("signupGET() invoked(회원가입 페이지로 이동)");
		
		
	} // joinGET
	
//	//회원가입 서비스로 가는 메소드(POST) 들어가야 함
//	@PostMapping("/info")
//	public String signupPOST(MemberDTO memberDTO) throws Exception {
//		log.trace("signupPOST() invoked(회원가입 서비스 실행)");
//		
//		//회원가입 서비스 실행
//		memberService.memberSignup(memberDTO);
//		
//		log.trace("memberPOST : {} invoked 성공", memberDTO);
//		
//		return "redirect:/signup/complete";
//		
//	} // signupPOST
	
	
//회원가입 비밀번호 암호화 적용	
//	@Setter(onMethod_=@Autowired)
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/info")
	public String signupPOST(MemberDTO memberDTO) throws Exception {
		log.trace("signupPOST() invoked(회원가입 서비스 실행)");
		
		String beforePw ="";
		String encodePw ="";
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		beforePw = memberDTO.getPassword();		// 비밀번호 가져오기
		log.trace("beforePw : {}", beforePw);
		
		encodePw = bCryptPasswordEncoder.encode(beforePw);	// 비밀번호 인코딩
		log.trace("encodePw : {}", encodePw);
		
		memberDTO.setPassword(encodePw);			// 인코딩된 비밀번호 집어넣기
		memberService.memberSignup(memberDTO);
		log.trace("memberService : {} invoked.", memberDTO);

		
		return "redirect:/signup/complete";
		
	} // signupPOST
	
	
	@GetMapping("/complete")
	public void signupComplete() throws Exception{
		log.trace("signupComplete() invoked 완료화면 get");
				

	} // signupComplete
	
} // end class


