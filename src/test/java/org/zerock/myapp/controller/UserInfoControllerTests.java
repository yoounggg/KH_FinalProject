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
@ContextConfiguration(locations= {
		"file:src/main/webapp/**/spring/**/root-*.xml",
		"file:src/main/webapp/**/spring/**/servlet-*.xml"
})
@WebAppConfiguration	

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserInfoControllerTests {
	@Setter(onMethod_= {@Autowired})
	
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@BeforeAll
	void beforeAll() {	//1회성 전처리 수행
		log.trace("beforeAll() invoked.");
	
		// 의존성 주입(DI) 확인
		assertNotNull(this.ctx);
		log.info("\t+ this.ctx: {}", this.ctx);
		
	} // beforeAll()
	
	@Test
	@Order(1)
	@DisplayName("테스트: 회원탈퇴")
	@Timeout(value=60, unit=TimeUnit.SECONDS)
	void testdeleteUser() throws Exception{
		log.trace("testdeleteUser() invoked.");
	
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx); 
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mypage/userInfo/{id}/delete");
		requestBuilder.param("id", "coduddl");

		ModelAndView modelAndView = 
				mockMvc.
				 perform(requestBuilder).
				 andReturn().
				 getModelAndView();
		
		log.info("\t+ modelAndView : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());
	} // testdeleteUser
	
}
