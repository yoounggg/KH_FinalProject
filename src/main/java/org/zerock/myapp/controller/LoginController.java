package org.zerock.myapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.service.MemberService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login/*") 
@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	// 자동 로그인 기능 구현을 위한 필드 추가
	private static final String AUTO_LOGIN_COOKIE = "AUTO_LOGIN"; 	// 자동 로그인을 위한 쿠키의 이름을 저장
	private static final int COOKIE_EXPIRY_DATE = 60 * 60 * 24 * 7; 	// 쿠키 유효 기간 => 7일동안 쿠키 유지

//	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 로그인 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	// # 단순 로그인 화면으로 진입
	@GetMapping("/main")
	public String loginGet() {
		
	    log.trace("단순 로그인 화면으로 진입했습니다. loginPage() invoked");
	    
	    return "login/Login_Main";
	    
	} // loginGet()
	
//	---------------------
	
	// # 로그인 기능 구현 - 암호화 이후!!
    @PostMapping("/main")
    public String loginPost(
    		MemberDTO memberDTO, HttpServletResponse response, HttpServletRequest request, RedirectAttributes rttr, 
    		@RequestParam(value = "autoLogin", required = false) String autoLogin
    ) throws Exception {

        log.trace("비밀번호 암호화 이후의 memberLogin 메소드에 진입하였습니다.");
        log.info("전달된 데이터는 {}입니다.", memberDTO);
        
        // 암호화
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        
        // 세션 생성
        HttpSession session = request.getSession();
        
        // 암호화 전의 암호 (제출 받은 암호 임시 저장 용도)
        String origin_pw = "";
        
        // 암호화 이후 암호 (인코딩 된 암호 임시 저장 용도)
        String encoded_pw = "";

        // 메소드 실행으로 쿼리 수행 이후 반환 받은 MemberDTO 인스턴스 주소를 MemberDTO 타입의 m_dto에 저장해줌!
        MemberDTO m_dto = memberService.memberLogin(memberDTO);
        log.info("\t+ 쿼리문 수행 이후 반환 받은 m_dto: {}", m_dto);

        // m_dto가 null인지 아닌지를 분기하는 if문 작성
        // -> 이때 if 조건문에 해당되지 않는 경우 = m_dto에 저장된 값이 null이라는 의미임
        // -> else의 구현부는 로그인이 실패할 경우 실행될 코드
        if(m_dto != null) {				// 일치하는 아이디가 존재할 경우
        	
        	origin_pw = memberDTO.getPassword();	// 사용자가 입력한 비밀번호
        	log.trace("origin_pw = memberDTO.getPassword() = {}", memberDTO.getPassword());
        	
        	encoded_pw = m_dto.getPassword();       // DB에 저장된 인코딩 된! 비밀번호
        	log.trace("encoded_pw = m_dto.getPassword() = {}", memberDTO.getPassword());
        	
        	// 비밀번호 일치 여부 판단을 위한 if문 작성
        	if(true == bCryptPasswordEncoder.matches(origin_pw, encoded_pw)) {	// BCryptPasswordEncoder의 .matches()로 비밀번호 일치 여부 판단
        		
        		log.trace("[셍나]: 비밀번호 일치 여부 판단을 위한 if문에 들어왔습니다.");
        		
        		// 자동 로그인 처리
        		if (autoLogin != null && autoLogin.equals("on")) {
        			
        			// 쿠키 생성 시작 (세션만 사용하면 웹브라우저 종료 시 사라지기 때문에 쿠키 사용해야 함 -ㅇ-) ------------
        			// 자동 로그인 기능을 위한 쿠키(AUTO_LOGIN_COOKIE) 생성 후, 이름과 값을 지정! 값은 현재 세션 ID로!
        			Cookie autoLoginCookie = new Cookie(AUTO_LOGIN_COOKIE, session.getId());
        			
        			// 쿠키의 유효 기간을 위 상수 필드에 선언한 int 타입의 COOKIE_EXPIRY_DATE로 설정할 수 있도록 저장!
        			autoLoginCookie.setMaxAge(COOKIE_EXPIRY_DATE);
        			
        			// 쿠키 경로를 "/"로 설정 -> 모든 경로에서 쿠키를 사용할 수 있도록 해줌.
        			autoLoginCookie.setPath("/");
        			
        			// 생성한 쿠키를 사용자의 웹 브라우저에 전송해줌.
        			response.addCookie(autoLoginCookie);
        			
        			// 세션에 자동 로그인 정보 저장
        			session.setAttribute(AUTO_LOGIN_COOKIE, "AUTO");
        			
        			log.trace("세션에 자동 로그인 정보를 저장했습니다.");
        	        
        		} // if문 자동 로그인 -> 쿠키 저장을 위한 if문
                 
        		m_dto.setPassword("");		// 인코딩된 비밀번호의 정보를 지워줌
        		session.setAttribute("member", m_dto);	// member 세션에 사용자 정보 저장
        		
        		return "redirect:/main";        // 로그인 성공 시 메인으로!
        		
        	} else {	// 비밀번호 일치 XX
                
                // RedirectAttributes에 담길 데이터 
                // 실패를 의미하는 데이터를 로그인 실패 시 저장
        		rttr.addFlashAttribute("result", 0);	    
        		
        		return "redirect:/login/main";    // 로그인 실패 시 로그인 폼에 남아있음!
        		
        	} // inner-if-else
        
        } else {						// 일치하는 아이디가 존재하지 않을 경우 = login fail
        	
            rttr.addFlashAttribute("result", 0);            
            return "redirect:/login/main";	// 로그인 실패 시 로그인 폼에 남아있음!
        	
        } // outter-if-else
        
    } // loginPost()
    
//	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 로그아웃 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    // # 로그아웃 -> a 태그의 요청은 GET 방식이기 때문에 GET Mapping!
    // session 작업 필요하기 때문에 HttpServletRequest 타입의 매개변수 작성이 필요함!
    @GetMapping("/logout")
	public String logoutGet(
//			HttpServletRequest request
			HttpSession session,
			HttpServletResponse response
	) throws Exception {
		
	    log.trace("logoutPage({}) invoked", session);
	    
	    // 세션 제거(member) 작업이 필요하기 때문에, HttpSession 타입의 session 변수 및 초기화 진행!
//	    HttpSession session = request.getSession();
	    
	    // 세션 제거에는 invalidate()와 removeAttribute() 두 가지의 메소드 사용 가능!
	    // & invalidate() = This method is used to invalidate the entire session.
	    // Once a session is invalidated, it can no longer be used, and a new session needs to be created for any further interaction between the client and the server
	    // & removeAttribute() = This method is used to remove a specific attribute from the session, can remove a single attribute by specifying its name as an argument
	    session.invalidate();
//	    session.removeAttribute("member");
	    
	    // 자동 로그인용 쿠키 제거 -ㅇ-
	    // 자동 로그인용 쿠키의 이름을 AUTO_LOGIN_COOKIE로 지정 + 값을 빈 문자열로 설정
	    Cookie autoLoginCookie = new Cookie(AUTO_LOGIN_COOKIE, "");
	    
	    // 쿠키의 유효 기간을 0으로 설정 -> 쿠키를 즉시 만료
	    autoLoginCookie.setMaxAge(0);
	    
	    // 쿠키 경로를 "/"로 설정 -> 모든 경로에서 쿠키를 사용할 수 있도록 해줌.
	    autoLoginCookie.setPath("/");
	    
	    // 생성한 쿠키를 사용자의 웹 브라우저에 전송해줌 
	    // -> 기존에 저장되어 있던 자동 로그인 쿠키는 만료되어 사라지게 됨!
	    response.addCookie(autoLoginCookie);
	    
	    return "redirect:/main";	// 로그아웃 시 메인으로 이동!
	    
	} // logoutGet()
	
//	---------------------
    
//  비동기식으로 하면 로그아웃해도 그 페이지에 남아있기는 하나, 원래 정보 또한 남아있음 ㅠ 어케 할 건지 고민!!
	// # 로그아웃 -> 비동기 방식
    @Async
	@PostMapping("/logout")
	@ResponseBody
	public void logoutPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("비동기방식 로그아웃 메소드인 logoutPost()에 진입하였습니다.");
		
		// session 초기화
		HttpSession session = request.getSession();
		
		// 전체 세션 제거
		session.invalidate();
		
	    // 자동 로그인용 쿠키 제거 -ㅇ-
	    // 자동 로그인용 쿠키의 이름을 AUTO_LOGIN_COOKIE로 지정 + 값을 빈 문자열로 설정
	    Cookie autoLoginCookie = new Cookie(AUTO_LOGIN_COOKIE, "");
	    
	    // 쿠키의 유효 기간을 0으로 설정 -> 쿠키를 즉시 만료
	    autoLoginCookie.setMaxAge(0);
	    
	    // 쿠키 경로를 "/"로 설정 -> 모든 경로에서 쿠키를 사용할 수 있도록 해줌.
	    autoLoginCookie.setPath("/");
	    
	    // 생성한 쿠키를 사용자의 웹 브라우저에 전송해줌 
	    // -> 기존에 저장되어 있던 자동 로그인 쿠키는 만료되어 사라지게 됨!
	    response.addCookie(autoLoginCookie);
	    
	} // logoutPost()
    
    
//	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 아이디 찾기 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
	// # 단순 아이디 찾기 화면으로 진입
	@GetMapping("/findid")
	public String idGet() {
		
	    log.trace("단순 아이디 찾기 화면으로 진입했습니다. idGet() invoked");
	    
	    return "login/Login_Find_ID";
	    
	} // idGet()
	
	
//	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 비밀번호 변경 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	// # 단순 비밀번호 변경 화면으로 진입
	@GetMapping("/changepw")
	public String pwGet() {
		
	    log.trace("단순 비밀번호 변경 화면으로 진입했습니다. pwGet() invoked");
	    
	    return "login/Login_Change_PW";
	    
	} // pwGet()
    
} // end class


//	--------------------------------------------------------

// 로그인 기능 구현 - 암호화 이전!!
//    @PostMapping("/main")
//    public String loginPost(MemberDTO memberDTO, HttpServletRequest request, RedirectAttributes rttr
//    ) throws Exception {
//
//        log.trace("memberLogin 메소드에 진입하였습니다.");
//        log.info("전달된 데이터는 {}입니다.", memberDTO);
//        
// 세션 생성
//        HttpSession session = request.getSession();
// m_dto에 객체 담기!!
//        MemberDTO m_dto = memberService.memberLogin(memberDTO);
//        
//        log.info("\t+ m_dto: {}", m_dto);
//
//        if(m_dto == null) {                                // 일치하지 않는 아이디, 비밀번호 입력
//            
//            log.info("실패: m_dto = id and password: {}", m_dto);
//        	
// RedirectAttributes에 담길 데이터 
// 실패를 의미하는 데이터를 로그인 실패 시 저장
//            int result = 0;
//            rttr.addFlashAttribute("result", result);
//            
// 로그인 실패 시 로그인 폼에 계속 남아있음
//            return "redirect:/login/main";
//            
//        } else {											// 일치하는 아이디, 비밀번호 입력
//        	
//        	log.info("성공: m_dto = id and password: {}", m_dto);
//        	
//            session.setAttribute("member", m_dto); // 회원 정보를 세션에 저장
//        	
// 로그인 성공 시 메인 화면으로 돌아감
//        	return "redirect:/main";
//        
//        } // if-else
//        
//    } // loginPost()