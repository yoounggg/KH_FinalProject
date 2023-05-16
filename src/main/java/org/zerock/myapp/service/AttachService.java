package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.AttachImageVO;
import org.zerock.myapp.exception.ServiceException;

public interface AttachService {

	/* [별이]이미지 데이터 반환 */
	public abstract List<AttachImageVO> getAttachList(Integer product_no) throws ServiceException;

} // end class
