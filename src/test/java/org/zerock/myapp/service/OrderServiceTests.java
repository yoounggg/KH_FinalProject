package org.zerock.myapp.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.zerock.myapp.domain.OrderPageItemDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTests {

	@Setter(onMethod_= {@Autowired})
	private OrderService orderService;
	
	@BeforeAll
	void beforeAll() {
		
		log.trace("beforeAll() invoked");
		
		assertNotNull(orderService);
		log.trace("\t+ this.orderService", this.orderService);
	}
	
	//@Disabled
	@Test
	@Order(1)
	@DisplayName("testGetProductsInfo")
	@Timeout(value =1, unit=TimeUnit.MINUTES)
	void testGetProductsInfo () {
		
//	    List<OrderPageItemDTO> orders = new ArrayList<>();
//	    OrderPageItemDTO order1 = new OrderPageItemDTO();
//	    order1.setProductId(1);
//	    order1.setProductCount(2);
//	    orders.add(order1);
//	    
//	    OrderPageItemDTO order2 = new OrderPageItemDTO();
//	    order2.setProductId(10);
//	    order2.setProductCount(3);
//	    orders.add(order2);
	    
//	    String memberId = "codud"; 
	    
//	    List<OrderPageItemDTO> result;
//		try {
//			result = orderService.getProductsInfo(orders);
//		    assertNotNull(result);
//		    assertFalse(result.isEmpty());
//		} catch (ServiceException e) {
//			e.printStackTrace(); 
//		} // try-catch
//		
	} // testGetProductsInfo
	
}
