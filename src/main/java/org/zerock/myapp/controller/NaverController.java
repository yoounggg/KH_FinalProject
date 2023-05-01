package org.zerock.myapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.myapp.api.NaverLoginApi;
import org.zerock.myapp.domain.MemberDTO;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class NaverController {
	
	// client_id: 애플리케이션 등록 후 발급받은 클라이언트 아이디
	private final static String CLIENT_ID = "0uv9EITi7mWXq43C1IuC";
	
	private final static String CLIENT_SECRET = "Fq5QQZ3JgK";
	// redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백 URL(URL 인코딩).
	// 애플리케이션을 등록할 때 Callback URL에 설정한 정보입니다.
	private final static String REDIRECT_URI = "http://localhost:8090/myfinal/naverlogin.do";
    // state: 애플리케이션이 생성한 상태 토큰
	private final static String SESSION_STATE = "oauth_state";
	// 프로필 조회 API URL
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	   
	private MemberDTO memberDTO;
	
	// getAuthorizationUrl() = 네이버 아이디로 인증  URL 생성  */
	public String getAuthorizationUrl(HttpSession session) {
	 
		// 세션의 유효성 검증을 위한 난수 생성
		String state = generateRandomString();
	      
		// 생성한 난수 값을 session에 저장해줌!
		setSession(session, state);
	 
		/* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
		OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI)
				.build(NaverLoginApi.instance());
	    
		// .state() 안되길래 이렇게 바꿈..!
		String authorizationUrl = oauthService.getAuthorizationUrl() + "&state=" + state;
	 
		return authorizationUrl;
	      
	} // getAuthorizationUrl()
	 
	// getAccessToken() = 네이버 아이디로 Callback 처리 및 AccessToken 획득
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) 
			throws IOException, InterruptedException, ExecutionException {
	 
		// Callback으로 전달받은 세션 검증용 난수 값과
		// 세션에 저장되어있는 값이 일치하는지 확인 
		String sessionState = getSession(session);
		
		// 전달받은 상태 토큰과 세션에 저장된 상태 토큰이 일치하는지 확인
		if (StringUtils.pathEquals(sessionState, state)) {
	 
			 OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
	                    .apiSecret(CLIENT_SECRET)
	                    .callback(REDIRECT_URI)
	                    .build(NaverLoginApi.instance());
	 
			 	// Scribe에서 제공하는 AccessToken 획득 기능으로 
			 	// 네이버 아이디로 로그인 Access Token을 획득
	            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
	            
	            // 상태 토큰 일치 시 accessToken 반환
	            return accessToken;
	            
		} // if
		
		// 상태 토큰이 일치하지 않으면 null을 반환
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
    
	 
	// Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
	public String getUserProfile(OAuth2AccessToken oauthToken) 
			throws IOException, InterruptedException, ExecutionException {
	 
   	 // OAuth2.0 서비스 객체를 생성하고, 필요한 정보(클라이언트 ID, 시크릿, 콜백 URL)를 설정.
        OAuth20Service oauthService = new ServiceBuilder(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI)
                .build(NaverLoginApi.instance());
	 
        // 네이버 프로필 조회 API를 호출하기 위한 OAuthRequest 객체 생성
        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL);
        
        // Access Token을 사용하여 요청에 서명합니다.
        oauthService.signRequest(oauthToken, request);
        
        // OAuthRequest 객체를 전달하여 API 요청을 실행하고 응답을 받습니다.
        Response response = oauthService.execute(request);
        
        // 응답 객체에서 사용자 프로필 정보를 JSON 형식으로 반환합니다.
        return response.getBody();
        
	} // getUserProfile()
	
	
	// Access Token을 이용하여 네이버 사용자 프로필 API를 호출
	public void naverProfile() {
		
		// 네이버 로그인 접근 토큰;
		String token = "0uv9EITi7mWXq43C1IuC";
		
		// Bearer 다음에 공백 추가
		String header = "Bearer " + token;
		
		try {
			// 프로필 조회 API URL
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			
			// URL을 연결하기 위한 HttpURLConnection 생성
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// Access Token을 이용한 인증 정보 설정
			con.setRequestMethod("GET");
			
			// 인증 정보 설정
			con.setRequestProperty("Authorization", header);
			
			// HTTP 응답 코드
			int responseCode = con.getResponseCode();
			
			// HTTP 응답 코드를 이용하여 정상 호출인 경우에는 BufferedReader를 이용하여 
			// 프로필 조회 결과를 읽음.
			// 에러 발생한 경우에는 에러 메시지를 읽어오기 위해 BufferedReader를 이용.
			BufferedReader br;
			
			if(responseCode==200) { 		// 정상 호출
				// 정상 호출 출력을 위한 BufferedReader
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  						// 에러 발생
				// 에러 호출 출력을 위한 BufferedReader
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				// 프로필 조회 결과를 문자열로 변환하여 저장
				response.append(inputLine);
			}
			
			// 자원 닫기 
			br.close();
			
			// 프로필 조회 결과 출력
			log.info("네이버 아이디로 로그인: naverProfile() 프로필 조회 결과 출력: {}", response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch
		
	} // naverProfile
	
//	 --------------------------------------------------------------------------------------------------
	
	// 네이버 로그인 & 회원정보(이름) 가져오기
	@RequestMapping(value = "/naverlogin.do", produces = "application/json;charset=utf-8", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView naverLogin (@RequestParam String code, @RequestParam String state, HttpSession session)
				throws IOException {
		
		ModelAndView modelAndView = new ModelAndView();
		
		OAuth2AccessToken oauthToken;
		
		oauthToken = naverLoginDTO.getAccessToken(session, code, state);
	 
			// 로그인한 사용자의 모든 정보가 JSON타입으로 저장되어 있음
			apiResult = naverLoginDTO.getUserProfile(oauthToken);
	 
			// 내가 원하는 정보 (이름)만 JSON타입에서 String타입으로 바꿔 가져오기 위한 작업
			JSONParser parser = new JSONParser();
			Object obj = null;
			try {
				obj = parser.parse(apiResult);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			JSONObject jsonobj = (JSONObject) obj;
			JSONObject response = (JSONObject) jsonobj.get("response");
			String nname = (String) response.get("name");
			String nemail = (String) response.get("email");
			String ngender = (String) response.get("gender");
			String nbirthday = (String) response.get("birthday");
			String nage = (String) response.get("age");
			String nimage = (String) response.get("profile_image");
	 
			// 로그인&아웃 하기위한 세션값 주기
			session.setAttribute("nname", nname);
			session.setAttribute("nemail", nemail);
			session.setAttribute("ngender", ngender);
			session.setAttribute("nbirthday", nbirthday);
			session.setAttribute("nage", nage);
			session.setAttribute("nimage", nimage);
	 
			// 네이버 로그인 성공 페이지 View 호출
			mav.setViewName("main");
			return mav;
		}// end naverLogin()
	
} // end class
