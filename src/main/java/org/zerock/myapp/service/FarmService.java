package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FarmDTO;
import org.zerock.myapp.domain.FarmVO;
import org.zerock.myapp.exception.ServiceException;

public interface FarmService {
	
	/* 농가 거래처 전체 조회 */
	public abstract List<FarmVO> getList() throws ServiceException;
	
	/* 농가 거래처 전체 조회(페이징) */
	public abstract List<FarmVO> getListPaging(Criteria cri) throws ServiceException;
		
	/* 농가 거래처 상세 조회 */
	public abstract FarmVO get(Integer no) throws ServiceException;
	
	/* 농가 거래처 삭제 */
	public abstract Boolean remove(Integer no) throws ServiceException;	
	
	/* 농가 거래처 등록 */
	public abstract Boolean register(FarmDTO dto) throws ServiceException;
	
	/* 농가 거래서 수정 */
	public abstract Boolean modify(FarmDTO dto) throws ServiceException;
	
	/* 게시글 총 갯수 */
	public abstract Integer getTotal() throws ServiceException;
	
} // end class