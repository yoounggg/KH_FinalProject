package org.zerock.myapp.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import com.github.scribejava.core.model.OAuth2AccessToken;

public interface NaverService {

	// 네이버 아이디로 인증을 위한 URL을 생성
    String getAuthorizationUrl(HttpSession session) throws Exception;

    // 사용자가 인증을 완료한 후 콜백에서 호출되며, 인증 코드(code)와 상태 토큰(state)를 전달
    OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException, InterruptedException, ExecutionException;

    // Access Token을 이용하여 네이버 사용자 프로필 정보를 조회하는 API를 호출
    String getUserProfile(OAuth2AccessToken oauthToken) throws Exception;
    
} // end class