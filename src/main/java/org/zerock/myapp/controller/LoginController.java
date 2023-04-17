package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.MemberVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.MemberService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login") 
@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	// 단순 로그인 화면으로 진입
	@RequestMapping(value="", method = RequestMethod.GET)
	public String loginPageGET() {
		
		log.trace("loginPage() invoked");
		
		// login 폴더에 login.jsp!
		return "login/Login_Main";
		
	} // loginPageGET()
	
	// 테스트용
    @PostMapping(value="")
    public String loginPost(
    		HttpServletRequest request, 
    		LoginDTO loginDTO,  Model model,
    		RedirectAttributes rttr
    ) throws Exception {

//        log.trace("memberLogin 메소드에 진입하였습니다.");
//        log.info("전달된 데이터는 {}입니다.", loginDTO);
        
    	// 세션 생성
        HttpSession session = request.getSession();

        // 로그인 성공 / 실패 -> if 문
		try {
			// 영속성 계층 구현
			
			MemberVO memberVO = this.memberService.memberLogin(loginDTO);
			
			log.info("\t+ vo: {}", memberVO);
			
			if(memberVO != null) {	// if 인증 성공
				
				// 인증 성공 시 모델 상자에 담아서!
				model.addAttribute("__AUTH__", memberVO);
				
				return "main";	// 메인화면
				
			} else {			// if 인증 실패
				
				// 인증 실패 시 임시 상자에 담아서!
				rttr.addAttribute("result", "Login Failed");
				
				// redirect 방식으로 login 창으로 밀어줌
				return "redirect:/login/login";	// 인증 실패 결과와 함께 로그인 화면으로 돌아감
				
			} // if-else
			
		} catch (Exception e) {
			
			throw new ControllerException(e);
			
		} // try-catch
        
    } // loginPost()
	
} // end class