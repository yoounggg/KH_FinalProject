package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;
import org.zerock.myapp.domain.OrderPageItemDTO;
import org.zerock.myapp.domain.ProductDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MypageMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private MypageMapper mypageMapper;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked");
		
		assertNotNull(this.mypageMapper);
		log.info("\t+this.mapper:{}, type:{}", this.mypageMapper);
	} // beforeAll
	

	//@Disabled
	@Test
	@Order(1)
	@DisplayName(" test: TestGetOrderInfoTest()")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestgetOrder() {
//		OrderDTO orderInfo = mypageMapper.getOrder(170);  	 
																	
//		log.trace(" orderInfo : {} " + orderInfo);
	} // TestGetOrderInfoTest()
	
	//@Disabled
	@Test
	@Order(2)
	@DisplayName(" test: TestgetSelect()")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestgetSelect() {
		OrderDTO select = mypageMapper.select(304);  	 
																	
		log.trace(" select : {} " + select);
	} // TestGetOrderInfoTest()
	
	//@Disabled
	@Test
	@Order(3)
	@DisplayName(" test: TestgetItemSelect()")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestgetItemSelect() {
		List<OrderItemDTO> ItemSelect = mypageMapper.ItemSelect(304);  	 
																	
		log.trace(" select : {} " + ItemSelect);
	} // TestGetOrderInfoTest()

	//@Disabled
	@Test
	@Order(4)
	@DisplayName(" test: TestProductName()")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestProductName() {
		List<ProductDTO> productName = mypageMapper.productName(147);  	 
																	
		log.trace(" productName : {} " + productName);
	} // TestGetOrderInfoTest()
	
	//@Disabled
	@Test
	@Order(5)
	@DisplayName(" test: TestItemPrice()")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestItemPrice() {				
		
	    Integer productNo = 204;
	    List<ProductDTO> orderItems = mypageMapper.productName(productNo);
	    assertEquals(1, orderItems.size());
	    assertEquals("국내산 파프리카 1입 (빨강/노랑, 150g)", orderItems.get(0).getName());
	    assertEquals(2600, orderItems.get(0).getPrice());
	    
		log.trace(" orderItems : {} " + orderItems);
		
	} // TestdeductStock() 

} // end class
