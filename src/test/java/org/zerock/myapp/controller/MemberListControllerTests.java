package org.zerock.myapp.controller;


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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

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
public class MemberListControllerTests {
	
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll invoked. (전처리)");
	}
	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : contextLoads")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.trace("contextLoads invoked. (테스트 실행 테스트)");
	}
	

//	목업 건설사
//	@Disable
	@Test
	@Order(2)
	@DisplayName("테스트 2 : getListPaging")
	@Timeout(value=60, unit=TimeUnit.SECONDS)
	void getListPaging() throws Exception{
		log.trace("getListPaging() invoked.");
	
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx); 
		MockMvc mockMvc = mockMvcBuilder.build();

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/member/list");
		requestBuilder.param("currPage", "1");
		requestBuilder.param("amount", "10");

		// 이제 가상의 MVC 환경에서, BoardController에 요청생성 및 전송
//		@Cleanup("clear")
		ModelAndView modelAndView = mockMvc.perform(requestBuilder).andReturn().getModelAndView();
		log.info("\t+ modelAndView : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());
	
	} // getListPaging
	
	
	/* 상품 상세 조회 */
//	@Disable
	@Test
	@Order(3)
	@DisplayName("테스트 3 : get")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void get() throws Exception {		
		log.trace("testGet() invoked.");

		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/member/get");
		requestBuilder.param("id", "mooyaho");
		
		ModelAndView modelAndView 
		= mockMvc.perform(requestBuilder).andReturn().getModelAndView();

		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
		
	} // get
	
	
} // end class
