package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class AttachImageVO {
	
	/* uuid */
	private String uuid; // pk
	
	/* 경로 */
	private String uploadPath;
	
	/* 파일 이름 */
	private String fileName;
	
	/* 상품 번호 */
	private Integer product_no; // product 테이블의 pk인 no를 fk로 가져옴

	@Override
	public String toString() {
		return "AttachImageVO [uploadPath=" + uploadPath + ", uuid=" + uuid + ", fileName=" + fileName + ", product_no="
				+ product_no + "]";
	}
	
}
