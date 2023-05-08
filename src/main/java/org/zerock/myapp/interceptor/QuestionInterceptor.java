package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.zerock.myapp.domain.MemberDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

//@Component // 빈 등록! 
public class QuestionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		log.trace("preHandle(req, res, {} invoked", handler);

		HttpSession session = req.getSession();
		MemberDTO memberId = (MemberDTO) session.getAttribute("member");

		if (memberId == null) {
			log.info("memberId : {} ", memberId);
			res.sendRedirect("/main");

			return false;

		}
		return true;

	} // preHandle

} // end class
