package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.AdminDTO;
import org.zerock.myapp.domain.FaqVO;
import org.zerock.myapp.domain.NoticeVO;
import org.zerock.myapp.domain.ProductDTO;

public interface AdminMapper {

	/* 관리자 로그인 */
	public abstract AdminDTO login(AdminDTO dto);
	
	/* 방문자 수 */
	

	/* 총 갯수 */
	public abstract Integer getTotal();
	
} // end class
