package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class SocialMemberDTO {

	private String account_email;
	private String profile_nickname;
	private String provider;
	private Integer address1;
	private String address2;
	private String address3;
	private String gender;
	private String bith_yy;
	private String bith_mm;
	private String bith_dd;
	private Integer adminCk;
	
} // end class
