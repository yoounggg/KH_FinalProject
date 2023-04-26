package org.zerock.myapp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
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
import org.zerock.myapp.domain.CategoryVO;
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTests {

//	@Setter(onMethod_= { @Autowired })
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
//	@DisplayName("getListTest")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void getListTest() throws ServiceException{
//		log.trace("\t getListTest() invoked");
//		
//		Criteria cri = new Criteria();
//		cri.setCurrPage(1);
//		cri.setAmount(12);
//		
//		List<ProductVO> list = this.service.getList(cri);
//		
//		assertNotNull(list);
//		list.forEach(log::info);
//		
//	} // getListTest
//	
////	@Disabled
//	@Test
//	@Order(2)
//	@DisplayName("getRecodeCount")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void getRecodeCount() throws ServiceException {
//		log.trace("\t getRecodeCount() invoked");
//		
//		Integer tempNum = this.service.getRecodeCount();
//		
//		Objects.requireNonNull(tempNum);
//		log.info("\t tempNum : {}", tempNum);
//	} // getRecodeCount
//	
//	
////	@Disabled
//	@Test
//	@Order(3)
//	@DisplayName("getMenuOrder")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void getMenuOrder() throws ServiceException {
//		log.trace("\t getMenuOrder() invoked");
//		
//		Criteria cri = new Criteria();
//		cri.setCurrPage(1);
//		cri.setAmount(12);
//		List<ProductVO> list = this.service.getOrder(cri);
//		
//		assert list != null;
//		list.forEach(log::info);
//	} // getMenuOrder
//	
//	
////	@Disabled
//	@Test
//	@Order(4)
//	@DisplayName("getProductDetail")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void getProductDetail() throws ServiceException {
//		log.trace("\t getProductDetail() invoked");
//		
//		Integer pno = 10;
//		ProductDTO dto = this.service.getProductDetail(pno);
//		
//		Objects.requireNonNull(dto);
//		log.info("\t dto : {}", dto);
//		
//	} // getProductDetail
	
	
	
//	[별이]상품 등록 테스트
//	@Disable
//	@Test
//	@Order(1)
//	@DisplayName("테스트 1 : register")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void register() throws ServiceException {
//		log.trace("register invoked.");
//		
//		ProductDTO dto = new ProductDTO();
//		dto.setCategory1("농가");
//		dto.setCategory2("국내외과일");
//		dto.setName("찬돌농장 고당도 프리미엄 샤인머스켓");
//		dto.setPrice(30000);
//		dto.setDiscount(10);
//		dto.setDiscount_price(27000);
//		dto.setWeight("2kg");
//		dto.setOrigin("국산(경상북도 안동시)");
//		dto.setStock(10);
//		dto.setFarm_no(1);
//		dto.setMain_image("main1");
//		dto.setSub_image1("sub1");
//		dto.setSub_image2("sub2");
//		dto.setSub_image3("sub3");
//		dto.setSub_image4("sub4");
//		dto.setContent("경상북도 안동시 예안면 상활지에서 생산한 탐스럽고 맛있는 샤인머스켓입니다. 달고 아삭한 식감을 가진 가족의 건강과 선물용으로 아주 좋은 상품입니다.");
//		dto.setContent_image("content_image1");
//		
//		Boolean successs = this.service.register(dto);
//		log.info("affectedLines() invoked. {}", successs);
//		
//	} // register
	
	
//	[별이]이미지 및 경로 첨부 상품 등록 테스트
//	@Disable
//	@Test
//	@Order(1)
//	@DisplayName("테스트 2 : imageRegister")
//	@Timeout(value=1, unit=TimeUnit.SECONDS)
//	void imageRegister() throws ServiceException {
//		log.trace("imageRegister invoked.");
//		
//		ProductDTO dto = new ProductDTO();
//		dto.setCategory("test");
//		dto.setName("채영이네 고당도 정신체리11");
//		dto.setPrice(30000);
//		dto.setDiscount(10);
//		dto.setDiscount_price(27000);
//		dto.setWeight("2kg");
//		dto.setOrigin("국산");
//		dto.setStock(10);
//		dto.setFarm_no(1);
//		dto.setMain_image("main111");
//		dto.setSub_image1("sub111");
//		dto.setSub_image2("sub211");
//		dto.setSub_image3("sub311");
//		dto.setSub_image4("sub411");
//		dto.setContent("정신체리11!");
//		dto.setContent_image("content_image111");
//
//		
//		//이미지 정보
//		List<AttachImageVO> imageList = new ArrayList<AttachImageVO>();
//		
//		AttachImageVO image1 = new AttachImageVO();
//		AttachImageVO image2 = new AttachImageVO();		
//		
//			image1.setFileName("test image1111111");
//			image1.setUploadPath("test1111111");
//			image1.setUuid("test111111111");
//			
//			image2.setFileName("test image222222222");
//			image2.setUploadPath("test222222222");
//			image2.setUuid("test222222222");
//		
//			imageList.add(image1);
//			imageList.add(image2);
//		
//			dto.setImageList(imageList);
//		
//		
//			Boolean successs = this.service.register(dto);
//			log.info("affectedLines() invoked. {}", successs);
		
		
//	} // register
	
	
//	@Disable
	@Test
	@Order(2)
	@DisplayName("테스트 2 : testGetCateList")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testGetCateList() throws ServiceException {
		log.trace("testGetCateList() invoked.");
	
		List<CategoryVO> list = this.service.getCateList();
		
		assert list != null;
		list.forEach(log::info);
		
	} // testGetCateList()
	
	
	
	
} // end class
