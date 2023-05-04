package org.zerock.myapp.mapper;

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
import org.zerock.myapp.domain.QuestionDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-*.xml")

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionMapperTests {

	@Setter(onMethod_ = { @Autowired })
	private QuestionMapper questionMapper;

	@BeforeAll
	void beforeAll() {
		log.trace("BeforeAll() invokeld.");

	} // beforeAll

//	@Disable
	@Test
	@Order(1)
	@DisplayName("write")
	@Timeout(value = 2, unit = TimeUnit.SECONDS)
	public void write() {
		log.trace("write () invoked");

		// VO는 set을 못하기 때문에 DTO를 사용하고 mapper도 추가함
		// DTO 객체 생성
		QuestionDTO questionDTO = new QuestionDTO();

		questionDTO.setTitle("mapper test");
		questionDTO.setContent("mapper test");
		questionDTO.setType("mapper test");

		questionMapper.write(questionDTO);
	} // write

} // end class