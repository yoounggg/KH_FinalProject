package org.zerock.myapp.domain;

import lombok.Data;
import oracle.sql.TIMESTAMP;

@Data
public class QuestionDTO {

	private Integer qno;
	private String title;
	private String content;
	private TIMESTAMP regDate;
	private String type;
	
} // end class
