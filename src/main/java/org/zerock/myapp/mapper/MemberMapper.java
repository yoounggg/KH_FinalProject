package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MemberDTO;

public interface MemberMapper {

	// 회원 가입  혁규
	public void memberSignup(MemberDTO memberDTO);

//	---------------------------------------------------------------------------------------
	
	// [셍나] 로그인
	// MemberVO로 하면 MemberVO 어노테이션 @Value라서 객체 생성이 안댐!
	// DTO 따로 생성해서 @Data 값 주고 사용하기
	public MemberDTO memberLogin(MemberDTO memberDTO);
	
	// [셍나] 아이디 찾기 - 휴대폰 인증 (이름과 전화번호로 사용자 정보가 존재하는지 확인)
	public int idCheck(@Param("name") String name, @Param("tel") String tel);
	
	// [셍나] 아이디 찾기 - 휴대폰 인증 - 결과
	public String findIdResult(@Param("name") String name, @Param("tel") String tel);
	
	// [셍나] 아이디 찾기 - 이메일 인증 (이름과 이메일로 사용자 정보가 존재하는지 확인)
	public int idCheck_e(@Param("name") String name, @Param("email") String email);
	
	// [셍나] 아이디 찾기 - 이메일 인증 - 결과
	public String findIdResult_e(@Param("name") String name, @Param("email") String email);
	
	// [셍나] 비밀번호 변경 - 아이디 조회 (아이디 값 입력해서 존재하는 아이디인지 확인)
	public int idSearch(@Param("id") String id);
	
	// [셍나] 비밀번호 변경 - 아이디 값으로 핸드폰 번호 가져오기
	public MemberDTO changePW_p(@Param("id") String id);
	
	// [셍나] 비밀번호 변경 - 아이디 값으로 이메일 주소 가져오기
	public MemberDTO changePW_e(@Param("id") String id);
	
	// [셍나] 비밀번호 변경 - 임시 비밀번호 암호화 후 DB 저장(이메일)
	public void updatePw_p(@Param("id") String id, @Param("encryptedTempPw_p") String encryptedTempPw_p);
	
	// [셍나] 비밀번호 변경 - 임시 비밀번호 암호화 후 DB 저장(이메일)
	public void updatePw_e(@Param("id") String id, @Param("encryptedTempPw_e") String encryptedTempPw_e);
	
//	---------------------------------------------------------------------------------------
	
	// [찬돌] 주문자 주소 정보
	public MemberDTO getMemberInfo(String id); // memberId 가 테이블에 ID로 되어있고 String임
	
//	---------------------------------------------------------------------------------------
	
	// [별이] 회원 조회
	public abstract List<MemberDTO> selectAll();
	
	// [별이] 회원 상세 조회
	public abstract MemberDTO select(String id);
	
	// [별이] 회원 조회 페이징
	public abstract List<MemberDTO> selectAllPaging(Criteria cri);
	
	// [별이]
	public abstract Integer getTotal();
	
} // end class
