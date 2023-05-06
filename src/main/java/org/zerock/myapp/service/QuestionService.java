package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.QuestionDTO;

public interface QuestionService {

	
	public void write(QuestionDTO questionDTO);	// 1:1문의 글쓰기
	
	public List<QuestionDTO> getList(); // 1:1 문의 목록 가져오기
	
	public List<QuestionDTO> getListByParam(String memberId);	// 특정 로그인 ID 값 가져오기
	
} // end interface
