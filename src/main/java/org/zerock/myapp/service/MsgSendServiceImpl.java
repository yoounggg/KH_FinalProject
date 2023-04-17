package org.zerock.myapp.service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import net.nurigo.java_sdk.api.Message;

@Service
@Log4j2
public class MsgSendServiceImpl implements MsgSendService {

		
		@Override
		public void msgSend(String userPhoneNumber, int randomNumber) {
			log.trace("msgSend ({}, {}) invoked",userPhoneNumber, randomNumber);
			
			String api_key = "NCSPPITQAGEFFLKB";
			String api_secret = "UITSAB9CX6H9EPNH88LQPOED7VIV1ZW1";
			
			Message coolsms = new Message(api_key , api_secret);
			
			HashMap<String, String> params = new HashMap<String, String>();
//			params.put("to", userPhoneNumber);			// 수신 번호
			params.put("to", "오오오오오");			// 수신 번호
			params.put("from", "01035552200");			// 발신 번호
			params.put("type", "SMS");
			params.put("text", "MYMG 인증번호는 " +" [ " + randomNumber + "] " + " 입니다. 인증시간 안에 입력해주세요.");
			params.put("app_version", "test app 1.2");
			
		
			
			try {
				
				for (String key : params.keySet()) {
				    log.trace(key + " : " + params.get(key));
				} // for
				
				JSONObject obj = (JSONObject) coolsms.send(params);
				log.trace("JSONObject ({}) invoked.", obj) ;
				
			} catch (Exception e) {

				log.trace("e.getMessage({}) invoked.",e.getMessage());
				
			} // try-catch
			
			
		} // msgSend
	

} // end class
