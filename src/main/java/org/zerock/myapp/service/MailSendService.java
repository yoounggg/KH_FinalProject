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
    	
        int randomNum = (int) ( Math.random() * 900000 ) + 100000; // 6자리 난수 생성 (100000 ~ 9999999)
        
        return String.valueOf(randomNum);
        
    } // authKey()

//  ==========================================
    
    // 인증 번호 메일 서비스
    public String sendAuthMail(String mail) throws MessagingException {
    	
        String authKey = authKey();
        
        MimeMessage mailMessage = mailSender.createMimeMessage();
        String mailContent = "MYMG 아이디 찾기 인증 번호: " + authKey; // 보낼 메시지

        mailMessage.setSubject("셍나가 보내는 이메일", "utf-8");
        mailMessage.setText(mailContent, "utf-8", "html");
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        mailSender.send(mailMessage);

        return authKey;
        
    } // sendAuthMail
    
} // end class