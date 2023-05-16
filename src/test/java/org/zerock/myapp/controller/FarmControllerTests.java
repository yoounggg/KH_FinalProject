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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.myapp.domain.FarmVO;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
						"file:src/main/webapp/**/spring/root-*.xml",
						"file:src/main/webapp/**/spring/**/servlet-*.xml"
					  })
@WebAppConfiguration

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FarmControllerTests {
	
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

	
//	@Disable
	@Test
	@Order(1)
	@DisplayName("테스트 1 : list")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testList() throws Exception {		
		log.trace("testList() invoked.");
		// MockMvc를 지어줄 "건설사(Builder)"부터 획득
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		// BoardController의 /board/list, GET 핸들러 테스트
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/farm/list");
		requestBuilder.param("currPage", "1");
		requestBuilder.param("amount", "10");
		
		// 이제 가상의 MVC 환경에서, BoardController에 요청 생성 및 전송
//		@Cleanup("clear") // Collection은 clear 메소드로 닫아야 함!
		ModelAndView modelAndView = mockMvc.perform(requestBuilder).andReturn().getModelAndView();
		log.info("\t+ modelAndView : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());
		
		
	} // testList
	
	
	
//	@Disable
	@Test
	@Order(2)
	@DisplayName("테스트 2 : get")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testGet() throws Exception {		
		log.trace("testGet() invoked.");

		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/farm/get");
		requestBuilder.param("no", "41");
		
		ModelAndView modelAndView 
		= mockMvc.perform(requestBuilder).andReturn().getModelAndView();

		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
		
	} // testGet
	
	
	
//	@Disable
	@Test
	@Order(3)
	@DisplayName("testRemove")
	@Timeout(value=60, unit=TimeUnit.SECONDS)
	void testRemove() throws Exception{
		log.trace("testRemove() invoked.");
	
		// MockMvc를 지어줄 "건설사Builder" 부터 획득
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx); 
		MockMvc mockMvc = mockMvcBuilder.build();
		
		//BoardController의 /board/list, GET 핸들러 테스트
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/farm/remove");
		requestBuilder.param("no", "17"); // ... <- 가변인자
		
		// 컬렉션 아니어서 cleanup 안해도됨.
		// 이제 가상의 MVC 환경에서, BoardController에 요청생성 및 전송
		ModelAndView modelAndView = 
				mockMvc.
				 perform(requestBuilder).
				 andReturn().
				 getModelAndView();		
		log.info("\t+ modelAndView : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());

	} // testRemove
	
	
	
	
//	@Disable
	@Test
	@Order(4)
	@DisplayName("testRegister")
	@Timeout(value=60, unit=TimeUnit.SECONDS)
	void testRegister() throws Exception{
		log.trace("testRegister() invoked.");
	
		// MockMvc를 지어줄 "건설사Builder" 부터 획득
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx); 
		MockMvc mockMvc = mockMvcBuilder.build();
		
		//BoardController의 /board/list, GET 핸들러 테스트
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/farm/register");
		requestBuilder.param("name", "(주)채영"); // ... <- 가변인자		
		requestBuilder.param("business_no", "789-78-45654"); // ... <- 가변인자
		requestBuilder.param("location", "서울특별시 강남구 테헤란로10길 15"); // ... <- 가변인자
		requestBuilder.param("tel", "010-1212-1546"); // ... <- 가변인자
		
		// 컬렉션 아니어서 cleanup 안해도됨.
		// 이제 가상의 MVC 환경에서, BoardController에 요청생성 및 전송
		ModelAndView modelAndView = 
				mockMvc.
				 perform(requestBuilder).
				 andReturn().
				 getModelAndView();		
		log.info("\t+ modelAndView : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());

	} // testList
	
	
//	@Disable
	@Test
	@Order(5)
	@DisplayName("testModify")
	@Timeout(value=60, unit=TimeUnit.SECONDS)
	void testModify() throws Exception{
		log.trace("testModify() invoked.");
		
		// MockMvc를 지어줄 "건설사Builder" 부터 획득
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx); 
		MockMvc mockMvc = mockMvcBuilder.build();
		
		//========================== STEP 1. 게시글을 상세 조회 ======================
		//BoardController의 /board/list, GET 핸들러 테스트
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/farm/get");
		requestBuilder.param("no", "40"); // ... <- 가변인자
		
		// 컬렉션 아니어서 cleanup 안해도됨.
		// 이제 가상의 MVC 환경에서, BoardController에 요청생성 및 전송
		ModelAndView modelAndView = 
				mockMvc.
				 perform(requestBuilder).
				 andReturn().
				 getModelAndView();	
		
		log.info("\t+ viewName : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());

		ModelMap model = modelAndView.getModelMap();
		log.info("\t+ model: {}, type : {}", model, model.getClass().getName());
		
		FarmVO vo = (FarmVO) model.getAttribute("farm");
		assertNotNull(vo);
		
		log.info("\t+ vo: {}", vo);
		
		
		//========================== STEP2. 20 bno 게시글을 수정 ======================
		
		Integer no = vo.getNo();
		String name = vo.getName();
		String business_no = vo.getBusiness_no();
//		String location = vo.getLocation();
//		String tel = vo.getTel();
		
		requestBuilder = MockMvcRequestBuilders.post("/admin/farm/modify");
		requestBuilder.param("no", no.toString());
		requestBuilder.param("name", name);
		requestBuilder.param("business_no", business_no);
		requestBuilder.param("location", "서울특별시 강남구 테헤란로10길 12");
		requestBuilder.param("tel", "010-4545-5656");
		
		modelAndView = 
				mockMvc.
				 perform(requestBuilder).
				 andReturn().
				 getModelAndView();	
		
		// 가상의 MVC 환경에서, BoardController 에 요청생성 및 전송
		log.info("\t+ viewName : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());
		
	} // testList
	
}