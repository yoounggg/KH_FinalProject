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
	
	//@Disabled
	@Test
	@Order(2)
	@DisplayName(" test: TestGetOrderInfoTest()")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestGetOrderInfo() {
		OrderItemDTO orderInfo = orderMapper.getOrderInfo(61);
		
		log.trace(" orderInfo : {} " + orderInfo);
	} // TestGetOrderInfoTest()
	
	//@Disabled
	@Test
	@Order(3)
	@DisplayName(" test: TestEnrollOrder()")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestEnrollOrder() {
		
		OrderDTO ord = new OrderDTO();
		List<OrderItemDTO> orders = new ArrayList();
		
		OrderItemDTO order1 = new OrderItemDTO();
		
		order1.setProductId(61);
		order1.setProductCount(4);
		order1.setPrice(100);
		order1.setDiscount(10);
		order1.initSaleTotal();
		
		ord.setOrders(orders);
		
		ord.setNo(1);				// 주문번호
		ord.setAddressee("찬돌");	// 주문받는사람
		ord.setMemberId("chandoll");// 주문자
		ord.setMemberAddr1(2023);
		ord.setMemberAddr2("서울 특별시 영등포구 대림동");
		ord.setMemberAddr3("지하철 10번출구");
		ord.setOrderState("배송준비");
		ord.getOrderPriceInfo();
		
		orderMapper.enrollOrder(ord);
	} // TestGetOrderInfoTest()
	
	//@Disabled
	@Test
	@Order(3)
	@DisplayName(" test: TestEnrollOrderItemTest()")
	@Timeout(value = 1, unit=TimeUnit.MINUTES)
	void TestEnrollOrderItemTest() {
		
		OrderItemDTO oid = new OrderItemDTO();
		
		oid.setNo(1);
		oid.setProductId(61);
		oid.setProductCount(4);
		oid.setPrice(100);
		oid.setDiscount(10);
		
		oid.initSaleTotal();
		
		orderMapper.enrollOrderItem(oid);
		
	} // TestGetOrderInfoTest()
	
	
} // end class
