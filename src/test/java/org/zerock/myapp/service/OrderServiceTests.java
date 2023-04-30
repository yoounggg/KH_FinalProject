package org.zerock.myapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.zerock.myapp.domain.OrderDTO;
import org.zerock.myapp.domain.OrderItemDTO;
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

	@Setter(onMethod_ = { @Autowired })
	private OrderService orderService;

	@BeforeAll
	void beforeAll() {

		log.trace("beforeAll() invoked");

		assertNotNull(orderService);
		log.trace("\t+ this.orderService", this.orderService);
	}

	// @Disabled
	@Test
	@Order(1)
	@DisplayName("testGetProductsInfo")
	@Timeout(value = 1, unit = TimeUnit.MINUTES)
	void testGetProductsInfo() {

		List<OrderPageItemDTO> orders = new ArrayList<>(); // OrderPageItemDTO타입의 orders 리스트 생성,
		OrderPageItemDTO order1 = new OrderPageItemDTO();
		order1.setProductId(21);
		order1.setProductCount(2);
		orders.add(order1);

		OrderPageItemDTO order2 = new OrderPageItemDTO();
		order2.setProductId(10);
		order2.setProductCount(3);
		orders.add(order2);

//	    String memberId = "codud"; 

		List<OrderPageItemDTO> result;
		try {
			result = orderService.getProductsInfo(orders);
			assertNotNull(result);
			assertFalse(result.isEmpty());
		} catch (ServiceException e) {
			e.printStackTrace();
		} // try-catch

	} // testGetProductsInfo

	// @Disabled
	@Test
	@Order(2)
	@DisplayName("testOrder")
	@Timeout(value = 1, unit = TimeUnit.MINUTES)
	void testOrder() {
		// given
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMember_id("chandoll");
		List<OrderItemDTO> orders = new ArrayList<>();
		OrderItemDTO orderItemDTO = new OrderItemDTO();
		orderItemDTO.setNo(65);
		orderItemDTO.setCount(1);
		orders.add(orderItemDTO);
		orderDTO.setOrders(orders);
		
		// when
		orderService.order(orderDTO);
		
		// then
		// 특별한 검증 로직은 없음. 
		// 주문이 정상적으로 처리되면 데이터베이스에서 확인 가능함. 
	}

}
