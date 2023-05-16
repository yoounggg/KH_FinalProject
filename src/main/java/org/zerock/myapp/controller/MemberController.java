package org.zerock.myapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.mapper.MemberMapper;
import org.zerock.myapp.service.MemberService;
import org.zerock.myapp.service.MsgSendService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/login/*") 
@Controller
public class MemberController {
	// 아이디 찾기, 비밀번호 변경을 위한 컨트롤러
	// 각각 인증 방식으로는 휴대폰 인증과 이메일 인증이 존재함
	
	// 아이디 찾기 - 이름이랑 전화번호로 회원 정보가 존재하는지 확인
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;
	
	// 아이디 찾기 - 휴대폰 인증 번호!
	@Setter(onMethod_ = { @Autowired })
	MsgSendService msgSendService;
	
	// 아이디 찾기 - 결과 반환
	@Autowired
	private MemberMapper memberMapper;
	
	// 이메일 인증
	@Autowired
	private JavaMailSender javaMailSender;
	
//	---------------------- [아이디 찾기] - 휴대폰 인증 ----------------------------------------------------------------------------------------------
	
	// [핸드폰] 아이디 찾기 - DB에 회원 정보(이름, 전화번호) 존재하는 지 확인
	@PostMapping("/findid/idCheck")
	public @ResponseBody int idCheck(@RequestParam("name") String name, @RequestParam("tel") String tel) throws Exception {
		log.trace("사용자가 입력한 값: idCheck({}, {}) activated.", name, tel);
		
		int cntIdCheck = memberService.idCheck(name, tel);
		
		log.trace("아이디 중복 확인: cntIdCheck : ({})" , cntIdCheck);
		
		return cntIdCheck;
		
	} // idCheck()
	
	
	// [핸드폰] 아이디 찾기 - 핸드폰 번호 인증
	@GetMapping("/findid/telCheck")
	public @ResponseBody String sendSMS(@RequestParam("tel") String userPhoneNumber) {				// 문자 보내기
		int randomNumber = (int)((Math.random() * 8999 ) + 1000 );									// 난수 1000 ~ 9999 생성
		
		msgSendService.msgSend(userPhoneNumber, randomNumber);
		
		log.trace("userPhoneNumber : {} , ramdomNumber : {}  " , userPhoneNumber, randomNumber );
		
		return Integer.toString(randomNumber);
		
	} // msgSend()
	
	
	// [핸드폰] 아이디 찾기 인증 결과 반환
	@PostMapping("/findid/result")
	@ResponseBody
	public String findIdResult(@RequestParam("name") String name, @RequestParam("tel") String tel) {

	    String foundId = memberMapper.findIdResult(name, tel);
	    
	    log.info("휴대폰 인증 - 아이디 찾기 결과 반환 메소드 findIdResult: {}", foundId);
	    
	    return foundId;
	
	} // findIdResult()
	
//	---------------------- [아이디 찾기] - 이메일 인증 ----------------------------------------------------------------------------------------------
	
	// [이메일] 아이디 찾기 - DB에 회원 정보(이름, 이메일) 존재하는 지 확인
	@PostMapping("/findid/idCheck_e")
	public @ResponseBody int idCheck_e(@RequestParam("name") String name, @RequestParam("email") String email) throws Exception {
		log.trace("사용자가 입력한 값: idCheck_e({}, {}) activated.", name, email);
		
		int cntIdCheck_e = memberService.idCheck_e(name, email);
		
		log.trace("아이디 중복 확인: cntIdCheck_e : ({})" , cntIdCheck_e);
		
		return cntIdCheck_e;
		
	} // idCheck_e()
	
	// [이메일] 아이디 찾기 - 이메일 전송
	@GetMapping("/findid/sendMail")
	@ResponseBody
	// 사용자가 입력한 이메일 주소를 매개 변수로 받는 mailCheck()
	public String mailCheck(@RequestParam("email") String email) throws Exception {
		
		// 인증 번호를 위한 6자리 랜덤 난수 생성
		int randomNum = (int) ( Math.random() * 900000 ) + 100000;
		
		log.info("생성된 인증번호: {}", randomNum);
		
        /* 이메일 보내기 - 이메일 데이터 설정! */
        String setFrom = "dhcksehf1@naver.com";
        String setTo = email;
        String setTitle = "[MYMG] 아이디 찾기 인증을 위한 이메일입니다.";
        String setContent = "인증 번호는 [" + randomNum + "]입니다.";
		
        try {
            
            // JavaMailSenderImpl 객체인 mailSender에서
            // MIME 타입의 이메일 메시지를 생성! -> MimeMessage 객체 반환
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            
            // MimeMessageHelper로 이메일 메세지 구성!
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            
            // 보내는 사람
            mimeMessageHelper.setFrom(setFrom);
            // 받는 사람
            mimeMessageHelper.setTo(setTo);
            // 이메일 제목
            mimeMessageHelper.setSubject(setTitle);
            // 이메일 본문
            mimeMessageHelper.setText(setContent,true);
            
            // 보내는 메소드
            javaMailSender.send(mimeMessage);
            
        } catch(Exception e) {
        	
            e.printStackTrace();

		} // try-catch
        
        String authKey = Integer.toString(randomNum);
        
        return authKey;
		
	} // mailCheck()
	
	
	// [이메일] 아이디 찾기 인증 결과 반환
	@PostMapping("/findid/result_e")
	@ResponseBody
	public String findIdResult_e(@RequestParam("name") String name, @RequestParam("email") String email) {

	    String foundId_e = memberMapper.findIdResult_e(name, email);
	    
	    log.info("이메일 인증 - 아이디 찾기 결과 반환 메소드 findIdResult_e: {}", foundId_e);
	    
	    return foundId_e;
	
	} // findIdResult_e()
	
