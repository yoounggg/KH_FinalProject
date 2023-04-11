package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.AttachImageVO;

public interface AttachMapper {
	
	/* 이미지 데이터 반환 */
	public List<AttachImageVO> getAttachList(int bookId);
	
	// test 주석입니당.
	// test 주석2

} // end class
