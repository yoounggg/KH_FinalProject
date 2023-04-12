package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FaqDTO;
import org.zerock.myapp.domain.FaqVO;
import org.zerock.myapp.exception.ServiceException;

public interface FaqService {
	
	/* 게시글 전체 조회 */
	public abstract List<FaqVO> getList() throws ServiceException;
		
	/* 게시글 전체조회 (페이징) */
	public abstract List<FaqVO> getListPaging(Criteria cri) throws ServiceException;
	
	/* 게시글 상세 조회 */
	public abstract FaqVO get(Integer no) throws ServiceException;
	
	/* 게시글 등록 */
	public abstract Boolean register(FaqDTO dto) throws ServiceException;
	
	/* 게시글 삭제 */
	public abstract Boolean remove(Integer no) throws ServiceException;
	
	/* 게시글 수정 */
	public abstract Boolean modify(FaqDTO dto) throws ServiceException;

	/* 게시글 총 갯수 */
	public abstract Integer getTotal() throws ServiceException;

} // end interface