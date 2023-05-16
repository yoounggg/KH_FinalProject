package org.zerock.myapp.service;

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
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FaqDTO;
import org.zerock.myapp.domain.FaqVO;
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
public class FaqSearchServiceTests {
	
	@Setter(onMethod_=@Autowired)
	private FaqSearchService service;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	} // beforeAll
	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : faqSearchList")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void faqSearchList() throws ServiceException {
		log.info("FaqSearch() invoked");
		
		Criteria cri = new Criteria();
		
		//테스트해볼 keyword 적기
		cri.setKeyword("공지");
		log.info("cri: " + cri);
		
		List<FaqDTO> list = this.service.faqSearchList(cri);
		log.info("list: " + list);
		
		Integer totalFaq = this.service.totalFaq(cri);
		log.info("total: " + totalFaq);
		
	} // testGetList()
	

	
	
} // end class