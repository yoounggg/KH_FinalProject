package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
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
	
	// [세인] 로그인	
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

	// [셍나] 휴대폰 인증 - 회원 정보 입력(이름, 전화번호)으로 아이디 존재하는지 찾기
	@Override
	public int idCheck(String name, String tel) {
	
		log.trace("셍나: 휴대폰 인증을 위한 idCheck({}, {})가 활성화 되었습니다.", name, tel);
		
		int cntIdCheck = memberMapper.idCheck(name, tel);
		
		return cntIdCheck;
		
	} // idCheck()
	
	
	// [셍나] 이메일 인증 - 회원 정보 입력(이름, 이메일)으로 아이디 존재하는지 찾기
	@Override
	public int idCheck_e(String name, String email) {
		
		log.trace("셍나: 이메일 인증을 위한 idCheck_e({}, {})가 활성화 되었습니다.", name, email);
		
		int cntIdCheck_e = memberMapper.idCheck_e(name, email);
		
		return cntIdCheck_e;
		
	} // idCheck_e()
	
	
	// [셍나] 비밀번호 변경 - 아이디 조회
	@Override
	public int idSearch(String id) {
		
		log.trace("셍나: 비밀번호 변경을 위한 아이디 조회 기능인 idSearch({})가 활성화 되었습니다.", id);
		
		int cntIdInq = memberMapper.idSearch(id);
		
		return cntIdInq;
		
	} // idSearch()
	
	
	// [셍나] 비밀번호 변경 - 아이디로 핸드폰 번호 가져오기
	@Override
	public MemberDTO getTelById(String id) {
		
		log.trace("Service 계층에서 아이디로 핸드폰 번호 가져오기! {}", memberMapper.changePW_p(id));
		
		return memberMapper.changePW_p(id);
		
	} // getTelById()
	
	
	// [셍나] 비밀번호 변경 - 아이디로 이메일 주소 가져오기
	@Override
	public MemberDTO getEamilById(String id) {
		
		log.trace("Service 계층에서 아이디로 이메일 주소 가져오기! {}", memberMapper.changePW_e(id));
		
		return memberMapper.changePW_e(id);
		
	} // getEamilById()
	
	
	// [셍나] 비밀번호 변경 - 임시 비밀번호 발급 이후 암호화 시켜서 DB에 저장하기 - 휴대폰
	@Override
	public void updatePw_p(String id, String encryptedTempPw_p) {
		
		memberMapper.updatePw_p(id, encryptedTempPw_p);

		log.info("이메일 - 임시 비밀번호 암호화 후 DB에 저장시키는 encodingPw_p() 활성화! id:{}, encryptedTempPw_p:{}", id, encryptedTempPw_p);
		
	} // encodingPw_p()
	
	
	// [셍나] 비밀번호 변경 - 임시 비밀번호 발급 이후 암호화 시켜서 DB에 저장하기 - 이메일
	@Override
	public void updatePw_e(String id, String encryptedTempPw_e) {
		
		memberMapper.updatePw_e(id, encryptedTempPw_e);

		log.info("이메일 - 임시 비밀번호 암호화 후 DB에 저장시키는 encodingPw_e() 활성화! id:{}, encryptedTempPw_e:{}", id, encryptedTempPw_e);
		
	} // encodingPw_e()
	
//	------------------------------------------------------------
	
	// MemberDTO 객체 반환 하는 구현객체 생성 (찬돌)
	@Override
	public MemberDTO getMemberInfo(String memberId) throws ServiceException {
		
		return memberMapper.getMemberInfo(memberId);
		
	} // getMemberInfo

//	------------------------------------------------------------
	
	// 별이 회원 목록 전체 조회
	@Override
	public List<MemberDTO> getList() throws ServiceException {
		log.trace("getList() invoked. - [관리자]회원 목록 전체 조회");
		
		try {
			return this.memberMapper.selectAll();
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try
		
	} // getList

	// 별이 회원 상세 조회
	@Override
	public MemberDTO get(String id) throws ServiceException {
		log.trace("get() invoked. - [관리자]회원 상세 조회");
		
		try {
			return this.memberMapper.select(id);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try
	
	} //get

	
	// 별이 회원 목록 전체 조회
	@Override
	public List<MemberDTO> getListPaging(Criteria cri) throws ServiceException {
		log.trace("getListPaging() invoked. - [관리자]회원 목록 전체 조회(페이징)");
		
		try {
			return this.memberMapper.selectAllPaging(cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try
	} //get
	
	// 별이 회원 목록 조회
	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getList() invoked. - [관리자]인원수 조회");
		
		try {
			return this.memberMapper.getTotal();			
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try
	
	} // getTotal

 
} // end class
