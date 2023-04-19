package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.MemberMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
@Service
public class MemberServiceImpl implements MemberService {
	
	@Setter(onMethod_=@Autowired)
	MemberMapper memberMapper;
	
	// 혁규 회원가입
	@Override
	public void memberSignup(MemberDTO memberDTO) throws Exception{

		log.trace("MemberDTO : {} invoked", memberDTO );
			
		memberMapper.memberSignup(memberDTO);
			
		log.trace("MemberDTO : {} invoked", memberDTO );
		
	} // memberSignup()
	
//	------------------------------------------------------------
	
	// 세인 로그인	
	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) throws ServiceException {
		log.trace("memberLogin({}) invoked.", memberDTO);
		
		try {
			return this.memberMapper.memberLogin(memberDTO);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch	
		
	} // memberLogin()

//	------------------------------------------------------------
	
	// MemberDTO 객체 반환 하는 구현객체 생성 (찬돌)
	@Override
	public MemberDTO getMemberInfo(String memberId) throws ServiceException {
		
		return memberMapper.getMemberInfo(memberId);
	} // getMemberInfo
 
} // end class
