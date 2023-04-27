package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.domain.MemberDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocialMapperTests {

	@Setter(onMethod_ = @Autowired)
	private SocialMemberMapper socialMemberMapper;

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked");

		Objects.requireNonNull(this.socialMemberMapper);

		log.trace("this.mapper{}, type {}", this.socialMemberMapper, this.socialMemberMapper.getClass().getName());

	} // beforeAll

//	@Disabled
	@Test
	@DisplayName("testKakaoCheck")
	@Order(1)
	@Timeout(value = 1, unit = TimeUnit.MINUTES)
	public void testKakaoCheck() throws Exception {
		log.trace("testKakaoCheck () invoked.");
		
		String id = "chlqufdl";
		String email = "admin@naver.com";
		
		MemberDTO idEmailCheck = socialMemberMapper.idEmailCheck(id, email);		// 이메일 또는 아이디 일치하면 정보 불러와짐

		log.trace("idEmailCheck : {} invoked.", idEmailCheck);
	} // testKakaoCheck

	
//	@Disabled
	@Test
	@DisplayName("testKakaoInsert")
	@Order(2)
	@Timeout(value = 1, unit = TimeUnit.MINUTES)
	public void testKakaoInsert() throws Exception {
		log.trace("testKakaoInsert () invoked.");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("299292929229");
		memberDTO.setEmail("email@naver.com");
		
		 socialMemberMapper.insert(memberDTO);		// member에 id , email 삽입

		log.trace("insert : {} invoked.", memberDTO);
		
	} // testKakaoInsert
	
	
//	@Disabled
	@Test
	@DisplayName("testKakaoAddinfo")
	@Order(3)
	@Timeout(value = 1, unit = TimeUnit.MINUTES)
	public void testKakaoAddinfo() throws Exception {
		log.trace("testKakaoAddinfo () invoked.");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setAddress1(5811);
		memberDTO.setAddress2("가리봉동 3번길");
		memberDTO.setAddress3("가리봉 빌라 A동 303호");
		memberDTO.setAdminCk(0);
		memberDTO.setBirth_year(1993);
		memberDTO.setBirth_month(10);
		memberDTO.setBirth_day(16);
		memberDTO.setGender("남자");
		memberDTO.setName("대림동무쇠팔");
		memberDTO.setSocial_type(null);
		memberDTO.setTel("01099801182");
		
		socialMemberMapper.kakaoSignupAddInfo(memberDTO);		// 추가정보 삽입

		log.trace("kakaoSignupAddInfo : {} invoked.", memberDTO);
	} // testKakaoAddinfo
	

	
} // end class
