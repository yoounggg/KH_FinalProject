package org.zerock.myapp.domain;

import lombok.Data;
import oracle.sql.TIMESTAMP;

@Data
//@Value
public class MemberVO {


	//고쳐용
	// 채영 웅ㅇ니꺼 VO
	private String id;
	private String password;
	private String name;
	private String email;
	private String tel;
	private Integer address1;
	private String address2;
	private String address3;
	private String gender;
	private TIMESTAMP signup_date;
	private String birth_date;
	private Integer adminCk;
	
//	// 혁규
//	private String memberId;
//	private String memberPw;
//	private String memberName;
//	private String memberMail;
//	private String memberHp;
//	private Integer memberAddr1;
//	private String memberAddr2;
//	private String memberAddr3;
//	private String gender;
//	private TIMESTAMP joinDate;
//	private String birthDate;
//	private Integer adminCk;
	
} // end class