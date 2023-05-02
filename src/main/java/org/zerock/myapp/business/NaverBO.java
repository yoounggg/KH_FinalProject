package org.zerock.myapp.business;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zerock.myapp.api.NaverLoginApi;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Service
// 비즈니스 로직을 캡슐화하고 다른 계층과 상호 작용을 관리하는 역할을 위한 Business Object class 생성
public class NaverBO {

    /* 인증 요청문을 구성하는 파라미터 */

	//response_type: 인증 과정에 대한 구분값. code로 값 고정.
	
	//client_id: 애플리케이션 등록 후 발급받은 클라이언트 아이디
    private final static String CLIENT_ID = "0uv9EITi7mWXq43C1IuC";
    
    private final static String CLIENT_SECRET = "Fq5QQZ3JgK";
    
    //redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백 URL(URL 인코딩). 애플리케이션을 등록할 때 Callback URL에 설정한 정보입니다.
    private final static String REDIRECT_URI = "http://localhost:8080/login/callBack";
    
    //state: 애플리케이션이 생성한 상태 토큰
    private final static String SESSION_STATE = "oauth_state";
    
    /* 프로필 조회 API URL */
    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
    
//  -------------------------------------------------------------------------------------------------
    
    /* getAuthorizationUrl() = 네이버 아이디로 인증  URL 생성  */
    public String getAuthorizationUrl(HttpSession session) throws Exception {
 
        // 세션의 유효성 검증을 위한 난수 생성
        String state = generateRandomString();
        
        // 생성한 난수 값을 session에 저장해줌!
        setSession(session,state);        
 
        // Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네이버로 로그인하기 인증 URL 생성
        OAuth20Service oauthService = new ServiceBuilder()
        		.apiKey(CLIENT_ID)
        		.apiSecret(CLIENT_SECRET)
        		.callback(REDIRECT_URI)
        		.state(state) //앞서 생성한 난수값을 인증 URL생성시 사용함
        		.build(NaverLoginApi.instance());
        
        return oauthService.getAuthorizationUrl();
 
    } // getAuthorizationUrl()
 
    /* 네이버아이디로 Callback 처리 및  AccessToken 획득 Method */
    public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) 
    		throws IOException, InterruptedException, ExecutionException {
    	 
        /* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
        String sessionState = getSession(session);
        
        // 전달받은 상태 토큰과 세션에 저장된 상태 토큰이 일치하는지 확인
        if(StringUtils.pathEquals(sessionState, state)) {
        	
        	OAuth20Service oauthService = new ServiceBuilder()
        			.apiKey(CLIENT_ID)
        			.apiSecret(CLIENT_SECRET)
        			.callback(REDIRECT_URI)
        			.state(state)
        			.build(NaverLoginApi.instance());
     
            /* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
            
            // 상태 토큰 일치 시 accessToken 반환
            return accessToken;
        
        } // if
        
        // 상태 토큰이 일치하지 않으면 null을 반환합니다.
        return null;
        
    } // getAccessToken() 
 
    
    /* 세션 유효성 검증을 위한 난수 생성기 */
    private String generateRandomString() {
    	
        return UUID.randomUUID().toString();
        
    } // generateRandomString()
 
    
    /* http session에 데이터 저장 */
    private void setSession(HttpSession session,String state){
    	
        session.setAttribute(SESSION_STATE, state);     
        
    } // setSession()
 
    
    /* http session에서 데이터 가져오기 */ 
    private String getSession(HttpSession session){
    	
        return (String) session.getAttribute(SESSION_STATE);
        
    } // getSession()
    
    
    /* Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
    public String getUserProfile(OAuth2AccessToken oauthToken) throws Exception {
    	
    	// OAuth2.0 서비스 객체를 생성하고, 필요한 정보(클라이언트 ID, 시크릿, 콜백 URL)를 설정.
    	OAuth20Service oauthService =new ServiceBuilder()
    			.apiKey(CLIENT_ID)
    			.apiSecret(CLIENT_SECRET)
    			.callback(REDIRECT_URI).build(NaverLoginApi.instance());
        
    	// 네이버 프로필 조회 API를 호출하기 위한 OAuthRequest 객체 생성
    	OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
    	
    	// Access Token을 사용하여 요청에 서명
    	oauthService.signRequest(oauthToken, request);
    	
    	// OAuthRequest 객체를 전달하여 API 요청을 실행 후 응답 받음
    	Response response = request.send();
    	
    	// 응답 객체에서 사용자 프로필 정보를 JSON 형식으로 반환
    	return response.getBody();
        
    } // getUserProfile()
    
} // end class
