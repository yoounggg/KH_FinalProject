package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.service.MemberService;
import org.zerock.myapp.service.NaverService;
import org.zerock.myapp.service.SocialMemberService;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class NaverController {

	// 네아로 -> NaverService
	private NaverService naverService;
	private String apiResult = null;
	
	@Autowired
	private SocialMemberService socialMemberService;
	
	@Autowired
	private MemberService memberService;
	
//	-------------------------------------------------------------------------------------------------
	
	@Autowired
	private void setNaverService(NaverService naverService) {
		
		this.naverService = naverService;
	
	} // setNaverService()
	
//	-------------------------------------------------------------------------------------------------
	
	// 로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/login/naver", method = { RequestMethod.GET, RequestMethod.POST })
		public String naverLogin(Model model, HttpSession session) throws Exception {
		
		log.trace("네아로: naverLogin({}, {}) invoked.", model, session);
		
		// 네이버 아이디로 인증 URL을 생성하기 위하여 NaverBO클래스의 getAuthorizationUrl() 호출
		String naverAuthUrl = naverService.getAuthorizationUrl(session);
		
		log.info("네이버 naverAuthUrl : {}", naverAuthUrl);

		model.addAttribute("naverAuthUrl", naverAuthUrl);
		
		return "/login/Login_Main";
		
	} // naverLogin()
	
	// 네이버 로그인 성공 시 callBack 호출 메소드
	@RequestMapping(value = "/login/callBack", method = { RequestMethod.GET, RequestMethod.POST })
		public String callBack
			(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request)
				throws Exception {
		log.info("네이버 아이디로 로그인 callback() 호출되었습니다.");

		OAuth2AccessToken oauthToken;
	
		oauthToken = naverService.getAccessToken(session, code, state);
		
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverService.getUserProfile(oauthToken); //String형식의 json데이터
		/** apiResult json 구조
		{
			"resultcode":"00",
			"message":"success",
			"response":{
				"id":"33666449","nickname":"~~~","age":"~~","gender":"~","email":"~~ @naver.com","name":"~~~"
			}
		}
		**/
		
		// 2. String 형식인 apiResult -> json
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(apiResult);

		log.info("apiResult의 값입니다. {}", apiResult);
		
		JSONObject jsonObj = (JSONObject) obj;
	
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		
		// 4. DTO 객체에 데이터 넣어주기
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId((String) response_obj.get("id"));
		memberDTO.setName((String)response_obj.get("name"));
		memberDTO.setEmail((String)response_obj.get("email"));
		
		boolean searchMember = socialMemberService.searchMember(memberDTO);
		
		if (searchMember == false) {			// 회원 조회 결과 X

			log.trace("socialMemberService.insertNaverMember()가 호출되었습니다!");
			
			socialMemberService.insertNaverMember(memberDTO);
			
			return "redirect:/signup/addinfo";
			
		} else {								// 회원 조회 결과 O
			
			log.trace("searchMember() 수행 결과, 중복된 아이디 혹은 이메일이 존재합니다. 로그인을 실행합니다.");
			
			// 로그인 처리
			MemberDTO loginMemberDTO = memberService.memberLogin(memberDTO);
	        
			session = request.getSession();
			
	        session.setAttribute("member", loginMemberDTO);
			
			return "redirect:/main";
			
		} // if-else
		
//		model.addAttribute("result", apiResult);
		
	} // callBack()
	
//	------------------------------------------------------------------------------------------------
	// 회원 가입 페이지용
	
	// 회원가입 첫 화면 요청 메소드
	@RequestMapping(value = "/signup/naver", method = { RequestMethod.GET, RequestMethod.POST })
		public String naverSignUp(Model model, HttpSession session) throws Exception {
		
		log.trace("네아로: naverLogin({}, {}) invoked.", model, session);
		
		// 네이버 아이디로 인증 URL을 생성하기 위하여 NaverBO클래스의 getAuthorizationUrl() 호출
		String naverAuthUrl = naverService.getAuthorizationUrl(session);
		
		log.info("네이버 naverAuthUrl : {}", naverAuthUrl);

		model.addAttribute("naverAuthUrl", naverAuthUrl);
		
		return "/signup/main";
		
	} // naverSignUp()
	
	// 네이버 callBack 호출 메소드
	@RequestMapping(value = "/signup/callBack", method = { RequestMethod.GET, RequestMethod.POST })
		public String signupCallBack
			(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request)
				throws Exception {
		log.info("네이버 아이디로 로그인 callback() 호출되었습니다.");

		OAuth2AccessToken oauthToken;
	
		oauthToken = naverService.getAccessToken(session, code, state);
		
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverService.getUserProfile(oauthToken); //String형식의 json데이터
		/** apiResult json 구조
		{
			"resultcode":"00",
			"message":"success",
			"response":{
				"id":"33666449","nickname":"~~~","age":"~~","gender":"~","email":"~~ @naver.com","name":"~~~"
			}
		}
		**/
		
		// 2. String 형식인 apiResult -> json
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(apiResult);

		log.info("apiResult의 값입니다. {}", apiResult);
		
		JSONObject jsonObj = (JSONObject) obj;
	
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		
		// 4. DTO 객체에 데이터 넣어주기
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId((String) response_obj.get("id"));
		memberDTO.setName((String)response_obj.get("name"));
		memberDTO.setEmail((String)response_obj.get("email"));
		
		boolean searchMember = socialMemberService.searchMember(memberDTO);
		
		if (searchMember == false) {			// 회원 조회 결과 X

			log.trace("socialMemberService.insertNaverMember()가 호출되었습니다!");
			
			socialMemberService.insertNaverMember(memberDTO);
			
			return "redirect:/signup/addinfo";
			
		} else {								// 회원 조회 결과 O
			
			log.trace("searchMember() 수행 결과, 중복된 아이디 혹은 이메일이 존재합니다. 로그인을 실행합니다.");
			
			// 로그인 처리
			MemberDTO loginMemberDTO = memberService.memberLogin(memberDTO);
	        
			session = request.getSession();
			
	        session.setAttribute("member", loginMemberDTO);
			
			return "redirect:/main";
			
		} // if-else
		
//		model.addAttribute("result", apiResult);
		
	} // signupCallBack()

} // end class