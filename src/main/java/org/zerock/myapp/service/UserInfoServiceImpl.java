package org.zerock.myapp.service;

import java.util.HashMap;
import java.util.Objects;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.UserInfoMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Log4j2
@NoArgsConstructor

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService, InitializingBean  {
	

	@Setter(onMethod_= {@Autowired})
	private UserInfoMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		  log.trace("afterPropertiesSet() invoked");
	      //1회성 전처리 -> 위에서 빈 (dao)을 잘 주입받았는지 체크해보기
	      try {
	         Objects.requireNonNull(this.mapper);
	         log.info("\t+this.dao:{}", this.mapper);
	      }catch(Exception e) {
	         throw new ServiceException(e);
	      } // try-catch
	} // afterPropertiesSet

	
	@Override // 조회
	public MemberDTO userDetail(String id) throws ServiceException {
		log.trace("userDetail({}) invoked.", id);
		
		try {
			return this.mapper.select(id);
		}catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // userDetail

	
	@Override // 수정 (비밀번호 제외)
	public Boolean updateUser(MemberDTO dto) throws ServiceException {
		log.trace("updateUser({}) invoked.", dto);
		
		try {
			return this.mapper.update(dto) == 1;
		}catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // updateUser


//	@Override // 휴대폰 인증
//	public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) {
//		log.trace("certifiedPhoneNumber({}, {}) invoked.", userPhoneNumber, randomNumber);
//		
//		String api_key = "coolsms 본인 api키 입력";
//		String api_secret = "coolsms 본인 api_secret키 입력";
//		Message coolsms = new Message(api_key, api_secret);
//		
//		 // 4 params(to, from, type, text) are mandatory. must be filled
//	    HashMap<String, String> params = new HashMap<String, String>();
//	    params.put("to", userPhoneNumber);    // 수신전화번호
//	    params.put("from", "자신의 번호");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
//	    params.put("type", "SMS");
//	    params.put("text", "[TEST] 인증번호는" + "["+randomNumber+"]" + "입니다."); // 문자 내용 입력
//	    params.put("app_version", "test app 1.2"); // application name and version
//
//	    try {
//	        JSONObject obj = (JSONObject) coolsms.send(params);
//	        System.out.println(obj.toString());
//	      } catch (CoolsmsException e) {
//	        System.out.println(e.getMessage());
//	        System.out.println(e.getCode());
//	      }
//		
//	} // certifiedPhoneNumber
	
	
	@Override
	public Boolean deleteUser(String id) throws ServiceException {
		log.trace("delete({}) invoked.", id);
		
		try {
			return this.mapper.delete(id) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // delete

} // end class
