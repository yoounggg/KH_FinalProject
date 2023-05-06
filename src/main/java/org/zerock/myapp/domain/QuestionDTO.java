package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QuestionDTO {

	private Integer qno;
	private String title;
	private String content;
	private Timestamp regDate;
	private String type;
	private String id;
	
} // end class
