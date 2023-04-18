package org.zerock.myapp.controller;

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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.myapp.service.MemberService;
import org.zerock.myapp.service.OrderService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/**/spring/root-*.xml",
		"file:src/main/webapp/**/spring/**/servlet-*.xml"})
@WebAppConfiguration

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class OrderControllerTests {

//	@Setter(onMethod_=@Autowired)
//	private OrderController orderController;
	
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
    private MockMvc mockMvc;
    
	@BeforeAll
	void beforeAll() {	// 1회성 전처리 수행
		
		log.trace("beforeAll() invoked.");
		
		// 의존성 주입(DI) 확인
		assertNotNull(this.ctx);
		log.info("\t+ this.ctx: {}", this.ctx);
		
		//
	} // beforeAll()
	
	//@Display
    @Test
    @Order(1)
    @DisplayName("testOrderController")
    @Timeout(value=2, unit=TimeUnit.SECONDS)
    void testOrderController() throws Exception {
    	
    	// mockMvc를 만들 공장 생성,  WebApplicationContext를 설정하여 MockMvcBuilder를 빌드
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		 // MockMvcRequestBuilders 클래스의 get() 메소드를 이용하여 GET 요청을 생성, memberId 변수를 전달
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/{memberId}", "codud");
        
		// mockMvc.perform() 메소드를 이용하여 요청을 실행하고, ModelAndView 객체를 반환
		ModelAndView modelAndView = mockMvc.perform(requestBuilder).andReturn().getModelAndView();
		
		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
    } // testOrderController
    


} // endclass