package org.zerock.myapp.domain;

import lombok.Value;
import oracle.sql.TIMESTAMP;

@Value
public class QuestionVO {
	
	private Integer qno;
	private String title;
	private String content;
	private TIMESTAMP regDate;
	private String type;
	
} // end class
