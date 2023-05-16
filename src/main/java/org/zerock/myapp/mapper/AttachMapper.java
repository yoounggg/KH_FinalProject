package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.AttachImageVO;

public interface AttachMapper {
	
	/* [별이]이미지 데이터 반환 */
	public List<AttachImageVO> getAttachList(Integer product_no);


} // end class