//	---------------------- [비밀번호 변경] - 아이디 조회 ----------------------------------------------------------------------------------------------
	
	// 비밀번호 변경을 위한 DB 회원 정보 조회
	@PostMapping("/changepw/idSearch")
	public @ResponseBody int idSearch(@RequestParam("id") String id) throws Exception {
		log.trace("사용자가 입력한 값: idSearch({}) activated.", id);
		
		int cntIdInq = memberService.idSearch(id);
		
		log.trace("아이디 중복 확인: cntIdCheck : ({})" , cntIdInq);
		
		return cntIdInq;
		
	} // idSearch()
	
	// 임시 비밀번호를 위한 메소드!
	// 영어 대문자 + 소문자 + 숫자 랜덤으로 10자리 문자열 생성
	public String generateTempPw (int length) {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    
	    Random random = new Random();
	    
	    StringBuilder stringBuilder = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	    	
	        int randomIndex = random.nextInt(characters.length());
	        
	        stringBuilder.append(characters.charAt(randomIndex));
	        
	    } // for

	    return stringBuilder.toString();
	    
	} // generateTempPw()
	
	// [핸드폰] 비밀번호 변경 - 임시 비밀번호 휴대폰 전송
	@GetMapping("/changepw/sendTelTempPw")
	// 해당 아이디와 일치하는 휴대폰 번호로 임시 비밀번호 발송
	public @ResponseBody String sendTelTempPw(@RequestParam("id") String id, @RequestParam("phone") String phone) throws Exception {				// 문자 보내기
		
		MemberDTO memberDTO = memberService.getTelById(id);
		
	    // 조회 결과 해당 휴대폰 번호가 존재할 경우
	    if (memberDTO != null) {

	        // memberDTO에서 tel값 뽑아옴!
	        String userPhoneNumber = memberDTO.getTel();

	        // 임시 비밀번호 메소드를 통한 임시 비밀번호
	        String tempPw_p = generateTempPw(10);

	        log.info("tempPw_p()에서 생성된 임시 비밀번호: {}", tempPw_p);

	        // BCryptPasswordEncoder 객체 생성
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	        // 임시 비밀번호 암호화
	        String encryptedTempPw_p = passwordEncoder.encode(tempPw_p);

	        // 암호화된 임시 비밀번호를 사용하여 데이터베이스에 저장하는 로직 추가
	        memberService.updatePw_p(id, encryptedTempPw_p);

	        // 문자 전송!
	        msgSendService.msgSendSein(userPhoneNumber, tempPw_p);

	        log.trace("userPhoneNumber: {} , tempPw: {}  " , userPhoneNumber, tempPw_p);

	        return tempPw_p;
	        
	    } else {
	    	
	        log.info("회원 정보가 존재하지 않습니다.");
	        return null;
	        
	    } // if-else
		
	} // sendTelTempPw()
	
	
	// [이메일] 비밀번호 변경 - 임시 비밀번호 이메일 전송
	@GetMapping("/changepw/sendEmailTempPw")
	@ResponseBody
	// 해당 아이디와 일치하는 이메일 주소로 임시 비밀번호 발송
	public Map<String, String> sendEmailTempPw(@RequestParam("id") String id) throws Exception {
		
		MemberDTO memberDTO = memberService.getEamilById(id);
		
		// 임시 비밀번호를 담을 Map 객체를 생성!
		Map<String, String> resultMap = new HashMap<>();
		
		// 조회 결과 해당 이메일 주소가 존재할 경우
		if (memberDTO != null) {
			
			// memberDTO에서 email값 뽑아옴!
			String email = memberDTO.getEmail();
			
			// 임시 비밀번호 메소드를 통한 임시 비밀번호
			String tempPw_e = generateTempPw(10);
			
			log.info("tempPw_e()에서 생성된 임시 비밀번호: {}", tempPw_e);
			
			// DB에 넣을 때 임시 비밀번호도 암호화 해야하기 때문에 객체 생성
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			
			// 임시 비밀번호 암호화
			String encryptedTempPw_e = bCryptPasswordEncoder.encode(tempPw_e);
			
			// 암호화된 임시 비밀번호를 사용하여 DB에 저장해주기
			memberService.updatePw_e(id, encryptedTempPw_e);

			/* 이메일 보내기 - 이메일 데이터 설정! */
			String setFrom = "dhcksehf1@naver.com";
			String setTo = email;
			String setTitle = "[MYMG] 임시 비밀번호를 알려드립니다.";
			String setContent = "임시 비밀번호는 [" + tempPw_e + "]입니다.";
			
			try {
				
				// JavaMailSenderImpl 객체인 mailSender에서
				// MIME 타입의 이메일 메시지를 생성! -> MimeMessage 객체 반환
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				
				// MimeMessageHelper로 이메일 메세지 구성!
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
				
				// 보내는 사람
				mimeMessageHelper.setFrom(setFrom);
				// 받는 사람
				mimeMessageHelper.setTo(setTo);
				// 이메일 제목
				mimeMessageHelper.setSubject(setTitle);
				// 이메일 본문
				mimeMessageHelper.setText(setContent,true);
				
				// 보내는 메소드
				javaMailSender.send(mimeMessage);
			
				resultMap.put("tempPw", tempPw_e);
				
			} catch(Exception e) {
				
				e.printStackTrace();
				
			} // try-catch
			
		} else {			// 이메일 존재 XX
			
			log.info("회원 정보가 존재하지 않습니다.");
			return null;
			
		} // if-else
		
		return resultMap;
		
	} // sendEmailTempPw()
	
} // end class
