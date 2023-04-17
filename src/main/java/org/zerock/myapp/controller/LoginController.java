package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.LoginDTO;
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
	@RequestMapping(value="", method = RequestMethod.GET)
	public String loginPageGET() {
		
		log.trace("loginPage() invoked");
		
		// login 폴더에 login.jsp!
		return "login/login";
		
	} // loginPageGET()
	
	// 테스트용
    @PostMapping(value="")
    public String loginPost(
    		HttpServletRequest request, 
    		LoginDTO loginDTO, RedirectAttributes rttr
    ) throws Exception {

//        log.trace("memberLogin 메소드에 진입하였습니다.");
//        log.info("전달된 데이터는 {}입니다.", loginDTO);
        
    	// 세션 생성
        HttpSession session = request.getSession();
        
        // 서버로부터 전달 받은 인자값으로 loginDTO를 사용,
        // memberLogin 메소드로 초기화 -> 메소드 요청 이후 MemberMapper의 쿼리가 실행
        // -> 쿼리 실행 결과값이 담긴 LoginDTO 값을 login_Dto에 저장!
        // 변수명 수정해야 함@@@@
        LoginDTO login_Dto = memberService.memberLogin(loginDTO);
       
        return null;
        
    } // loginPost()
	
} // end class