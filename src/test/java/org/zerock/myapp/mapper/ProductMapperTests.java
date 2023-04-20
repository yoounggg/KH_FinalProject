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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.myapp.domain.AttachImageVO;
import org.zerock.myapp.domain.CategoryVO;
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
			dto.setCategory("test");
			dto.setName("감차빵dq");
			dto.setPrice(3000);
			dto.setDiscount(10);
			dto.setDiscount_price(2700);
			dto.setWeight("2kgqd");
			dto.setOrigin("국산(강원도 춘천시q)");
			dto.setStock(10);
			dto.setFarm_no(1);
			dto.setMain_image("test");
			
			dto.setContent("먹고말테야.");
			dto.setContent_image("cowntent_image1");
			
			log.trace("Before ProductDTO : " + dto);
			
			int affectedLines = this.mapper.insert(dto);
			log.info("affectedLines() invoked. {}", affectedLines);
		
			log.trace("After ProductDTO : " + dto);
			
	} // insert
	
	
//	@Disable
	@Test
	@Order(5)
	@DisplayName("테스트 : insertImage")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void imageInsert() {
		log.trace("imageInsert invoked() - 이미지 경로 등록");
		
		AttachImageVO vo = new AttachImageVO();
		
		vo.setProduct_no(43);
		vo.setFileName("test");
		vo.setUploadPath("test");
		vo.setUuid("test2");
		
		int affectedLines = this.mapper.imageInsert(vo);
		log.info("affectedLines() invoked. {}", affectedLines);

		
	} // imageInsert
	
	
//	@Disable
	@Test
	@Order(6)
	@DisplayName("테스트 : cateList")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void cateList() {
		log.trace("cateList invoked() - 카테고리 등록");
		
		List<CategoryVO> list = this.mapper.cateList();
		assertNotNull(list);
		
		list.forEach(log::info);
		
		
	} // cateList
	
	
	

} // end class
