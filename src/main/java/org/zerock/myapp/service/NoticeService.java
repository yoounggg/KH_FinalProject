package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.exception.ServiceException;

public interface NoticeService {
	
	// 게시글 전체조회 (list) 
	public abstract List<NoticeDTO> getList() throws ServiceException;
	
	// 게시글 전체조회 (페이징)
	public abstract List<NoticeDTO> getListPaging(Criteria cri) throws ServiceException;
	
	// 게시글 상세조회
	public abstract NoticeDTO get(Integer no) throws ServiceException;
	
	// 게시글 등록
	public abstract Boolean register(NoticeDTO dto) throws ServiceException;
	
	// 게시글 삭제
	public abstract Boolean remove(Integer no) throws ServiceException;
	
	// 게시글 수정
	public abstract Boolean modify(NoticeDTO dto) throws ServiceException;

	// 게시글 총 갯수
	public abstract Integer getTotal() throws ServiceException;
	
} // end class