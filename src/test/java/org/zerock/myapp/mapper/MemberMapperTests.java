package org.zerock.myapp.mapper;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.domain.LoginDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= { "file:src/main/webapp/WEB-INF/spring/**/root-*.xml" })

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberMapperTests {

	// 의존성 주입
	@Inject
	private MemberMapper memberMapper;
	
	@BeforeAll
	void beforeAll() {	// 1회성 전처리
		
		log.trace("beforeAll() invoked.");
		
		assert this.memberMapper != null;
		
		log.info("\t+ this.userService: {}", this.memberMapper);
		
	} // beforeAll()
	
	@Test
	void contextLoads() {
		log.trace("contextLoads() invoked.");
	
	} // contextLoads()
	
////	회원가입 테스트
////	@Disabled
//	@Test
//	@Order(1)
//	@DisplayName("memberJoin Test")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void memberJoin() throws Exception {
//		MemberVO memberVO = new MemberVO();
//		
//		memberVO.setMEMBER_ID("TEST_SENGNA");
//		memberVO.setMEMBER_PW("TEST_SENGNA");
//		memberVO.setMEMBER_NAME("TEST_SENGNA");
//		memberVO.setMEMBER_MAIL("TEST_SENGNA");
//		memberVO.setMEMBER_HP("1055555555");
//		memberVO.setMEMBER_ADDR1("TEST_SENGNA");
//		memberVO.setMEMBER_ADDR2("TEST_SENGNA");
//		memberVO.setMEMBER_ZIPCODE(123456);
//		
//		memberMapper.memberJoin(memberVO);
//	
//		log.info("memberVO: {}", memberVO);
//
//		
//	} // memberJoin()
//	
//	
//	로그인 테스트
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("memberLogin Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void memberLogin() throws Exception {
		
		LoginDTO loginDTO = new LoginDTO();
		
		loginDTO.setId("chachan");
		loginDTO.setPassword("1");
				
		memberMapper.memberLogin(loginDTO);
	
		log.info("Login 테스트 결과: {}", memberMapper.memberLogin(loginDTO));
		
		
	} // memberLogin()

//	@Disabled
//	@Test
//	@Order(1)
//	@DisplayName("memberSignup")
//	@Timeout(value = 1, unit = TimeUnit.MINUTES)
//	public void memberSignup() throws Exception{
//		log.info("memberSignup() invoked.");
//		
//		MemberVO memberVO = new MemberVO();
//		
//		memberVO.setId("aaa");
//		memberVO.setPassword("1234567890z");
//		memberVO.setName("차은우");
//		memberVO.setEmail("eunwoochachacha@gmail.com");
//		memberVO.setTel("01035552020");
//		memberVO.setAddress1(12345);
//		memberVO.setAddress2("강남역 10번 출구");
//		memberVO.setAddress3("dd");
//		memberVO.setGender("남자");
//		memberVO.setAdminCk(1);
//		memberVO.setBirth_date("1999-01-01");
//		memberVO.setJoinDate(1991-01-01);
		
//		memberMapper.memberSignup(memberVO);
		
//	} // memberSignup
	
} // end class
