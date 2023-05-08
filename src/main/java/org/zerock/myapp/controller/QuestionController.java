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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.QuestionDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.QuestionService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2

@NoArgsConstructor
public class QuestionController {

	@Setter(onMethod_ = @Autowired)
	private QuestionService questionService;
	
	@GetMapping("/help/write") // 1:1 문의 글 등록페이지
	public void writeGet() {
		log.trace("1:1 문의 글 등록 페이지 writeGet() invoked");
		
	} // writeGet

	@PostMapping("/help/write")    // 1:1 문의 글 등록
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

	
	@GetMapping("/help/question")
	public void listGet(Model model, HttpServletRequest req) throws ControllerException {	// 1:1 문의 메인
        log.info("1:1문의 메인페이지 : ", model);

        HttpSession session = req.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        boolean isLogin = member != null;
        model.addAttribute("isLogin", isLogin);

        if (isLogin) {
            String memberId = member.getId();
            log.info("-----------------------------------" + memberId);
            model.addAttribute("list", questionService.getListByParam(memberId));
            log.info(model);
        } // if
    
		
	} // listGet
	
	
	@GetMapping("/help/page")
	public void getPage(Integer qno, Model model) {			// 1:1 문의 내용보기
		log.info("getPage() invoked");
		
		model.addAttribute("pageInfo", questionService.getPage(qno));
		
	} // getPage
	
	@GetMapping("/admin/question/list")
	public void adminList(Model model) {					// 관리자 1:1 전체 내역
		log.info("adminList() invoked.");
		
		model.addAttribute("list", questionService.adminList());
		
	} // adminList
	
	@GetMapping("/admin/question/page")
	public void adminGet(Integer qno, Model model) {		// 관리자 1:1문의 내용보기
		log.info("adminGet() invoked");
		
		model.addAttribute("pageInfo", questionService.getPage(qno));
		
	} // adminGet
	
	
	@PostMapping("/admin/question/delete")
	public String adminDelete(Integer qno, RedirectAttributes rttrs) {
		
		questionService.delete(qno);
		
		rttrs.addFlashAttribute("result", "success");
		
		return "redirect:/admin/question/list";
		
	} // adminDelete
	
	
	@GetMapping("/admin/question/write")
	public void adminWrite(Integer qno, Model model) {
		log.info("adminWrite() invoked");
		
	} // adminWrite
	
} // end class
