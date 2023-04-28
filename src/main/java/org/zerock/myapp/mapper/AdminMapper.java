package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.AdminDTO;

public interface AdminMapper {

	/* 관리자 로그인 */
	public abstract AdminDTO login(AdminDTO dto);
	
} // end class
