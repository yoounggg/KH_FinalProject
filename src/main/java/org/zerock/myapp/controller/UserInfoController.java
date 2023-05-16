package org.zerock.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.MsgSendService;
import org.zerock.myapp.service.UserInfoService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
//@AllArgsConstructor

@RequestMapping("/mypage/userInfo/*")
@Controller
public class UserInfoController {
	
	@Setter(onMethod_=@Autowired) // 서비스 주입
	private UserInfoService service;
	
	@Setter(onMethod_=@Autowired) // 핸드폰 인증 서비스 주입
	private MsgSendService msgservice;

	
	//1. 회원상세조회
	@GetMapping("/{id}")
	public String userDetail(@PathVariable("id") String id, Model model) throws ControllerException {
		log.trace("userDetail({},{}) invoked.", id, model);

		try {
			MemberDTO dto = this.service.userDetail(id);
			model.addAttribute("details", dto); // 모델 == details
			
			return "mypage/userInfo";
			
		} catch (ServiceException e) {
			throw new ControllerException(e);
		} // try-catch

	} // userDetail
	
//	===========================================================================	
	//2. 회원 정보 수정	(비밀번호 제외)
	
	@PostMapping("/update")
	public String updateUser(MemberDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("updateUser({},{}) invoked.", dto, rttrs);

		try {
			boolean success = this.service.updateUser(dto);
			log.info("\t+success:{}", success);
			
			rttrs.addAttribute("result", (success)? "success" : "failure");
			
			return "redirect:/mypage/userInfo/" + dto.getId();
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-with
	} // updateUser
	
	@GetMapping("/update") //-> 상세조회화면도 get 해야함
	public void updateUser() {
		log.trace("updateUser() invoked.(회원정보수정)");
	} // updateUser
	
//	===========================================================================
	//3. 휴대폰 인증
	
	@GetMapping("/{id}/phoneCheck")
	public @ResponseBody String sendSMS(@RequestParam("tel") String userPhoneNumber) { // 휴대폰 문자 보내기
		int randomNumber = (int)((Math.random()*(9999-1000+1))+1000); // 난수 생성
		
		msgservice.msgSend(userPhoneNumber, randomNumber);
		
		return Integer.toString(randomNumber);
		
	} // sendSMS
	
	
//	===========================================================================
	//4. 회원 정보 삭제
		
	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id") String id, RedirectAttributes rttrs) throws ControllerException{
		log.trace("deleteUser({}, {}) invoked", id, rttrs);
		
		try {
			boolean success = this.service.deleteUser(id);
			log.info("\t+success:{}", success);
			
			rttrs.addAttribute("result", (success)? "success" : "failure");
			
			return "redirect:/login/logout"; // 탈퇴하면 로그아웃 해야만 함
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // deleteUser()
	
//	===========================================================================	
	//4-1. 기존비밀번호와 일치하는지 확인

		@PostMapping("/{id}/checkPw")
		public ResponseEntity<String> checkPw(@RequestBody Map<String, String> request, @PathVariable("id") String id) throws Exception {	
		
		log.info("checkPw({}, {}) invoked", request.get("password"), id);
			
			String result = "";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			MemberDTO dto = this.service.userDetail(id);
			log.info("dto({})", dto);
			
			log.info("DB 회원의 비밀번호:" + dto.getPassword());
			log.info("form에서 받아온 비밀번호: " + request.get("password"));
			
			
			if(passwordEncoder.matches(request.get("password"), dto.getPassword())) {
				log.info("result({}, {})", request.get("password"),dto.getPassword() );
				result = "true";

			}else {
				log.info("result({}, {})", request.get("password"), dto.getPassword());
				result = "false";

			} // if else

			return ResponseEntity.ok(result);
		} // checkPw
		

//	===========================================================================	
//	===========================================================================
	//5. 비밀번호 변경
	
	@GetMapping("/{id}/changePw")
	public String changePw(@PathVariable("id") String id) {
		log.info("changePw() invoked(비밀번호 변경 페이지)");
		
		return "mypage/modifypw";
	} // modifypw
	
	
	//4-2 새로운 비밀번호 변경
	@PostMapping("/{id}/changedPw")
	public ResponseEntity<Boolean> modifyPw(@RequestBody Map<String, String> request, MemberDTO dto, @PathVariable("id") String id, RedirectAttributes rttrs) throws Exception{
		log.info("modifyPw({},{}) invoked (비밀번호 변경 요청)", request.get("password"), id);
		
		//인코딩 전 새로받은 비번
		String securePw = "";
		String password = request.get("password");
		log.info(request.get("password"));
		log.info(password);
		
		//인코딩
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		securePw = encoder.encode(password);
		log.info("암호화 후 새 비번: " + securePw);
		
//		session.setAttribute(password, securePw);
		dto.setPassword(securePw); // 인코딩된 비번 집어넣기
		log.info("dto({}) ", dto);
		
		try {
			boolean success = this.service.modifyPw(dto);
			log.info("\t+success:{}", success);		
			log.info(success);
			
			return ResponseEntity.ok(success);
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-with
		
	} // changePw
	

//===================================================================================================	
//	@PostMapping("/delete")
//	public String deleteUser(MemberDTO dto, HttpSession session, RedirectAttributes rttrs) throws ControllerException{
////	public String deleteUser(String id, Model model) throws ControllerException, ServiceException{
////	public String deleteUser(@RequestBody Map<String, String> requestBody, HttpSession session, RedirectAttributes rttrs) throws ControllerException{
//		log.trace("deleteUser({}, {}) invoked", dto, session, rttrs);
//
////		String id = requestBody.get("id");
////		MemberDTO dto = new MemberDTO();
////		dto.setId(id);
////		MemberDTO mdto = (MemberDTO) session.getAttribute("member");
////	    // 세션에서 id 얻고
////	    String member_id = mdto.getId();
////	    // 두 개가 같지 않으면
////	    if (!member_id.equals(id)) {
////	        rttrs.addFlashAttribute("msg", false);
////	        return "redirect:/mypage/userInfo/{id}";
////	    }
////
////	    try {
////	        this.service.deleteUser(id);
////	    } catch (ServiceException e) {
////	        // TODO Auto-generated catch block
////	        e.printStackTrace();
////	    }
////	    session.invalidate();
////	    return "redirect:/main";
//		
//		
//		
//		MemberDTO mdto = (MemberDTO) session.getAttribute("details");
//		//세션에서 id 얻고
//		String id = mdto.getId();
//		//MemberDTO에 담긴 id 얻고
//		String dto_id = dto.getId();
//		//두개가 같지 않으면 
//		if(!(id.equals(dto_id))) {
//			rttrs.addFlashAttribute("msg", false);
//			return "redirect:/mypage/userInfo/{id}"; // 다시 회원 정보 수정페이지로 
//		}
//		//두개가 같으면
//		try {
//			this.service.deleteUser(dto);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		session.invalidate();
//		return "redirect:/main";
//		
//		
////		MemberDTO dto = this.service.userDetail(id);
////		model.addAttribute("deletedetails", dto); // 모델 == details
////		String deleteid = dto.getId();
////		
////		this.service.deleteUser(deleteid);
////		return "redirect:/login/logout";
//
//		
//	}
	
	
	


} // end class
