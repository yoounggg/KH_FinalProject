package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.QuestionDTO;

public interface QuestionMapper {

	public void write(QuestionDTO questionDTO); // 1:1문의
	
} // end interface
