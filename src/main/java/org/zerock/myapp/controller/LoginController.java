package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.MemberVO;
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
	@GetMapping("")
	public String loginPageGET() {
		
		log.trace("loginPage() invoked");
		
		return "login/Login_Main";
		
	} // loginPageGET()
	
	// 테스트용
    @PostMapping("/main")
    public String loginPost(MemberDTO memberDTO, HttpServletRequest request, RedirectAttributes rttr
    ) throws Exception {

        log.trace("memberLogin 메소드에 진입하였습니다.");
//        log.info("전달된 데이터는 {}입니다.", memberVO);
        log.info("전달된 데이터는 {}입니다.", memberDTO);
        
        HttpSession session = request.getSession();
        MemberVO m_vo = memberService.memberLogin(memberDTO);
        log.info("\t+ m_vo: {}", m_vo);

        if(m_vo == null) {                                // 일치하지 않는 아이디, 비밀번호 입력 경우
            
            log.info("실패 m_vo=id and password: {}", m_vo);
        	
            rttr.addFlashAttribute("result", m_vo);
            // 로그인 폼에 계속 남아있음
            return "login/Login_Main";
            
        } else {
        	
        	log.info("성공 m_vo=id and password: {}", m_vo);
        	
                session.setAttribute("member", m_vo); // 일반 회원 정보를 세션에 저장
                log.info("m_vo: {}", m_vo);
        	
        	// 메인 화면으로 돌아감
        	return "redirect:/main";
        
        }
        
    } // loginPost()
	
} // end class