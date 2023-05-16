package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.NoticeDTO;

public interface NoticeMapper {
	
	/* 공지사항 게시글 목록 조회 */
	@Select("SELECT /*+ index_desc(NOTICE) */ * FROM notice")
	public abstract List<NoticeDTO> selectAll();
	
	
	//----------- XML 매핑 방식으로 생성 -----------//

	/* 공지사항 게시글 목록 조회(페이징 적용) */
	public abstract List<NoticeDTO> getListPaging(Criteria cri);
	
	/* 공지사항 게시글 상세 조회 */
	public abstract NoticeDTO select(Integer no);
	
	/* 공지사항 게시글 삭제 */
	public abstract Integer delete(Integer no);
	
	/* 공지사항 게시글 등록 */
	public abstract Integer insert(NoticeDTO dto);
	
	/* 공지사항 게시글 수정 */	
	public abstract Integer update(NoticeDTO dto);	
	
	/* 총 게시물 건수 반환 */
	@Select("SELECT count(no) FROM NOTICE WHERE no>0")
	public abstract Integer totalCount();
	
	/* 공지사항 게시글 총 갯수 */
	public abstract Integer getTotal();
	
	
} // end class