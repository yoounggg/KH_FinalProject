package org.zerock.myapp.domain;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Value
public class CartVO {
	
	private Integer no; // <- cartNumber 
	private String member_id;
	private Integer product_No;
	private Integer count;

}
