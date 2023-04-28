package org.zerock.myapp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private MemberService service;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	} // beforeAll
	
//	[별이] 회원 목록 전체 조회
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : getList")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void getList() throws ServiceException {
		log.trace("getList invoked. - [관리자]회원 목록 전체 조회");
		
		List<MemberDTO> list = this.service.getList();
		assertNotNull(list);
		
		log.info("list({}) invoked.", list);
		
	} // selectAll
	
	
//	[별이] 회원 목록 전체 조회
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : getListPaging")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void getListPaging() throws ServiceException {
		log.trace("getListPaging invoked. - [관리자]회원 목록 전체 조회(페이징)");
		
		Criteria cri = new Criteria();
		List<MemberDTO> list = this.service.getList();
		
		assertNotNull(list);
		log.info("list({}) invoked.", list);
		
	} // selectAll
	
	
	
//	[별이] 회원 목록 전체 조회
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 2 : get")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void get() throws ServiceException {
		log.trace("get invoked. - [관리자]회원 목록 전체 조회");
		
		String id = "mooyaho";
		MemberDTO dto = this.service.get(id);
		assertNotNull(dto);
		
		log.info("get({}) invoked.", dto);
		
	} // get
	
	

} // end class
