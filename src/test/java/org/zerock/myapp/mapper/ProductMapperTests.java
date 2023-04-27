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
import org.zerock.myapp.domain.CategoryVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ProductDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-context.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductMapperTests {

	@Setter(onMethod_= { @Autowired })
	private ProductMapper mapper;

	
	@BeforeAll
	void beforeAll() {
		log.trace("\t beforeAll() invoked");
	} // beforeAll
	
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("테스트 1 : contextLoads")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.trace("contextLoads() invoked");
		
	} // testGetMapper
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("테스트 1 : selectAll")
	@Timeout(value=60, unit=TimeUnit.SECONDS)
	void selectAll() {
		log.trace("selectAll() invoked");
		
		List<ProductDTO> list = this.mapper.selectAll();
		
		assert list != null;
		log.info("select() invoked.");
		
	} // testGetMapper
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("테스트 2 : select")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void select() {
		log.trace("select() invoked");
		
		int no = 21;
		ProductDTO dto = this.mapper.select(no);
		
		assert dto != null;
		log.info("vo : {}", dto);
//		log.info("\t dto : {}", dto);		
	} // select
	

	
//	[별이]상품등록 테스트 추가
//	@Disable
	@Test
	@Order(3)
	@DisplayName("테스트 3 : insert")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void insert() {
		log.trace("insert invoked().");

			ProductDTO dto = new ProductDTO();
	        	dto.setCategory("10300");
	        	dto.setName("토마토");
	        	dto.setPrice(15000);
				dto.setDiscount(50);
				dto.setDiscount_price(7500);
				dto.setWeight("500g");
				dto.setOrigin("국산");
				dto.setStock(10);
				dto.setFarm_no(1);
				dto.setMain_image("Main_image");
				dto.setMain_image2("Main_image2");
				dto.setSub_image1("Sub_image1");
				dto.setSub_image2("Sub_image2");
				dto.setSub_image3("Sub_image3");
				dto.setSub_image4("Subn_image4");
				dto.setContent("톰아토");
				dto.setContent_image("Content_image");
				
			int affectedLines = this.mapper.insert(dto);
			log.info("affectedLines() invoked. {}", affectedLines);
			
	} // insert
	
	
//	@Disable
	@Test
	@Order(4)
	@DisplayName("테스트 4 : update")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void update() {
		log.trace("insert invoked().");

			ProductDTO dto = new ProductDTO();
				dto.setNo(120);
	        	dto.setCategory("10400");
	        	dto.setName("투투투xhxhxhxh투투마토");
	        	dto.setPrice(15000);
				dto.setDiscount(60);
				dto.setDiscount_price(6000);
				dto.setWeight("500g");
				dto.setOrigin("국산");
				dto.setStock(10);
				dto.setFarm_no(1);
				dto.setMain_image("Main_image");
				dto.setMain_image2("Main_image2");
				dto.setSub_image1("Sub_image1");
				dto.setSub_image2("Sub_image2");
				dto.setSub_image3("Sub_image3");
				dto.setSub_image4("Subn_image4");
				dto.setContent("톰톰아토");
				dto.setContent_image("Content_image");
			
			int affectedLines = this.mapper.update(dto);
			log.info("affectedLines() invoked. {}", affectedLines);
			
	} // update
	
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("테스트 5 : delete")
	@Timeout(value=100, unit=TimeUnit.SECONDS)
	void delete() {
		log.trace("delete() invoked");
		
		int no = 120;
		int affectedLines = this.mapper.delete(no);

		log.info("dto : {}", affectedLines);
		
	} // delete
	
	
	
//	@Disable
	@Test
	@Order(6)
	@DisplayName("테스트 6 : cateList")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void cateList() {
		log.trace("cateList invoked() - 카테고리 등록");
		
		List<CategoryVO> list = this.mapper.cateList();
		assertNotNull(list);
		
		list.forEach(log::info);
		
		
	} // cateList
	
	
//	@Disable
	@Test
	@Order(7)
	@DisplayName("테스트 6 : selectAllPaging")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void selectAllPaging() {
		log.trace("selectAllPaging() invoked.");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(1);

		List<ProductDTO> list = this.mapper.selectAllPaging(cri);
		assert list != null;
		list.forEach(log::info);
		
		
	} // selectAllPaging

} // end class
