package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.mapper.IdCheckMapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Service
public class IdCheckServiceImpl implements IdCheckService {

	@Autowired
	IdCheckMapper mapper;
	
	// 아이디 중복체크
	@Override
	public int idCheck(String memberId) {
		log.trace("id ({})invoked. 서비스 impl", memberId);
		
		int cnt = mapper.idCheck(memberId);
		
		log.trace("cnt : {} invoked ", cnt);
		
		return cnt;
	} // idCheck
	
	
} // end class
