package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.zerock.myapp.domain.MemberVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserInfoMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private UserInfoMapper mapper; // 매퍼 빈 주입
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked");
		
		Objects.requireNonNull(this.mapper);
		log.info("\t+this.mapper:{}, type:{}", this.mapper, this.mapper.getClass().getName());
	} // beforeAll
	
	@Test
	@Order(1)
	@DisplayName("contextloads")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void contextloads() {
		log.trace("contextloads() invoked");
		
	} // contextloads
	
	@Test
	@Order(2)
	@DisplayName("테스트1: 회원상세조회")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void testselect() {
		log.trace("testselect() invoked");
		
		String id = "mymgadmin";
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		
		MemberDTO affectedlines = this.mapper.select(id);
		
		log.info("\t+ affectedlines : {}", affectedlines);
		
	} // testselect
	
	@Test
	@Order(3)
	@DisplayName("테스트2: 회원정보수정")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void testupdate() {
		log.trace("testupdate() invoked");
	    
		MemberDTO dto = new MemberDTO();
		dto.setId("mymgadmin");
		dto.setName("admin");
		dto.setTel("01012345678");
		dto.setAddress1(12345);
		dto.setAddress2("관리자");
		dto.setAddress3("관리자");
		dto.setGender("여자");
	
		int affectedlines = this.mapper.update(dto);
		
		log.info("\t+ affectedlines : {}", affectedlines);
		
	} // testupdate
	
	@Test
	@Order(4)
	@DisplayName("테스트3: 회원삭제")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void testdelete() {
		log.trace("testdelete() invoked");
		
		String id = "admin";
	
		int affectedlines = this.mapper.delete(id);
		
		log.info("\t+ affectedlines : {}", affectedlines);
		
	} // testdelete
	

} // end UserInfoMapperTests
