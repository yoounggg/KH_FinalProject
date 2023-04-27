package org.zerock.myapp.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

    @Autowired
    private JavaMailSenderImpl mailSender;

//    ==========================================
    
    // 인증 번호 -> 6자리 수의 랜덤 번호 생성
    private String authKey() {
    	
    	// 6자리 난수 생성 (100000 ~ 9999999)
        int randomNum = (int) ( Math.random() * 900000 ) + 100000;
        
        return String.valueOf(randomNum);
        
    } // authKey()

//  ==========================================
    
    // 인증 번호 메일 서비스
    // 반환값(= authKey)은 6자리의 인증 번호!
    public String sendMail(String mail) throws MessagingException {
    	
        String authKey = authKey();
        
        // JavaMailSenderImpl 객체인 mailSender에서
        // MIME 타입의 이메일 메시지를 생성! -> MimeMessage 객체 반환
        MimeMessage mailMessage = mailSender.createMimeMessage();
        
        // 이메일 내용
        String mailContent = "MYMG 아이디 찾기 인증 번호: " + authKey; // 보낼 메시지
        // 제목
        mailMessage.setSubject("셍나가 테스트로 보내는 이메일", "utf-8");
        // MemeMessage 객체의 본문 설정!
        // 생성된 인증 번호를 포함하는 문자열을 본문으로 사용하며, 
        // "utf-8" 문자 인코딩과 "html" 서브타입을 사용!
        mailMessage.setText(mailContent, "utf-8", "html");
        // 수신자
        // Message.RecipientType.TO : 수신자 타입
        // new InternetAddress(mail) : 수신자 이메일 주소
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        // 설정된 MimeMessage 객체 전송! -> 해당 메소드로 실제 전송됨.
        mailSender.send(mailMessage);

        return authKey;
        
    } // sendMail()
    
} // end class