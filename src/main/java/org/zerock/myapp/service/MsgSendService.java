package org.zerock.myapp.service;

public interface MsgSendService {
	
	// 문자 전송
	public void msgSend(String userPhoneNumber, int randomNumber);
	
	// [셍나] 임시 비밀번호 휴대폰 전송
	public void msgSendSein(String userPhoneNumber, String tempPw);
	
} // end interface
