package org.zerock.myapp.controller;

import java.util.concurrent.TimeUnit;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/**/spring/root-*.xml",
		"file:src/main/webapp/**/spring/**/servlet-*.xml"})
@WebAppConfiguration

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberControllerTests {

	@Autowired
    JavaMailSenderImpl javaMailSenderImpl;
    
//  MimeMessage 객체 직접 생성 -> 메일 발송
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : mailSendTest()")
	@Timeout(value=5, unit=TimeUnit.MINUTES)
    public void mailSendTest() throws Exception{

		
//		이메일 보내려면 사용 중인 jdk 설정 파일인 java.security에서 TLS 설정 바꿔야 함 !!!!!!!!!!!!!1
//		= 셍나 노트북으로만.. 가능..하다는..것..
		
        String subject = "셍나는 뚠둔, 오늘도 뚠뚠, 열심히~ 테스트 하네~";
        String content = "이메일 인증 구현을 위한 컨트롤러 테스트 중입니다!";
        String from = "dhcksehf1@naver.com";
        String to = "jeonseino.o@gmail.com";
        
        try {
        	
            MimeMessage mail = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
            
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            
            javaMailSenderImpl.send(mail);
            
            log.info("이메일 전송 성공입니다 :-)");
            
        } catch(Exception e) {
            e.printStackTrace();
        } // try-catch
        
    } // mailSendTest()
	
} // end class
