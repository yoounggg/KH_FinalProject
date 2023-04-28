package org.zerock.myapp.mapper;

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
import org.zerock.myapp.domain.AdminDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private AdminMapper mapper;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	}
	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : login")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void login() {
		log.trace("login() invoked. - 관리자 로그인");

		AdminDTO dto = new AdminDTO();
		
		dto.setId("admin");
		dto.setPassword("Oracle12345678");
				
		AdminDTO affectedLines = this.mapper.login(dto);
	
		log.info("\t+ affectedLines : {}", affectedLines);
		
	} // testSelectAll

	
	
} // end class