package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zerock.myapp.domain.MemberDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

//@Component // 빈 등록! 
public class CartInterceptor implements HandlerInterceptor{

	//로그인 시 session "member"라는 key로 로그인 사용자 정보 데이터를 저장해뒀음
	//member데이터가 있으면 로그인 성공해서 controller로 이동, 없으면 다시 메인으로 리다이렉트
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.trace("preHandle(request, response, {}) invoked", handler);
		
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("member");
		
		if(dto == null) {
			log.info("장바구니는 로그인 후 이용바랍니다.");
			response.sendRedirect("/main");
			
			return false;
		} else {
			return true;
		} // if else

	} // preHandle

} // end class
