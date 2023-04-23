package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("") // base uri
@Controller // 이 클래스는 컨트롤러임
public class MainController { // 홈페이지 기본기능 요청 관리
	
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public String mainPage() { // 단순히 메인 페이지 입장
		log.trace("mainPage() invoked(모야모과 메인 페이지)");
		
		return "main";
	} // mainPage
	
	@RequestMapping(value="/terms", method = RequestMethod.GET)
	public String termsPage() { 
		log.trace("termsPage() invoked(약관 및 정책)");
		
		return "terms";
	} // termsPage
	
	@RequestMapping(value="/privacy", method = RequestMethod.GET)
	public String privacyPage() { 
		log.trace("privacyPage() invoked(개인정보 취급방침)");
		
		return "privacy";
	} // privacyPage
	
//	=============================================================
//	[별이] help 메인 추가
	@RequestMapping(value="/help/main", method = RequestMethod.GET)
	public String help() { 
		log.trace("help() invoked(고객센터 메인 진입)");
		
		return "/help/main";
	} // privacyPage

} // end class
