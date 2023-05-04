package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.QuestionDTO;
import org.zerock.myapp.mapper.QuestionMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class QuestionServiceImpl implements QuestionService {

	@Setter(onMethod_ = @Autowired)
	private QuestionMapper questionMapper;
	
	@Override
	public void write(QuestionDTO questionDTO) {	// 1:1 문의 
		log.trace("QuestionVO {}: ", questionDTO);
		
		questionMapper.write(questionDTO);
	} // write

	
} // end class
