package org.zerock.myapp.domain;



import java.sql.Timestamp;

import lombok.Value;

@Value
public class NoticeVO {
	
	private Integer no;
	private String title;
	private String content;
	private String writer;
	private Timestamp reg_date;
	private Timestamp update_date;

} // end class
