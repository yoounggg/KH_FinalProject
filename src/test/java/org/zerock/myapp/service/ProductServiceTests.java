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
import org.zerock.myapp.domain.CategoryVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ProductDTO;
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
public class ProductServiceTests {

	@Setter(onMethod_= { @Autowired })
	private ProductService service;	 // ProductService(interface) 주입
	
	@BeforeAll
	void beforeAll() {
		log.trace("\t beforeAll() invoked");
		
		assertNotNull(this.service);
		log.info("\t this.service : {}",this.service);
	} // beforeAll
	
	
//	@Disabled
//	@Test
//	@Order(1)
//	@DisplayName("테스트 1 : contextLoads")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void contextLoads() throws ServiceException{
//		log.trace("contextLoads() invoked");
//		
//	} // getListTest
	
	
	/* 상품 목록 조회 */
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("테스트 1 : getList")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void getList() throws ServiceException{
		log.trace("\t getListTest() invoked");
		
		List<ProductDTO> list = this.service.getList();
		
		assertNotNull(list);
		log.info("list : " + list);
		
	} // getListTest
	
	
	/* 상품 목록 조회(페이징) */
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("테스트 2 : getListPaging")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void getListPaging() throws ServiceException{
		log.trace("\t getListPaging() invoked");
		
		Criteria cri = new Criteria();
		List<ProductDTO> list = this.service.getListPaging(cri);
		
		assertNotNull(list);
		log.info("list : " + list);
		
	} // getListPaging
	
	

	/* 상품 상세 조회 */
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("테스트 3 : get")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void get() throws ServiceException {
		log.trace("\t get() invoked");
		
		int no = 121;
		ProductDTO dto = this.service.get(no);
		
		assertNotNull(dto);
		log.info("dto : "+dto);
		
	} // get
	
	
	/* 상품 등록 */
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("테스트 4 : register")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void register() throws ServiceException {
		log.trace("register() invoked");
		
		ProductDTO dto = new ProductDTO();
	    	dto.setCategory("10300");
	    	dto.setName("후추후추");
	    	dto.setPrice(10000);
			dto.setDiscount(10);
			dto.setDiscount_price(9000);
			dto.setWeight("150g");
			dto.setOrigin("국산");
			dto.setStock(10);
			dto.setFarm_no(1);
			dto.setMain_image("Main_image");
			dto.setMain_image2("Main_image2");
			dto.setSub_image1("Sub_image1");
			dto.setSub_image2("Sub_image2");
			dto.setSub_image3("Sub_image3");
			dto.setSub_image4("Subn_image4");
			dto.setContent("후추후추");
			dto.setContent_image("Content_image");
		
		boolean success = this.service.register(dto);
		log.info("\t+ success : {}", success);
		
	} // register

	
	
	/* 상품 수정 */
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("테스트 5 : modify")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void modify() throws ServiceException {
		log.trace("modify() invoked");
		
		ProductDTO dto = new ProductDTO();
			dto.setNo(121);
    		dto.setCategory("10300");
	    	dto.setName("후추후추훗충수정지낮수정");
	    	dto.setPrice(10000);
			dto.setDiscount(20);
			dto.setDiscount_price(8000);
			dto.setWeight("150g");
			dto.setOrigin("국내");
			dto.setStock(10);
			dto.setFarm_no(1);
			dto.setMain_image("Main_image");
			dto.setMain_image2("Main_image2");
			dto.setSub_image1("Sub_image1");
			dto.setSub_image2("Sub_image2");
			dto.setSub_image3("Sub_image3");
			dto.setSub_image4("Subn_image4");
			dto.setContent("후추후추");
			dto.setContent_image("Content_image");
		
		boolean success = this.service.modify(dto);
		log.info("\t+ success : {}", success);
		
	} // register
	
	
	/* 상품 삭제 */
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("테스트 6 : remove")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void remove() throws ServiceException {
		log.trace("\t get() invoked");
		
		int no = 123;
		boolean success = this.service.remove(no);
		
		log.info("success : {}", success);
		
	} // get
	
	
	/* 카테고리 목록 */
//	@Disable
	@Test
	@Order(2)
	@DisplayName("테스트 7 : getCateList")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void getCateList() throws ServiceException {
		log.trace("testGetCateList() invoked.");
	
		List<CategoryVO> list = this.service.getCateList();
		
		assert list != null;
		list.forEach(log::info);
		
	} // getCateList
	
	
	
} // end class
