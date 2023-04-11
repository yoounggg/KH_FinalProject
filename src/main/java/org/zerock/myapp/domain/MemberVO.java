package org.zerock.myapp.domain;

import lombok.Value;
import oracle.sql.TIMESTAMP;

//@Data
@Value
public class MemberVO {

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
	
<<<<<<< HEAD
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
	
=======
>>>>>>> 77d9f86eb9332fa8eaf9a29eacb12dd85bcef89f
} // end class