package org.zerock.myapp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ServiceException;

public interface MemberService {
	
	// 회원가입 혁규
	public void memberSignup(MemberDTO memberDTO) throws Exception;
	
//	---------------------------------------------------------------------------------------
	
	// [셍나] 로그인
    public MemberDTO memberLogin(MemberDTO memberDTO) throws ServiceException;
    
    // [셍나] 아이디 찾기 (이름, 전화번호로 회원 정보 존재 확인) - 휴대폰 인증
    public int idCheck(@Param("name") String name, @Param("tel") String tel) throws ServiceException;
    
    // [셍나] 아이디 찾기 (이름, 이메일로 회원 정보 존재 확인) - 이메일 인증
    public int idCheck_e(@Param("name") String name, @Param("email") String email) throws ServiceException;
    
    // [셍나] 비밀번호 변경 - 아이디 조회
    public int idSearch(@Param("id") String id) throws ServiceException;
    
    // [셍나] 비밀번호 변경 - 아이디 값으로 핸드폰 번호 가져오기
    public MemberDTO getTelById(String id) throws ServiceException;
    
    // [셍나] 비밀번호 변경 - 아이디 값으로 이메일 주소 가져오기
    public MemberDTO getEamilById(String id) throws ServiceException;
    
    // [셍나] 비밀번호 암호화해서 DB에 저장하기 - 휴대폰으로 전송
    public void updatePw_p(String id, String encryptedTempPw_p);
    
    // [셍나] 비밀번호 암호화해서 DB에 저장하기 - 이메일로 전송
    public void updatePw_e(String id, String encryptedTempPw_e);
    
//	---------------------------------------------------------------------------------------
    
    // 주문자 정보 (찬돌)
    public MemberDTO getMemberInfo(String memberId) throws ServiceException;
    
//	---------------------------------------------------------------------------------------
    
    // [별이] 회원 목록 전체 조회
    public abstract List<MemberDTO> getList() throws ServiceException;
    
    // [별이] 회원 목록 전체 조회(페이징)
    public abstract List<MemberDTO> getListPaging(Criteria cri) throws ServiceException;
    
    // [별이] 회원 상세 조회
    public abstract MemberDTO get(String id) throws ServiceException;
  
    // [별이] 회원 총 인원수
    public abstract Integer getTotal() throws ServiceException;
    
} // end class
