package org.zerock.myapp.service;

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
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FarmDTO;
import org.zerock.myapp.domain.FarmVO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FarmServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private FarmService service;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	}

	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : getList")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void getList() throws ServiceException {
		log.trace("getList() invoked.");
		
		List<FarmVO> list = this.service.getList();
		
		assertNotNull(list);
		
		list.forEach(log::info);
	
	} // getList
	
	
//	@Disable
	@Test
	@Order(2)
	@DisplayName("테스트 2 : get")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void get() throws ServiceException {
		log.trace("get() invoked.");
		
		int no = 35;
		FarmVO vo = this.service.get(no);
		
		assertNotNull(vo);
		
		log.info("\t+ vo : {}", vo);
	
	} // getList
	
	
	
//	@Disable
	@Test
	@Order(3)
	@DisplayName("테스트 3 : register")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void register() throws ServiceException {
		log.trace("get() invoked.");
		
		FarmDTO dto = new FarmDTO();
		dto.setName("(주)찬돌");
		dto.setBusiness_no("454-45-45454");
		dto.setLocation("서울특별시 강남구 테헤란로10길 11");
		dto.setTel("010-7979-8989");
		
		Boolean success = this.service.register(dto);
		
		log.info("\t+ success : {}", success);
	
	} // register
	
	
//	@Disable
	@Test
	@Order(4)
	@DisplayName("테스트 4 : modify")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void modify() throws ServiceException {
		log.trace("modify() invoked.");
		
		FarmVO vo = this.service.get(15);
		
		FarmDTO dto = new FarmDTO();
		dto.setNo(vo.getNo());
		dto.setName(vo.getName());
		dto.setBusiness_no("454-45-45456");
		dto.setLocation(vo.getLocation());
		dto.setTel(vo.getTel());
		
		Boolean success = this.service.register(dto);	
		
		log.info("\t+ success : {}", success);
	
	} // modify
	
	
	
//	@Disable
	@Test
	@Order(5)
	@DisplayName("테스트 5 : remove")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void remove() throws ServiceException {
		log.trace("remove() invoked.");
		
		int no = 16;
		
		Boolean success = this.service.remove(no);	
		
		log.info("\t+ success : {}", success);
	
	} // remove
	
	
//	@Disable
	@Test
	@Order(6)
	@DisplayName("테스트 6 : getListPaging")
	@Timeout(value=10, unit=TimeUnit.MINUTES)
	void testGetListPaging() throws ServiceException {
		log.trace("testGetListPaging() invoked.");
		
		Criteria cri = new Criteria();
		
		List<FarmVO> list = this.service.getListPaging(cri);
		assert list != null;
		list.forEach(log::info);
		
		
	} // testGetListPaging()
	
	
	

} // end class