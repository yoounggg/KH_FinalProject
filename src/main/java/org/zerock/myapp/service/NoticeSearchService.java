package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.exception.ServiceException;

public interface NoticeSearchService { // 인터페이스
	
	//1. 상품 리스트
	public abstract List<NoticeDTO> noticeSearchList(Criteria cri) throws ServiceException;
	
	//2. 상품 총 개수
	public abstract Integer totalNotice(Criteria cri) throws ServiceException;
	
	
} // end interface
