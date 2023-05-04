package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.QuestionDTO;
import org.zerock.myapp.mapper.QuestionMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2

@RequestMapping("/help")
@NoArgsConstructor
public class QuestionController {
	
	@Setter(onMethod_ = @Autowired)
	private QuestionMapper questionMapper;
	
	@GetMapping("/question")	// 1:1문의 메인페이지
	public void question() {
		log.trace("1:1메인페이지 question() invoked");
		
	} // question
	
	@GetMapping("/write")	// 1:1 문의 글 등록페이지
	public void writeGet() {
		log.trace("1:1 문의 글 등록 페이지 writeGet() invoked");
		
	} // writeGet
	
	@PostMapping("/write")	// 1:1 문의 글 등록
	public String writePost(QuestionDTO questionDTO) {
		log.trace("1:1 문의 글 등록 writePost() invoked");
		
		questionMapper.write(questionDTO);
		
		log.trace("questionDTO : {} " , questionDTO);
		
		return "redirect:/help/question";
	} // writePost
	
	
} // end class
