package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class QuestionController {
	
	@GetMapping("/help/question")
	public void question() {
		log.trace("question() invoked");
		
	} // question
	
	
} // end class
