package org.zerock.myapp.service;

public interface KakaoService {

		//access token 얻기
		String getAccessToken(String authorizeCode) throws Throwable;
		
} // end interface
