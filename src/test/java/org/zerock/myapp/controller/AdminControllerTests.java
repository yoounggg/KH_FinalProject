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
import org.zerock.myapp.domain.ProductDTO;
import org.zerock.myapp.service.ProductService;

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
public class AdminControllerTests {
	
	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	private ProductService service;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll invoked. (전처리)");
	}
	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("contextLoads")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.trace("contextLoads invoked. (테스트 실행 테스트)");
	}
	
	
//	@Disable
	@Test
	@Order(2)
	@DisplayName("테스트 2 : register")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testRegister() throws Exception {		
		log.trace("testRegister() invoked.");

		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/product/register");
			requestBuilder.param("category1", "농가");
			requestBuilder.param("category2", "국내외과일");
			requestBuilder.param("name", "채영농장 스테비아 방울토마토");
			requestBuilder.param("price", "10000");
			requestBuilder.param("discount", "20");
			requestBuilder.param("discount_price", "8000");
			requestBuilder.param("weight","2kg");
			requestBuilder.param("origin", "국산");
			requestBuilder.param("stock", "10");
			requestBuilder.param("farm_no", "1");
			requestBuilder.param("main_image", "main_1");
			requestBuilder.param("sub_image1", "sub_1");
			requestBuilder.param("sub_image2", "sub_2");		
			requestBuilder.param("sub_image3", "sub_3");
			requestBuilder.param("sub_image4", "sub_4");
			requestBuilder.param("content", "신선한 망고향이 듬-뿍 스테비아 토마토! 헤어나올 수 없는 검증된 맛! 토마토에 설탕을 뿌린걸까? 착각할 정도의 달달함!");
			requestBuilder.param("content_image","content_image1");
		
		ModelAndView modelAndView 
		= mockMvc.perform(requestBuilder).andReturn().getModelAndView();

		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
		
	} // testRegister
	
	
} // end class
