package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FaqVO;
import org.zerock.myapp.domain.NoticeVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.FaqService;
import org.zerock.myapp.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@AllArgsConstructor
@RequestMapping("/help")
public class HelpController {
	
	@GetMapping("/terms")
	public void terms() throws Exception {
		log.trace("terms() invoked");
		
	} // terms

	@GetMapping("/privacy")
	public void privacy() throws Exception {
		log.trace("privacy() invoked");
		
	} // privacy
	
	
//	============================
//	[별이]매핑추가
	
	
	private FaqService service1;
	
	@GetMapping("/faq")
	public void faqList(Criteria cri, Model model) throws ControllerException {
		log.trace("list({}, {}) invoked. (faq 메인 진입)", cri, model);
		
		try {
            List<FaqVO> list = this.service1.getListPaging(cri);
            model.addAttribute("faqList", list); // view로 날아갈 model 상자 안에 model 데이터를 담음
            
            
            int totalAmount = this.service1.getTotal();
            PageDTO pageDTO = new PageDTO(cri, totalAmount);
            model.addAttribute("pageMaker", pageDTO);
            
        } catch (Exception e) {
            throw new ControllerException(e);
        } // try-catch
		
	} // faqList
	
	
//	================================================================================
	
	private NoticeService service2;
	
	@GetMapping("/notice")
	public void notice (Criteria cri, Model model) throws ControllerException {	// 게시판 전체 목록 조회 요청 처리 핸들러
			
		log.trace("list({}, {}) invoked.", cri, model);
			
		try {
			List<NoticeVO> list = this.service2.getListPaging(cri);
			model.addAttribute("noticeList", list); // view로 날아갈 model 상자 안에 model 데이터를 담음
				
				
			int totalAmount = this.service2.getTotal();
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			model.addAttribute("pageMaker", pageDTO);
				
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
			
	} // list()
	
	
	
	@GetMapping("/get")
	public void get(@RequestParam("no") Integer no, Model model) throws ControllerException {
		log.trace("get({}, {}) invoked.", no, model);
		
		try {
			NoticeVO vo = this.service2.get(no);
			model.addAttribute("notice", vo);
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // get()
	
//	================================================================================
	
	@GetMapping("/guide")
	public void guide() { 
		log.trace("guide() invoked (이용안내 메인 진입)");
		
	} // guide
	
} // end class
