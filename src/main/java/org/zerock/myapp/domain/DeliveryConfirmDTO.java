package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Data;

@Data
public class DeliveryConfirmDTO {

	private String name;
	private Integer order_no;
	private String member_id;
	private String order_date;
	
} // end class
