package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import oracle.sql.TIMESTAMP;

@Value
public class QuestionVO {
	
	private Integer qno;
	private String title;
	private String content;
	private Timestamp regDate;
	private String type;
	private String id;
	
} // end class
