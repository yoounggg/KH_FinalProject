package org.zerock.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.NoticeSearchService;
import org.zerock.myapp.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@RequestMapping("/admin/notice/*")
@Controller

@SessionAttributes({"notice", "noticeDTO"}) // String 타입의 배열을 인자로 받음!
public class NoticeController {

	private NoticeService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) throws ControllerException {	// 게시판 전체 목록 조회 요청 처리 핸들러
		
		log.trace("list({}, {}) invoked.", cri, model);
		
		// 주입 잘 됐는지 확인용
//		Objects.requireNonNull(this.service);
//		log.info("\t+ this.service: {}", this.service);
		
		try {
			// 페이징처리된 현재 pageNum에 해당하는 게시글목록 받아옴
			List<NoticeDTO> list = this.service.getListPaging(cri);
			model.addAttribute("list", list); // view로 날아갈 model 상자 안에 model 데이터를 담음
			
			
			int totalAmount = this.service.getTotal();
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			model.addAttribute("pageMaker", pageDTO);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // list()

	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("no") Integer no, Model model) throws ControllerException {
		// 매개변수 이름이 전송 파라미터와 어쩔수없이 달라야 한다면
		// 이 RequestParam으로 값을 달라고 할 전송 파라미터를 지정
		
		log.trace("get({}, {}) invoked.", no, model);
		
		try {
			NoticeDTO dto = this.service.get(no);
			
			model.addAttribute("notice", dto);
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // get()
	
	
	@PostMapping("/remove")
	public String remove(Criteria cri, Integer no, RedirectAttributes rttrs) 
			throws ControllerException {
		
		log.trace("remove({}, {}, {}) invoked.", cri, no, rttrs);
		
		try {
			boolean success = this.service.remove(no); 
		
			
			rttrs.addAttribute("result", (success)? "success" : "failure"); 
			//성공했던 실패했든 그대로 있으면 안되고 이동시켜야 하기 때문에 리다이렉션 무조건 
			return "redirect:/admin/notice/list"; // redirect 때문에 string으로 선언한듯
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try catch
	} // remove()
	
	
	@PostMapping("/modify")
	public String modify(Criteria cri, NoticeDTO dto, RedirectAttributes rttrs) 
			throws ControllerException {
		
		log.trace("modify({}, {}, {}) invoked.",cri, dto, rttrs);
		
		try {
			boolean success = this.service.modify(dto);
			log.info("\t: success: {}", success);
			
			rttrs.addAttribute("result", (success)? "success" : "failure"); 
			// KEY = 수정처리결과
			
			return "redirect:/admin/notice/list"; // 실패했든 성공했든 여기로 이동
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // modify()
	
	
	@PostMapping("/register")
	public String register(Criteria cri, NoticeDTO dto, RedirectAttributes rttrs) 
			throws ControllerException {
		
		log.trace("register({}, {}, {}) invoked.", cri, dto, rttrs);
		
		try {
			boolean success = this.service.register(dto);
			log.info("\t: success: {}", success);
		

			// 비지니스 요청 처리용 전송파라미터 전송처리
			rttrs.addAttribute("result", (success)? "success" : "failure"); 
			// KEY = 등록처리결과
			
			return "redirect:/admin/notice/list"; // 실패했든 성공했든 여기로 이동
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // register()
	

	@GetMapping("/temp")
	void temp(
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			@SessionAttribute("notice") NoticeDTO dto
			) {
	
		log.trace("temp({}, {}, {}, {}) invoked.", session, req, res, dto);
		
	} // temp()
	
	
	@ModelAttribute("NoticeDTO")
	NoticeDTO createNoticeDTO() { // 이 메소드는 요청을 처리하는 핸들러 메소드가 아님!
		
		log.trace("createNoticeDTO() invoked.");
		
		NoticeDTO dto = new NoticeDTO();
		dto.setNo(1001);
		dto.setTitle("TEST");
		
		return dto;
		
	} // createNoticeDTO()
	
	@GetMapping("/register")
	public void register() {
		log.trace("register() invoked.");
	} //GetMapping()
	
	
//	=====================================================================================
	
	private NoticeSearchService service2;
	
	@GetMapping("/search") // 
	public String NoticeSearchList (Criteria cri, Model model) throws ServiceException {
		log.info("NoticeSearchList({}) invoked.", cri);
		
		
		List<NoticeDTO> list = this.service2.noticeSearchList(cri);
		log.info("searchList:" + list);
		
		if(!list.isEmpty()) {
			model.addAttribute("searchList", list);
			
			log.info("searchList: " + list);
			
		} else {
			
			model.addAttribute("emptylist", "empty");
			
			return "/admin/notice/search"; 
		} // if-else
		
		//페이징
		model.addAttribute("__PAGE_MAKER__", new PageDTO(cri, service2.totalNotice(cri)));
		
		return "/admin/notice/search";
	} // searchproductList
	
	
} // end class