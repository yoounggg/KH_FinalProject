package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
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
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MemberDTO;

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
	
//	혁규 회원가입 테스트
//	@Disabled
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
	
// 혁규 테스트
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
	
//	셍나 로그인 테스트 - 기본 로그인 되는지 확인
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("memberLogin Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void memberLogin() throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId("jeonseinoo");
		memberDTO.setPassword("Jeonsein1216");
				
		memberMapper.memberLogin(memberDTO);
	
		log.info("Login 테스트 결과: {}", memberMapper.memberLogin(memberDTO));
		
	} // memberLogin()

//	셍나 로그인 테스트 - 아이디 찾기 결과값 반환 테스트 - 핸드폰
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("findIdResult Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void findIdResult() throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setName("전셍나");
		memberDTO.setTel("01019971216");
				
	    // memberMapper.findIdResult에 memberDTO의 이름과 전화번호 값을 전달.
	    memberMapper.findIdResult(memberDTO.getName(), memberDTO.getTel());

	    log.info("findIdResult 테스트 결과: {}", memberMapper.findIdResult(memberDTO.getName(), memberDTO.getTel()));
		
	} // findIdResult()
	
//	찬돌 member정보 얻기 테스트
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("getMemberInfo Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void TestsgetMemberInfo() throws Exception {
		
        String memberId = "chandoll";
        
        MemberDTO memberDTO = memberMapper.getMemberInfo(memberId);
		
	} // TestsgetMemberInfo()
	
//	셍나 로그인 테스트 - 이름, 이메일로 아이디 중복 확인 테스트
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("idCheck_e Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void idCheck_e() throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setName("전셍나");
		memberDTO.setEmail("jeonseino.o@gmail.com");
				
	    // memberMapper.findIdResult에 memberDTO의 이름과 이메일 값을 전달.
	    memberMapper.idCheck_e(memberDTO.getName(), memberDTO.getEmail());

	    log.info("idCheck_e 테스트 결과: {}", memberMapper.idCheck_e( memberDTO.getName(), memberDTO.getEmail() ));
		
	} // idCheck_e()
	
	
//	셍나 로그인 테스트 - 아이디 찾기 결과값 반환 테스트 - 이메일
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("findIdResult_e Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void findIdResult_e() throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setName("전셍나");
		memberDTO.setEmail("jeonseino.o@gmail.com");
				
	    // memberMapper.findIdResult_e에 memberDTO의 이름과 이메일 값을 전달.
	    memberMapper.findIdResult_e(memberDTO.getName(), memberDTO.getEmail());

	    log.info("findIdResult_e 테스트 결과: {}", memberMapper.findIdResult_e(memberDTO.getName(), memberDTO.getEmail()));
		
	} // findIdResult_e()
	
//	셍나 로그인 테스트 - 아이디 조회 테스트
//	@Disabled
	@Test
	@Order(6)
	@DisplayName("idSearch Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void idSearch() throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId("jeonseinoo");
				
	    // memberMapper.idSearch에 memberDTO의 아이디값 전달.
	    memberMapper.idSearch(memberDTO.getId());

	    log.info("idSearch 테스트 결과: {}", memberMapper.idSearch( memberDTO.getId() ));
		
	} // idSearch()
	
//	셍나 로그인 테스트 - 아이디로 휴대폰 번호 조회 테스트
//	@Disabled
	@Test
	@Order(7)
	@DisplayName("changePW_p Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void changePW_p() throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId("jeonseinoo");
				
	    // memberMapper.changePW_p에 memberDTO의 아이디값 전달.
	    memberMapper.changePW_p(memberDTO.getId());

	    log.info("changePW_p 테스트 결과: {}", memberMapper.changePW_p( memberDTO.getId() ));
		
	} // changePW_p()
	
//	셍나 로그인 테스트 - 아이디로 이메일 주소 조회 테스트
//	@Disabled
	@Test
	@Order(8)
	@DisplayName("changePW_e Test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void changePW_e() throws Exception {
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId("jeonseinoo");
				
	    // memberMapper.changePW_e에 memberDTO의 아이디값 전달.
	    memberMapper.changePW_e(memberDTO.getId());

	    log.info("changePW_e 테스트 결과: {}", memberMapper.changePW_e( memberDTO.getId() ));
		
	} // changePW_e()
	
//	[별이] 회원 전체 목록 조회
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("member selectAllPaging test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void selectAllPaging() throws Exception {
		log.trace("selectAll() invoked.");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(1);
		
		List<MemberDTO> list = this.memberMapper.selectAllPaging(cri);
		assertNotNull(list);
		
	    list.forEach(log::info);
		
	} // selectAll
	
	
//	[별이] 회원 전체 목록 조회
//	@Disabled
	@Test
	@Order(6)
	@DisplayName("member select test")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void select() throws Exception {
		log.trace("select() invoked.");
		
		String id = "mooyaho";
		MemberDTO dto = this.memberMapper.select(id);
		
	    log.info("member select 테스트 결과: {}", dto);
		
	} // select
	
	
	
} // end class
