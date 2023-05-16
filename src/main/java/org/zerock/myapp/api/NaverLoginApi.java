package org.zerock.myapp.api;

import com.github.scribejava.core.builder.api.DefaultApi20;

import lombok.NoArgsConstructor;

@NoArgsConstructor
// 네이버 로그인 API를 사용하는 데 필요한 인증 및 액세스 토큰 엔드포인트를 구현한 클래스임!
public class NaverLoginApi extends DefaultApi20 {
 
    private static class InstanceHolder {
    	// NaverLoginApi 인스턴스를 단일 객체로 유지
        private static final NaverLoginApi INSTANCE = new NaverLoginApi();
        
    } // end class (InstanceHolder)
 
 
    public static NaverLoginApi instance() {
    	
    	// InstanceHolder에서 생성된 단일 인스턴스를 반환
        return InstanceHolder.INSTANCE;
        
    } // instance()
 
    
    @Override
    public String getAccessTokenEndpoint() { 		// 액세스 토큰 엔드포인트 URL을 반환하는 메서드
    	
        return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
        
    } // getAccessTokenEndpoint()
 
    
    @Override
    protected String getAuthorizationBaseUrl() {	// 인증 엔드포인트 URL을 반환하는 메서드
    	
        return "https://nid.naver.com/oauth2.0/authorize";
        
    } // getAuthorizationBaseUrl()
 
    
} // end class
