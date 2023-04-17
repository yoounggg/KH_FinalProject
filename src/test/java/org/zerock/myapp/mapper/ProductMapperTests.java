package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.zerock.myapp.domain.ProductDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductMapperTests {

	@Setter(onMethod_= { @Autowired })
	private ProductMapper mapper;
	
	@BeforeAll
	void beforeAll() {
		log.trace("\t beforeAll() invoked");
		
		assertNotNull(this.mapper);
		log.info("\t this.mapper {}", this.mapper);
	} // beforeAll
	
//	@Disabled
//	@Test
//	@Order(1)
//	@DisplayName("testGetList")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void testGetList() {
//		log.trace("\t testGetList() invoked");
//		
//		Criteria cri = new Criteria();
//		cri.setAmount(12);
//		cri.setCurrPage(1);
//	
//		List<ProductVO> list = this.mapper.SelectAllList(cri);
//		
//		assert list != null;
//		list.forEach(log::info);
//		
//	} // testGetMapper
//	
////	@Disabled
//	@Test
//	@Order(2)
//	@DisplayName("testGetMenuOrder")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void testGetMenuOrder(Criteria cri) {
//		log.trace("\t testGetMenuOrder() invoked");
//		
//		cri.setCurrPage(1);
//		cri.setAmount(12);
//		List<ProductVO> list = this.mapper.SelectOrder(cri);
//		
//		Objects.requireNonNull(list);
//		list.forEach(log::info);
//	} // testGEtMenuOrder
//	
//	
////	@Disabled
//	@Test
//	@Order(3)
//	@DisplayName("testSelectDetail")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void testSelectDetail() {
//		log.trace("\t testSelectDetail() invoked");
//	
//		Integer pno = 1;
//		ProductDTO dto = this.mapper.SelectDetail(pno);
//		
//		assertNotNull(dto);
//		log.info("\t dto : {}", dto);
//		
//	} // testSelectDetail
	
	
//	[별이]상품등록 테스트 추가
//	@Disable
	@Test
	@Order(4)
	@DisplayName("테스트 : insert")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void insert() {
		log.trace("insert invoked().");
		
			ProductDTO dto = new ProductDTO();
			dto.setCategory1("농가");
			dto.setCategory2("국내외과일");
			dto.setName("찬돌농장 고당도 프리미엄 샤인머스켓");
			dto.setPrice(30000);
			dto.setDiscount(10);
			dto.setDiscount_price(27000);
			dto.setWeight("2kg");
			dto.setOrigin("국산(경상북도 안동시)");
			dto.setStock(10);
			dto.setFarm_no(1);
			dto.setMain_image("main1");
			dto.setSub_image1("sub1");
			dto.setSub_image2("sub2");
			dto.setSub_image3("sub3");
			dto.setSub_image4("sub4");
			dto.setContent("경상북도 안동시 예안면 상활지에서 생산한 탐스럽고 맛있는 샤인머스켓입니다. 달고 아삭한 식감을 가진 가족의 건강과 선물용으로 아주 좋은 상품입니다.");
			dto.setContent_image("content_image1");
			
			int affectedLines = this.mapper.insert(dto);
			log.info("affectedLines() invoked. {}", affectedLines);
		
	} // insert
}
