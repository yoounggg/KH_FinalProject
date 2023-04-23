package org.zerock.myapp.service;

import java.util.Map;

public interface KakaoService {

	//access token 얻기
	public String getAccessToken(String code) throws Throwable;
		
	public Map<String, Object> getUserInfo(String access_token) throws Throwable;
	
} // end interface
