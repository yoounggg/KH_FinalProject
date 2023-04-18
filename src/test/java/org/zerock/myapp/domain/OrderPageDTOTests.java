package org.zerock.myapp.domain;

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

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderPageDTOTests {
	
	@Setter(onMethod_= {@Autowired})
	private OrderPageDTO orderPageDTO;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked");
		
		assertNotNull(orderPageDTO);
		log.trace("/t+ this.orderPageDTO", this.orderPageDTO);
	} //beforeAll()
	
	//@Disabled
	@Test
	@Order(1)
	@DisplayName("testOrders")
	@Timeout(value=1, unit=TimeUnit.MINUTES)
	void testOrders() {
		
		List<OrderPageItemDTO> orderItems = new ArrayList<>();
	    // orderItems에 OrderPageItemDTO 객체를 추가하는 코드 작성
	    orderPageDTO.setOrders(orderItems);
	    assertEquals(orderItems, orderPageDTO.getOrders());
		
	} // testOrders()

}
