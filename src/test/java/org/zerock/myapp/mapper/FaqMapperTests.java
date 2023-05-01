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
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FaqDTO;
import org.zerock.myapp.domain.FaqVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FaqMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private FaqMapper mapper;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	}
	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : selectAll")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testSelectAll() {
		log.trace("testSelectAll() invoked.");
		
		List<FaqVO> list = this.mapper.selectAll();
		
		assertNotNull(list);
		
		list.forEach(log::info);
	} // testSelectAll
	
	
//	@Disable
	@Test
	@Order(2)
	@DisplayName("테스트 2 : select")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testSelect() {
		log.trace("testSelect() invoked.");
		
		Integer no = 8;
		// 우선 특정 조회할 글의 번호를 부여함
		
		FaqVO vo = this.mapper.select(no);
		// 그다음 vo에 넣는다
		
		assertNotNull(vo);
		
		log.info("\t+ vo : {}", vo);
	} // testSelectAll
	
	
	
//	@Disable
	@Test
	@Order(3)
	@DisplayName("테스트 3 : delete")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void testDelete() {
		log.trace("testDelete() invoked.");
		
		Integer no = 8;
		// 우선 특정 조회할 글의 번호를 부여함
		
		FaqVO vo = this.mapper.select(no);
		// 그다음 vo에 넣는다
		
		assertNotNull(vo);
		
		log.info("\t+ vo : {}", vo);
	} // testSelectAll
	
//	@Disable
	@Test
	@Order(4)
	@DisplayName("테스트 4 : insert")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void testInsert() {
		log.trace("testInsert() invoked.");
		
		FaqDTO dto = new FaqDTO();
		dto.setTitle("자주묻는질문11");
		dto.setAnswer("자주묻는답변11");
		dto.setWriter("admin");
		
		int affectedLines = this.mapper.insert(dto);
		
		
		log.info("\t+ affectedLines : {}", affectedLines);
	
	} // testInsert
	
	
	
//	@Disable
	@Test
	@Order(5)
	@DisplayName("테스트 5 : update")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void testUpdate() {
		log.trace("testUpdate() invoked.");
		
		FaqDTO dto = new FaqDTO();
		dto.setNo(9);
		dto.setTitle("자주묻는질문12");
		dto.setAnswer("자주묻는답변12");
		dto.setWriter("admin");
		
		int affectedLines = this.mapper.insert(dto);
		
		
		log.info("\t+ affectedLines : {}", affectedLines);
	
	} // testUpdate
	
	
//	@Disable
//	@Test
//	@Order(6)
//	@DisplayName("GetListPaging")
//	@Timeout(value=60, unit=TimeUnit.SECONDS)
//	void testSelectGetListPaging() {
//		log.trace("testGetListPaging() invoked.");
//		
//		Criteria cri = new Criteria();
//		cri.setCurrPage(1);
//
//		List<FaqVO> list = this.mapper.getListPaging(cri);
//		assert list != null;
//		list.forEach(log::info);
//		
//		
//	} // testSelectTotalCount
	

	

} // end class