package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.QuestionDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.QuestionService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2

@RequestMapping("/help")
@NoArgsConstructor
public class QuestionController {

	@Setter(onMethod_ = @Autowired)
	private QuestionService questionService;
	
	@GetMapping("/write") // 1:1 문의 글 등록페이지
	public void writeGet() {
		log.trace("1:1 문의 글 등록 페이지 writeGet() invoked");
		
	} // writeGet

	@PostMapping("/write")    // 1:1 문의 글 등록
	public String writePost(QuestionDTO questionDTO, HttpServletRequest req, ModelMap model) {
	    log.trace("1:1 문의 글 등록 writePost() invoked");

	    HttpSession session = req.getSession();
	    MemberDTO memberId = (MemberDTO)session.getAttribute("member");
	    String id = memberId.getId();

	    questionDTO.setId(id);  // id 설정

	    questionService.write(questionDTO);

	    
	    model.put("questionDTO", questionDTO);  // 모델에 데이터 추가

	    log.trace("model : {} " , model);
	    
	    
	    return "redirect:/help/question";
	} // writePost

	
	@GetMapping("/question")
	public void listGet(Model model, HttpServletRequest req) throws ControllerException {
		log.info("1:1문의 메인페이지 : ", model);
		
		HttpSession session = req.getSession();
	    MemberDTO member = (MemberDTO)session.getAttribute("member");
	    String memberId  = member.getId();
		log.info("////////////////////////////////////////////"+memberId);
	    
		model.addAttribute("list", questionService.getListByParam(memberId));
		log.info(model);
		
	} // listGet
	
	
} // end class
