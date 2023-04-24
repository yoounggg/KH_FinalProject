package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.service.MemberService;
import org.zerock.myapp.service.MsgSendService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login/*") 
@Controller
public class MemberController {
	// 아이디 찾기, 비밀번호 변경을 위한 컨트롤러
	// 각각 인증 방식으로는 휴대폰 인증과 이메일 인증이 존재함
	
	// 아이디 찾기
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;
	
	// 아이디 찾기 휴대폰 인증 번호 받기!
	@Setter(onMethod_ = { @Autowired })
	MsgSendService msgCheckService;
	
	// 아이디 찾기 - DB에 회원 정보(이름, 전화번호) 존재하는 지 확인
	@PostMapping("/findid/idCheck")
	public @ResponseBody int idCheck(@RequestParam("name") String name, @RequestParam("tel") String tel) throws Exception {
		log.trace("사용자가 입력한 값: idCheck({}, {}) activated.", name, tel);
		
		int cntIdCheck = memberService.idCheck(name, tel);
		
		log.trace("아이디 중복 확인: cntIdCheck : ({})" , cntIdCheck);
		
		return cntIdCheck;
		
	} // idCheck()
	
	@GetMapping("/findid/telCheck")
	public @ResponseBody String msgSend(@RequestParam("tel") String userPhoneNumber) {	//문자 보내기
		int randomNumber = (int)((Math.random() * 8999 ) + 1000 );			// 난수 1000 ~ 9999
		
		msgCheckService.msgSend(userPhoneNumber, randomNumber);
		
		log.trace("userPhoneNumber : {} , ramdomNumber : {}  " , userPhoneNumber, randomNumber );
		
		return Integer.toString(randomNumber);
		
	} // msgSend()
	

} // end class
