package org.zerock.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FarmDTO;
import org.zerock.myapp.domain.FarmVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.FarmService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@SessionAttributes({"farmDTO", "farm"})

@Controller
@RequestMapping("/admin/farm/*")
public class FarmController {
	
	private FarmService service;
	
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) throws ControllerException {	// 게시판 전체 목록 조회 요청 처리 핸들러
		
		log.trace("list({}, {}) invoked.", cri, model);
		
		// 주입 잘 됐는지 확인용
//		Objects.requireNonNull(this.service);
//		log.info("\t+ this.service: {}", this.service);
		
		try {
			// 페이징처리된 현재 pageNum에 해당하는 게시글목록 받아옴
			List<FarmVO> list = this.service.getListPaging(cri);
			model.addAttribute("list", list); // view로 날아갈 model 상자 안에 model 데이터를 담음
			
			
			int total = this.service.getTotal();
			PageDTO pageDTO = new PageDTO(cri, total);
			model.addAttribute("pageMaker", pageDTO);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // list() --> 전체 조회
	
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("no") Integer no, Model model) throws ControllerException {
		log.trace("get({},{}) invoked.", no, model);
		
		try {
			FarmVO vo = this.service.get(no);
			model.addAttribute("farm", vo);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // get()  --> 게시글 상세 조회, 수정 화면 이동
	
	
	@PostMapping("/remove")
	public String get(Integer no, RedirectAttributes rttrs) 
			throws ControllerException {
		
		log.trace("get({},{}) invoked.", no, rttrs);
		
		try {
			boolean success = this.service.remove(no);
			log.info("\t: success : {}", success);
			
			rttrs.addAttribute("result", (success)? "success" : "failure");

			return "redirect:/admin/farm/list";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // remove()  --> 게시글 삭제
	
	
	
	@GetMapping("/register")
	public void register() {
		log.trace("register() invoked.");
	} // register() --> 게시글 등록 화면 이동
	
	
	
	@PostMapping("/register")
	public String register(FarmDTO dto, RedirectAttributes rttrs) 
			throws ControllerException {
		
		log.trace("register({},{}) invoked.", dto, rttrs);
		
		try {
			boolean success = this.service.register(dto);
			log.info("\t: success : {}", success);
			
			rttrs.addAttribute("result", (success)? "success" : "failure");

			return "redirect:/admin/farm/list";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // register()  --> 게시글 등록
	
	
	@PostMapping("/modify")
	public String modify(FarmDTO dto, RedirectAttributes rttrs) 
			throws ControllerException {
		
		log.trace("modify({},{}) invoked.", dto, rttrs);
		
		try {
			boolean success = this.service.modify(dto);
			log.info("\t: success : {}", success);
			
			rttrs.addAttribute("result", (success)? "success" : "failure");

			return "redirect:/admin/farm/list";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // register()  --> 게시글 등록
	
	
	@GetMapping("/temp")
	void temp(
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			@SessionAttribute("farm") FarmVO vo
			) {
		
		log.trace("temp({}, {}, {}, {}) invoked.", session, req, res, vo);
		
	} // temp()  --> 세션 주입 잘 됐는지 확인...
	
		

} // end class