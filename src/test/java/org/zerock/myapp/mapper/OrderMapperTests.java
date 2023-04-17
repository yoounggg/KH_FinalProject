package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.zerock.myapp.domain.OrderPageItemDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderMapperTests {
	
	@Setter(onMethod_= {@Autowired})
	private OrderMapper orderMapper;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked");
		
		assertNotNull(this.orderMapper);
		log.info("\t+this.mapper:{}, type:{}", this.orderMapper);
	} // beforeAll
	
	
	//@Disabled
	@Test
	@Order(1)
	@DisplayName("테스트1: TestGetProductsInfo")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestGetProductsInfo() {
	    Integer productId = 1; // 테스트용 상품 ID
	    OrderPageItemDTO product = orderMapper.getProductsInfo(productId);
	    assertNotNull(product);
	    assertEquals(productId, product.getProductId());
	    // 예를 들어, product.getName(), product.getPrice() 등을 확인하는 코드를 작성할 수 있습니다.

	} // TestGetProductsInfo
} // end class
