package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.MemberVO;
import org.zerock.myapp.service.MemberService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/signup") 
@Controller
public class MemberControllerHG { // 회원가입 페이지 호출
	
	
	@Setter(onMethod_=@Autowired)
	private MemberService memberservice;
	
	//회원가입 페이지로 이동만!
	@GetMapping("/info")
	public void signupGET() {
		log.trace("signupGET() invoked(회원가입 페이지로 이동)");
		
		
	} // joinGET
	
	
	//회원가입 서비스로 가는 메소드(POST) 들어가야 함
	@PostMapping("/info")
	public String signupPOST(MemberVO memberVO) throws Exception {
		log.trace("signupPOST() invoked(회원가입 서비스 실행)");
		
		//회원가입 서비스 실행
		memberservice.memberSignup(memberVO);
		
		log.trace("memberPOST : {} invoked 성공", memberVO);
		
		return "redirect:/signup/complete";
		
	} // signupPOST
	

	@GetMapping("/complete")
	public void signupComplete() throws Exception{
		log.trace("signupComplete() invoked 완료화면 get");
				
	}
	
} // end class


