package org.zerock.myapp.mapper;

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
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.myapp.domain.FarmDTO;
import org.zerock.myapp.domain.FarmVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/root-*.xml")

@WebAppConfiguration

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FarmMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private FarmMapper mapper;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	} // beforeAll
	
	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : selectAll")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void testSelectAll() {
		log.trace("testSelectAll() invoked.");
	
		List<FarmVO> list = this.mapper.selectAll();
		
		assertNotNull(list);
		list.forEach(log::info);
		
	} // testSelectAll()
	
	
//	@Disable
	@Test
	@Order(2)
	@DisplayName("테스트 2 : select")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void testSelect() {
		log.trace("testSelect() invoked.");
		
		int no = 1;
		FarmVO vo = this.mapper.select(no);
		
		assertNotNull(vo);
		
		log.info("\t+ vo : {}", vo);
	
	} // testSelect()
	
	
//	@Disable
	@Test
	@Order(3)
	@DisplayName("테스트 3 : insert")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void testInsert() {
		log.trace("testInsert() invoked.");
	
		FarmDTO dto = new FarmDTO();
		dto.setName("(주)셍나");
		dto.setBusiness_no("789-56-12345");
		dto.setLocation("서울특별시 강남구 테헤란로10길 10");
		dto.setTel("010-8282-8282");
		
		int affectLines = this.mapper.insert(dto);
		
		log.info("\t+ affectLines : {}", affectLines);
	
	} // testInsert()
	
	
//	@Disable
	@Test
	@Order(4)
	@DisplayName("테스트 4 : update")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void testUpdate() {
		log.trace("testInsert() invoked.");
	
		FarmDTO dto = new FarmDTO();
		dto.setNo(6);
		dto.setName("(주)셍나");
		dto.setBusiness_no("789-56-12345");
		dto.setLocation("서울특별시 강남구 테헤란로10길 10");
		dto.setTel("010-8282-8282");
		
		int affectLines = this.mapper.insert(dto);
		
		log.info("\t+ affectLines : {}", affectLines);
	
	} // testInsert()
	
	
	
//	@Disable
	@Test
	@Order(5)
	@DisplayName("테스트 5 : delete")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void testDelete() {
		log.trace("testDelete() invoked.");
		
		Integer no = 14;
		int affectedLines = this.mapper.delete(no);
		
		log.info("\t+ vo : {}", affectedLines);
	
	} // testDelete()
	
	
	
//	@Disable
//	@Test
//	@Order(6)
//	@DisplayName("GetListPaging")
//	@Timeout(value=2, unit=TimeUnit.SECONDS)
//	void testSelectGetListPaging() {
//		log.trace("testGetListPaging() invoked.");
//		
//		Criteria cri = new Criteria();
//		cri.setPageNum(1);
//
//		List<FarmVO> list = this.mapper.getListPaging(cri);
//		assert list != null;
//		list.forEach(log::info);
//		
//		
//	} // testSelectTotalCount
	
	
	

} // end class