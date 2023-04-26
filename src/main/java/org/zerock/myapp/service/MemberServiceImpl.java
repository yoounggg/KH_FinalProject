package org.zerock.myapp.service;

import org.apache.ibatis.annotations.Param;
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
	
	@Setter(onMethod_=@Autowired)
	MsgSendService msgSendService;
	
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

	// 셍나 휴대폰 인증 - 아이디 존재하는지 찾기
	@Override
	public int idCheck(String name, String tel) {
	
		log.trace("셍나: 휴대폰 인증을 위한 idCheck({}, {})가 활성화 되었습니다.", name, tel);
		
		int cntIdCheck = memberMapper.idCheck(name, tel);
		
		return cntIdCheck;
		
	} // idCheck()
	
	// 셍나 휴대폰 인증 - 아이디 찾기 결과 반환
	@Override
	public String findIdResult(String name, String tel) {
		
		log.trace("셍나: 아이디 찾기 결과 반환을 위한 ({}, {})가 활성화 되었습니다.", name, tel);
		
		String idResult = memberMapper.findIdResult(name, tel);
		
		log.trace("findIdResult의 결과인 idResult의 값은: {}입니다.", idResult);
		
		return idResult;
		
	} // findIdResult()
	
//	------------------------------------------------------------
	
	// MemberDTO 객체 반환 하는 구현객체 생성 (찬돌)
	@Override
	public MemberDTO getMemberInfo(String memberId) throws ServiceException {
		
		return memberMapper.getMemberInfo(memberId);
		
	} // getMemberInfo
 
} // end class
