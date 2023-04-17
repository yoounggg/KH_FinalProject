package org.zerock.myapp.mapper;

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
	@DisplayName("테스트1: 비밀번호 변경")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void testchangepw() {
		log.trace("testchangepw() invoked");

		MemberDTO dto = new MemberDTO();
		dto.setId("'codud'"); // 문자열로 인식하기 위해서 큰 따옴표 안에 작은 따옴표 적기
		dto.setPassword("123456");
		
		int affectedLines = this.mapper.changePw(dto);
		
		log.info("\t+affectedLines:{}", affectedLines);
		
	} // testchangepw
	

} // end UserInfoMapperTests
