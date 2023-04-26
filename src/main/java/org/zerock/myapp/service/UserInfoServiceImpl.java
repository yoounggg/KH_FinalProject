package org.zerock.myapp.service;

import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.UserInfoMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService, InitializingBean  {
	

	@Setter(onMethod_= {@Autowired})
	private UserInfoMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		  log.trace("afterPropertiesSet() invoked");
	      //1회성 전처리 -> 위에서 빈 (dao)을 잘 주입받았는지 체크해보기
	      try {
	         Objects.requireNonNull(this.mapper);
	         log.info("\t+this.dao:{}", this.mapper);
	      }catch(Exception e) {
	         throw new ServiceException(e);
	      } // try-catch
	} // afterPropertiesSet

	
	@Override // 조회
	public MemberDTO userDetail(String id) throws ServiceException {
		log.trace("userDetail({}) invoked.", id);
		
		try {
			return this.mapper.select(id);
		}catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // userDetail

	
	@Override // 수정 (비밀번호 제외)
	public Boolean updateUser(MemberDTO dto) throws ServiceException {
		log.trace("updateUser({}) invoked.", dto);
		
		try {
			return this.mapper.update(dto) == 1;
		}catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // updateUser
	
	
	@Override // 회원탈퇴
	public Boolean deleteUser(String id) throws ServiceException {
		log.trace("delete({}) invoked.", id);
		
		try {
			return this.mapper.delete(id) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // delete

//========================================================================
	
	@Override // 비밀번호 수정
	public Boolean modifyPw(MemberDTO dto) throws ServiceException {
		log.trace("modifyPw({}) invoked.", dto);
		
		try {
			return this.mapper.updatePw(dto) == 1;
		}catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch

		
	} // modifyPw

} // end class
