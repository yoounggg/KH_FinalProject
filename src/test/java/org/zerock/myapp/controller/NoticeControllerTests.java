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
import org.zerock.myapp.domain.NoticeVO;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
						"file:src/main/webapp/**/spring/root-*.xml",
						"file:src/main/webapp/**/spring/**/servlet-*.xml"})
@WebAppConfiguration

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoticeControllerTests {

	@Setter(onMethod_= {@Autowired})
	private WebApplicationContext ctx;
	
	@Setter(onMethod_= {@Autowired})
	private MockMvc mockMvc;
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		
		assertNotNull(this.ctx);
		log.info("\t+ ctx : {}", ctx);
	} // beforeAll
	
	
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
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/notice/list");
		
		// 이제 가상의 MVC 환경에서, BoardController에 요청 생성 및 전송
		@Cleanup("clear") // Collection은 clear 메소드로 닫아야 함!
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
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/notice/get");
		requestBuilder.param("no", "51");
		
		ModelAndView modelAndView 
		= mockMvc.perform(requestBuilder).andReturn().getModelAndView();

		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
		
	} // testGet
	
	
	
//	@Disable
	@Test
	@Order(3)
	@DisplayName("테스트 3 : register")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testRegister() throws Exception {		
		log.trace("testRegister() invoked.");

		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/get");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/notice/register");
		requestBuilder.param("title", "NoticeController테스트");
		requestBuilder.param("content", "NoticeController테스트");
		requestBuilder.param("writer", "admin");
		
		ModelAndView modelAndView 
		= mockMvc.perform(requestBuilder).andReturn().getModelAndView();

		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
		
	} // testRegister
	
	
//	@Disable
	@Test
	@Order(4)
	@DisplayName("테스트 4 : modify")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testModify() throws Exception {		
		log.trace("testModify() invoked.");
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
//		 ========= Step.1 4번 no 게시글을 상세 조회 =========
		
		MockHttpServletRequestBuilder requestBuilder 
			= MockMvcRequestBuilders.get("/admin/notice/get");
		requestBuilder.param("no", "4");
		
		ModelAndView modelAndView 
									= mockMvc.
									perform(requestBuilder).
									andReturn().
									getModelAndView();

		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
		ModelMap model = modelAndView.getModelMap();
		log.info("\t+ model: {}, type: {}", model, model.getClass().getName());
		
		NoticeVO vo = (NoticeVO) model.getAttribute("notice");
		log.info("\t+ vo: {}", vo);
		
//		 ========= Step.2 4번 no 게시글을 수정 =========
		Integer no = vo.getNo();
//		String title = vo.getTitle();
//		String content = vo.getContent();
		String writer = vo.getWriter();
		
		requestBuilder = MockMvcRequestBuilders.post("/admin/notice/modify");
		requestBuilder.param("no", no.toString());
		requestBuilder.param("title", "NoticeController테스트2"); 	// 수정 항목 1
		requestBuilder.param("content", "NoticeController테스트2");	// 수정 항목 1
		requestBuilder.param("writer", writer);
		
		modelAndView 
					= mockMvc.
						perform(requestBuilder).
						andReturn().
						getModelAndView();
		
		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
	} // testRegister
	
	
	
//	@Disable
	@Test
	@Order(5)
	@DisplayName("테스트 5 : remove")
	@Timeout(value=2, unit=TimeUnit.SECONDS)
	void testRemove() throws Exception {		
		log.trace("testRemove() invoked.");
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/get");
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/notice/remove");
		requestBuilder.param("no", "32");
		
		ModelAndView modelAndView 
		= mockMvc.perform(requestBuilder).andReturn().getModelAndView();

		log.info("\t+ viewName: {}, type: {}", 
				modelAndView.getViewName(), modelAndView.getClass().getName());
		
	} // testRegister
	
	
	
	
}
