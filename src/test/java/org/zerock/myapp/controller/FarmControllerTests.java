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

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
//		"file:src/main/webapp/spring/**/*.xml",
		"file:src/main/webapp/**/spring/**/root-*.xml",
		"file:src/main/webapp/**/spring/**/servlet-*.xml"
})

//Spring MVC 까지 작동(=> 결과적으로 spring core인, ** spring beans container **까지 생성함)시키는
//어노테이션으로, 표현계층의 컨트롤러 핸들러 메소드를 테스트할 때에는,
//반드시 넣어주어야 하는 어노테이션입니다. (***)
@WebAppConfiguration	

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FarmControllerTests {
	
//	다른 계층처럼, 아래와 같이 하면 안됩니다!! (***)
//	@Setter(onMethod_={@Autowired})
//	private BoardController controller;
	
	@Setter(onMethod_= {@Autowired})
//	이게 뭡니까? 바로 , spring beans container 의 구현객체임(***)
//	이걸필요로 한다. 이거없이 빈즈컨테이너가 구동되지않다고 하신건감
//	 
	private WebApplicationContext ctx;
	// 가상의 Spring MVC를 테스트하는 POSTMAN 과 비슷한 역할을 수행
	// 이 타입의 객체를 통해서, 실질적으로 컨트롤러의 핸들러 메소드 테스트를 수행합니다.
	// 이거사용법이 좀어려움!!
	private MockMvc mockMvc;  // 이..이게머노.... 서블릿으로 목mvc가 들어있음. 목...목업 모형 장난감
	//실체처럼 만든 모형 mvc를 목업한다.
	
	@BeforeAll
	void beforeAll() {	//1회성 전처리 수행
		log.trace("beforeAll() invoked.");
	
		// 의존성 주입(DI) 확인
		assertNotNull(this.ctx);
		log.info("\t+ this.ctx: {}", this.ctx);
		
	} // beforeAll()

	
//	목업 건설사
//	@Disable
	@Test
	@Order(1)
	@DisplayName("testList")
	@Timeout(value=60, unit=TimeUnit.SECONDS)
	void testList() throws Exception{
		log.trace("testList() invoked.");
	
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx); 
		//일단 ctx를 목업 어쩌꾸에 넣어주면 됨. 건설사 획득
		// MockMvc를 지어줄 "건설사Builder" 부터 획득
		MockMvc mockMvc = mockMvcBuilder.build();
		// 이게 브라우저 역할 하는거임
//		욫청을 만드는 핸들클래스가 따로 있음 이건 정적요청방식을 쓰고
		
		//BoardController의 /board/list, GET 핸들러 테스트
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/farm/list");
		requestBuilder.param("pageNum", "2");
		requestBuilder.param("amount", "10");
		
		// 상위타입에 넣음
		
		// 이제 가상의 MVC 환경에서, BoardController에 요청생성 및 전송
//		@Cleanup("clear")
		ModelAndView modelAndView = mockMvc.perform(requestBuilder).andReturn().getModelAndView();
		log.info("\t+ modelAndView : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());
		
		// 요청이 날라감. 겟방식의 요청유알아이로 요청을 보내는자! 건설하는 자를 넣어줌 저 유아이로 리퀘스트
		// 만들어 보냄
		
		// 테스트 대상 컨트롤러 핸들러(메소드)가 반환한 (1) 모델 (2) 뷰 이름 중,
		// 모델(ModelMap)을 얻었으니, 순회하여 그 안의 모든 모델속성(즉, 비지니스 데이터인 모델객체들)
		// 출력
//		log.info("\t+ modelMap: {}", modelMap);
		
		
	} // testList
	
	
	
//	@Disable
	@Test
	@Order(2)
	@DisplayName("testGet")
	@Timeout(value=60, unit=TimeUnit.SECONDS)
	void testGet() throws Exception{
		log.trace("testGet() invoked.");
	
		// MockMvc를 지어줄 "건설사Builder" 부터 획득
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx); 
		MockMvc mockMvc = mockMvcBuilder.build();
		
		//BoardController의 /board/list, GET 핸들러 테스트
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/farm/get");
		requestBuilder.param("no", "17"); // ... <- 가변인자
		
		// 컬렉션 아니어서 cleanup 안해도됨.
		// 이제 가상의 MVC 환경에서, BoardController에 요청생성 및 전송
		ModelAndView modelAndView = mockMvc.perform(requestBuilder).andReturn().getModelAndView();		
		log.info("\t+ modelAndView : {}, type : {}", modelAndView.getViewName(), modelAndView.getClass().getName());

	} // testList
	
	
	
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
		requestBuilder.param("name", "(주)혁뀨"); // ... <- 가변인자		
		requestBuilder.param("business_no", "789-78-78989"); // ... <- 가변인자
		requestBuilder.param("location", "서울특별시 강남구 테헤란로10길 12"); // ... <- 가변인자
		requestBuilder.param("tel", "010-1212-1313"); // ... <- 가변인자
		
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
		requestBuilder.param("no", "18"); // ... <- 가변인자
		
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