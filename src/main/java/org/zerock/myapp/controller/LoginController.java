package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.LoginDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login") 
@Controller
public class LoginController {

	@RequestMapping(value="", method = RequestMethod.GET)
	public String loginPageGET() { // 단순 로그인 화면으로 진입
		
		log.trace("loginPage() invoked");
		
		// login 폴더에 login.jsp!
		return "login/login";
		
	} // loginPageGET()
	
    @PostMapping(value="")
    public String loginPost(HttpServletRequest request, LoginDTO loginDTO, RedirectAttributes rttr) throws Exception {

        log.trace("memberLogin 메소드에 진입하였습니다.");
        log.info("전달된 데이터는 {}입니다.", loginDTO);
        
        return null;
        
    } // loginPost()
	
} // end class