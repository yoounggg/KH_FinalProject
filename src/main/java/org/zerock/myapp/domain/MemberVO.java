package org.zerock.myapp.domain;

import lombok.Value;
import oracle.sql.TIMESTAMP;

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
	private Integer birth_year;
	private Integer birth_month;
	private Integer birth_day;
	private String social_type;
	private Integer adminCk;
	private Integer no;
	
} // end class