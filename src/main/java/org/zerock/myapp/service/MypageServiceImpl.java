package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.mapper.MypageMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@Service
public class MypageServiceImpl implements MypageService {

	@Setter(onMethod_= {@Autowired})
	private MypageMapper mypageMapper;
	
	// 매핑처리에 아이디 붙이려고 조회해서 가져오기
	@Override
	public MemberDTO getID(String id) {
		
		log.trace("findID({}) invoked.", id);
		
		return this.mypageMapper.getID(id);
		
	} // getID()
	
} // end class
