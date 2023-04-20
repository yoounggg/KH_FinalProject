package org.zerock.myapp.service;

public interface KakaoService {

	//access token 얻기
	public String getAccessToken(String code) throws Throwable;
		
} // end interface
