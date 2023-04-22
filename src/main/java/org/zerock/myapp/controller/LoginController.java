package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.MemberDTO;
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
    		MemberDTO memberDTO, HttpServletRequest request, RedirectAttributes rttr
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
        		
        		log.trace("비밀번호 일치 여부 판단을 위한 if문에 들어왔습니다.");
                 
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
			HttpSession session
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
	    
	    return "redirect:/main";	// 로그아웃 시 메인으로 이동!
	    
	} // logoutGet()
	
//	---------------------
    
	// # 로그아웃 -> 비동기 방식
    @Async
	@PostMapping("/logout")
	@ResponseBody
	public void logoutPost(HttpServletRequest request) throws Exception {
		
		log.info("비동기방식 로그아웃 메소드인 logoutPost()에 진입하였습니다.");
		
		// session 초기화
		HttpSession session = request.getSession();
		
		// 전체 세션 제거
		session.invalidate();
		
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