package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.service.HpCheckService;
import org.zerock.myapp.service.MsgSendService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Controller

@Log4j2
@NoArgsConstructor
public class HpCheckController {

	@Setter(onMethod_ = { @Autowired })
	HpCheckService hpCheckService;

	@PostMapping("/signup/infoc")
	public @ResponseBody int hpCheck(@RequestParam("tel") String tel) {
		log.trace("tel invoked 핸드폰 중복확인", tel);

		int cntTel = hpCheckService.hpCheck(tel);

		log.trace("cntTel : {} invoked 핸드폰 중복확인", cntTel);

		return cntTel;
		
	} // hpCheck
	
	@Setter(onMethod_ = { @Autowired })
	MsgSendService msgCheckService;
	
	@GetMapping("/signup/info/telCheck")
	public @ResponseBody String msgSend(@RequestParam("tel") String userPhoneNumber) {	//문자 보내기
		int randomNumber = (int)((Math.random() * 8999 ) + 1000 );			// 난수 1000 ~ 9999
		
		msgCheckService.msgSend(userPhoneNumber, randomNumber);
		
		log.trace("userPhoneNumber : {} , ramdomNumber : {}  " , userPhoneNumber, randomNumber );
		
		return Integer.toString(randomNumber);
		
	} // msgSend
	
//	========================================================================================================================
	
} // end class
