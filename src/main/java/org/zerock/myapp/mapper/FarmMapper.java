package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FarmDTO;
import org.zerock.myapp.domain.FarmVO;

public interface FarmMapper {

	/* 농가 업체 전체 조회 */
	@Select("SELECT /*+ index_desc(FARM) */ * FROM FARM")
	public abstract List<FarmVO> selectAll();
	
	
	//--------------- XML 매핑 방식으로 생성 ----------------//
	
	/* 농가 업체 목록 조회(페이징 적용) */
	public abstract List<FarmVO> getListPaging(Criteria cri);
	
	/* 농가 업체 상세 조회 */
	public abstract FarmVO select(Integer no);
	
	/* 농가 업체 삭제 */
	public abstract Integer delete(Integer no);
	
	/* 농가 업체 등록 */
	public abstract Integer insert(FarmDTO dto);
	
	/* 농가 업체 수정 */
	public abstract Integer update(FarmDTO dto);
	
	/* 농가 업체 총 갯수 */
	public abstract Integer getTotal();
	
	/* 총 게시물 건수 반환 */
	@Select("SELECT count(no) FROM FARM WHERE no>0")
	public abstract Integer totalCount();

	
}