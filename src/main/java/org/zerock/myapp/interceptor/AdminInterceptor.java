package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.zerock.myapp.domain.MemberDTO;

import lombok.extern.log4j.Log4j2;
@Log4j2

public class AdminInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) 
			throws Exception {
		
		HttpSession session = req.getSession();
		
		MemberDTO Ivo = (MemberDTO) session.getAttribute("member");
		
		if(Ivo == null || Ivo.getAdminCk() == 0) {	// 관리자 계정 아닌경우
			log.info("불허되는 접근입니다.");
			res.sendRedirect("/main"); 				// 메인페이지로 리다이렉트
			
			return false;
		} // if
		
		return true;	// 관리자 계정 로그인 경우 (Ivo != null && Ivo.getAdminCk() == 1 )
	
	} // preHandle

} // end class
