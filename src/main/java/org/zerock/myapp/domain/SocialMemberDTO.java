package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class SocialMemberDTO {

	private String id;
	private String profile_nickname;
	private String email;
	private String nickname;
	private String properties;

	private Integer address1;
	private String address2;
	private String address3;
	private String gender;
	private String birth_year;
	private String birth_month;
	private String birth_day;
	
} // end class
