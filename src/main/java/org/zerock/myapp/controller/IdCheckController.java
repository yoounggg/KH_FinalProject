package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.service.IdCheckService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("/signup")
@Controller
public class IdCheckController {

	@Autowired
	private IdCheckService idCheckService;
	
	
	@PostMapping("/infoa")
	@ResponseBody
	public int idCheck(@RequestParam("id") String id) {
		log.trace("idCheck {} invoked. (중복확인)",id);
		
		int cnt= idCheckService.idCheck(id);
		
		log.trace("cnt : {} (중복확인 성공)" , cnt);
		
		return cnt;
	} // id check

	
} // end class
