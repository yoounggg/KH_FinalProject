package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class SocialMemberVO {
	
	private String id;
	private String profile;
	private String email;
	private Integer address1;
	private String address2;
	private String address3;
	private String gender;
	private String bith_year;
	private String bith_month;
	private String bith_day;
	
} // end class
