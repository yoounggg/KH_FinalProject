package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@NoArgsConstructor
@RequestMapping("/help")
public class HelpController {
	
	@GetMapping("/terms")
	public void terms() throws Exception {
		log.trace("terms() invoked");
		
	} // terms

	@GetMapping("/privacy")
	public void privacy() throws Exception {
		log.trace("privacy() invoked");
		
	} // privacy
	
	
} // end class
