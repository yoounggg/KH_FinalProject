package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FaqDTO;
import org.zerock.myapp.domain.FaqVO;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.FaqSearchService;
import org.zerock.myapp.service.FaqService;
import org.zerock.myapp.service.NoticeSearchService;
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
			List<NoticeDTO> list = this.service2.getListPaging(cri);
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
			NoticeDTO vo = this.service2.get(no);
			model.addAttribute("notice", vo);
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // get()
	
	
//	================================================================================
	
	private FaqSearchService service3;
	
	@GetMapping("/search") // 
	public String faqSearchList (Criteria cri, Model model) throws ServiceException {
		log.info("faqSearchList({}) invoked.", cri);
		
		
		List<FaqDTO> list = this.service3.faqSearchList(cri);
		log.info("searchList:" + list);
		
		if(!list.isEmpty()) {
			model.addAttribute("searchList", list);
			
			log.info("searchList: " + list);
			
		} else {
			
			model.addAttribute("emptylist", "empty");
			
			return "/help/search"; 
		} // if-else
		
		//페이징
		model.addAttribute("__PAGE_MAKER__", new PageDTO(cri, service3.totalFaq(cri)));
		
		return "/help/search";
	} // searchproductList
	
	
//	================================================================================
	
	
	private NoticeSearchService service4;
	
	@GetMapping("/search2") // 
	public String NoticeSearchList (Criteria cri, Model model) throws ServiceException {
		log.info("faqSearchList({}) invoked.", cri);
		
		
		List<NoticeDTO> list = this.service4.noticeSearchList(cri);
		log.info("searchList2:" + list);
		
		if(!list.isEmpty()) {
			model.addAttribute("searchList2", list);
			
			log.info("searchList: " + list);
			
		} else {
			
			model.addAttribute("emptylist", "empty");
			
			return "/help/search2"; 
		} // if-else
		
		//페이징
		model.addAttribute("__PAGE_MAKER__", new PageDTO(cri, service4.totalNotice(cri)));
		
		return "/help/search2";
	} // searchproductList
	
	
//	================================================================================
	
	@GetMapping("/guide")
	public void guide() { 
		log.trace("guide() invoked (이용안내 메인 진입)");
		
	} // guide
	
} // end class
