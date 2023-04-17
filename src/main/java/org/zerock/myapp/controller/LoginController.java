package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.MemberVO;
import org.zerock.myapp.service.MemberService;
import org.springframework.ui.Model;

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
	@GetMapping("")
	public String loginPageGET() {
		
		log.trace("loginPage() invoked");
		
		return "login/Login_Main";
		
	} // loginPageGET()
	
	// 테스트용
    @PostMapping("/main")
    public String loginPost(
    		HttpServletRequest request, 
    		LoginDTO loginDTO,
    		Model model,
    		RedirectAttributes rttr
    ) throws Exception {

        log.trace("memberLogin 메소드에 진입하였습니다.");
//        log.info("전달된 데이터는 {}입니다.", memberVO);
        log.info("전달된 데이터는 {}입니다.", loginDTO);
        
        HttpSession session = request.getSession();
        MemberVO m_vo = this.memberService.memberLogin(loginDTO);
       
        if(m_vo == null) {                                // 일치하지 않는 아이디, 비밀번호 입력 경우
            
        	rttr.addAttribute("result", "Login Failed");
            return "redirect:/login/Login_Main";
            
        } // if
        
        // setAttribute -> member로 담아줘야 함
        session.setAttribute("member", m_vo);             // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
        
        return "redirect:main";
        
//        return "login/Login_Main";
        
    } // loginPost()
	
} // end class