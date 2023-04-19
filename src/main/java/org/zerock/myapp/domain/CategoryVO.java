package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class CategoryVO {
	
	/* 카테고리 등급 */
	private Integer tier;
	
	/* 카테고리 이름 */
	private String name;
	
	/* 카테고리 넘버 */
	private String code;
	
	/* 상위 카테고리 */
	private String parent;

} // end class