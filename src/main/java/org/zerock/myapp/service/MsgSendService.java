package org.zerock.myapp.service;

public interface MsgSendService {
	
	// 문자 전송
	public void msgSend(String userPhoneNumber, int randomNumber);
	
} // end interface
