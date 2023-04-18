package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.domain.MemberVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.MemberMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
@Service
public class MemberServiceImpl implements MemberService {
	
	// 혁규 회원가입
	@Setter(onMethod_=@Autowired)
	MemberMapper memberMapper;
	
	@Override
	public void memberSignup(MemberVO memberVO) throws Exception{

		log.trace("MemberVO : {} invoked", memberVO );
			
		memberMapper.memberSignup(memberVO);
			
		log.trace("MemberVO : {} invoked", memberVO );
	}
	
	// 세인 로그인	
	@Override
	public MemberVO memberLogin(LoginDTO loginDTO) throws ServiceException {
		
		return this.memberMapper.memberLogin(loginDTO);
		
	} // memberLogin()
	
	// MemberDTO 객체 반환 하는 구현객체 생성 (찬돌)
	@Override
	public MemberDTO getMemberInfo(String memberId) throws ServiceException {
		
		return memberMapper.getMemberInfo(memberId);
	} // getMemberInfo
 
} // end class
