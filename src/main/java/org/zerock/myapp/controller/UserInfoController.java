package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.MemberVO;
import org.zerock.myapp.service.UserInfoService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage/userInfo/*")
@Controller
public class UserInfoController {
	
	@Setter(onMethod_=@Autowired) // 서비스 주입
	private UserInfoService service;
	
	
	@GetMapping("/main")
	public String userInfoMain() { // 메인페이지 보려고
		
		return "userInfo";
	}
	
	
	//1. 회원정보수정 메인 페이지 
//	@GetMapping("/userInfo")
//	public String userInfoMain(String id, Model model) {
//		log.trace("userInfoMain() invoked(회원정보수정 페이지)");
//		
//		MemberVO vo = service.userDetail(id);
//		
//		model.addAttribute("member", vo);
//		
//		return "userInfo";
//	} // userInfoMain
//	
//	@PostMapping("/update")
//	public String userInfoUpdate(MemberVO vo) {
//		service.updateMember(vo);
//		
//		return "redirect:userInfo?id="+vo.getId();
//	}
	

}
