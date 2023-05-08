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
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.NoticeDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoticeMapperTests {

	@Setter(onMethod_= {@Autowired})
	private NoticeMapper mapper;
	
	@BeforeAll
	void beforeAll() {
		log.trace("BeforeAll() invokeld.");
	
	} // beforeAll
	
	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("selectAll")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void selectAll() {
		log.trace("selectAll() invoked.");
		
		List<NoticeDTO> list = this.mapper.selectAll();
		assertNotNull(list);
		
		list.forEach(log::info);
		
	} // contextLoads
	
	
//	@Disable
	@Test
	@Order(2)
	@DisplayName("select")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void select() {
		log.trace("select() invoked.");
		
		//값을 부여한 다음 vo에 넣어야 함
		Integer no = 7;
		NoticeDTO vo = this.mapper.select(no);
		assertNotNull(vo);
		
		log.info("\t vo : {}", vo);
		
	} // select
	
	
//	@Disable
	@Test
	@Order(3)
	@DisplayName("delete")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void delete() {
		log.trace("delete() invoked.");
		
		Integer no = 7;
		int affectedLines = this.mapper.delete(no);
		
		log.info("\t+ affectedLines : {}", affectedLines);

		
	} // select
	
//	@Disable
	@Test
	@Order(4)
	@DisplayName("insert")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void insert() {
		log.trace("insert() invoked.");
		
		NoticeDTO dto = new NoticeDTO();
		dto.setTitle("<공지> 7월 둘째주 장원 이벤트 당첨자 발표!");
		dto.setContent("<공지> 7월 둘째주 장원 이벤트 당첨자 발표!");
		dto.setWriter("admin");
		
		int affectedLines = this.mapper.insert(dto);
		
		log.info("\t+ affectedLines : {}", affectedLines);

		
	} // insert
	
	
//	@Disable
	@Test
	@Order(5)
	@DisplayName("update")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void update() {
		log.trace("update() invoked.");
		
		NoticeDTO dto = new NoticeDTO();
		dto.setNo(28);
		dto.setTitle("'<공지> 9월 둘째주 장원 이벤트 당첨자 발표!'");
		dto.setContent("'<공지> 9월 둘째주 장원 이벤트 당첨자 발표!'");
		dto.setWriter("'admin'");
		
		int affectedLines = this.mapper.update(dto);
		
		log.info("\t+ affectedLines : {}", affectedLines);

		
	} // insert
	
	
}// end class
