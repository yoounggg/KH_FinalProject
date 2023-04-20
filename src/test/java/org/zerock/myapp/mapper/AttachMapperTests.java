package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
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
import org.zerock.myapp.domain.AttachImageVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AttachMapperTests {

	@Setter(onMethod_= {@Autowired})
	private AttachMapper mapper;
	
	@BeforeAll
	void beforeAll() {
		log.info("beforeAll() invoked");
	}
	
	
//	별이가 생성함 4/19
	
//	@Disable
//	@Test
//	@Order(1)
//	@DisplayName("테스트1 : contextLoads")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void contextLoads() {
//		log.trace("contextLoads() invoked.");
//	} // contextLoads
	
	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트1 : getAttachList")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void getAttachList() {
		log.trace("getAttachList() invoked.");
		
		int product_no = 60;
		List<AttachImageVO> list = this.mapper.getAttachList(product_no);
		
		assertNotNull(list);
		
		list.forEach(log::info);
		
	} // getAttachList
	
	
}
