package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.service.MemberService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/signup") 
@Controller
public class SignupController { // 회원가입 페이지 호출
	
	
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
	
	
	//로그인 페이지로 이동만!
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() {
		log.trace("loginGET() invoked(로그인 페이지로 이동)");
		
	} // loginGET
	
	// 메인페이지에 있는 로그아웃을 작동할 수 있게 하는 메서드 (세션에 저장된 사용자 정보를 지울수 있게 해줘야 함)
		//수업시간에는 인증인가할 때 logincontroller에 dummylogout했었는데 .. 우선 logout 버튼 누르면 세션파괴하고 다시 메인으로 돌아오는 것만
//	    @RequestMapping(value="/logout", method=RequestMethod.GET) // main.jsp에서 a태그에 경로로 /logout으로 줌, a태그의 요청은 GET임
//	    public String logoutMainGET(HttpServletRequest request) throws Exception{ // 세션스콥이므로 HttpServletRequest타입 사용
//	    	log.trace("logoutMainGET() invoked(로그아웃 서비스 실행)");
//	    	
//	    	HttpSession session = request.getSession();
//	    	
//	    	session.invalidate(); // 세션파괴
//	    	
//	    	return "redirect: /main";
//	    } // logoutMainGET
		
//		==> 비동기식 로그아웃으로 바꿔서 다시 메소드 작성!
		
		@RequestMapping(value = "/logout", method = RequestMethod.POST)
		@ResponseBody // ajax를 통해서 서버에 요청하는 방식이라서 해당 메서드에 반드시 이 어노테이션을 붙여야 함
		public void logoutPOST(HttpServletRequest request) throws Exception{
			log.trace("logoutMainGET() invoked(로그아웃 서비스 실행)");
			
			HttpSession session = request.getSession();
	    	
	    	session.invalidate(); // 세션파괴
			
		} // logoutPOST

	
} // end class


