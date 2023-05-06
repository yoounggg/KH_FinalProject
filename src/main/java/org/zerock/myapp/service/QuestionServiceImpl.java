package org.zerock.myapp.service;

import java.util.List;

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
	public List<QuestionDTO> getList() {
		log.trace("getList() invoked");
		
		return questionMapper.getList();
		
	} // getList

	
	@Override
	public void write(QuestionDTO questionDTO) {	// 1:1 문의 글작성
		log.trace("QuestionVO {}: ", questionDTO);
		
		questionMapper.write(questionDTO);
	} // write


	@Override
	public List<QuestionDTO> getListByParam(String memberId) {	// 1:1 문의 특정 아이디 값 가져오기
		log.trace("getListByParam() invoked");

		return questionMapper.getListByParam(memberId);
		
	} // getListByParam


	@Override
	public QuestionDTO getPage(Integer qno) {	// 1:1문의 글 조회
		log.info("getPage() invoked");
		
		return questionMapper.getPage(qno);
		
	}  //getPage

	
} // end class
