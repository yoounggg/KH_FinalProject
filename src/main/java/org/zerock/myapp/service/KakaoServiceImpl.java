package org.zerock.myapp.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.mapper.SocialMemberMapper;

import lombok.extern.log4j.Log4j2;
//
@Service
@Log4j2
public class KakaoServiceImpl implements KakaoService {

	@Autowired
	private SocialMemberMapper socialMemberMapper;
	
	@Override
	public String getAccessToken(String code) throws IOException, org.json.simple.parser.ParseException {
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		String token = "";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=4728fdda1dcf6b1dcbc098a9a7ece445");

			sb.append("&redirect_uri=http://localhost:8080/signup/kakao/callback");
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			log.trace("responseCode : {}", responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			} // while

			log.trace("result : {}" + result);

			JSONParser parser = new JSONParser();
			JSONObject elem = (JSONObject) parser.parse(result);

			access_token = elem.get("access_token").toString();
			refresh_token = elem.get("refresh_token").toString();
			log.trace("access_token : " + access_token);
			log.trace("refresh_token : " + refresh_token);

			token = access_token;

			br.close();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} // try-catch

		return token;

	} // getReturnAccessToken

	public Map<String, Object> getUserInfo(String access_token) throws IOException {
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		Map<String, Object> result = new HashMap<>();

		try {
			URL url = new URL(reqUrl);

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
			urlConnection.setRequestMethod("GET");

			int responseCode = urlConnection.getResponseCode();
			log.trace("responseCode : {}", responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line = "";
			String res = "";
			while ((line = br.readLine()) != null) {
				res += line;
			} // while
			log.trace("res : {}", res);

			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(res);
			JSONObject kakao_account = (JSONObject) obj.get("kakao_account");
			JSONObject properties = (JSONObject) obj.get("properties");

			String id = obj.get("id").toString();
			String nickname = properties.get("nickname").toString();
			String email = kakao_account.get("email").toString();

			result.put("id", id);
			result.put("nickname", nickname);
			result.put("email", email);

			br.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} // try-catch
		
		return result;
		
	} // getUserInfo

	
} // end class
