package org.zerock.myapp.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.MemberVO;
import org.zerock.myapp.exception.DAOException;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public MemberVO memberLogin(LoginDTO dto) throws DAOException {
		
		
		log.trace("memberLogin({}) invoked.", dto);
		
		@Cleanup
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try (sqlSession) {
			
			String namespace = "org.zerock.myapp.persistence.MemberDAO";
			String sqlId = "memberLogin";
			String mappedStmt = namespace + "." + sqlId;
			
			return sqlSession.<MemberVO>selectOne(mappedStmt, dto);
			
		} catch (Exception e) {
			
			throw new DAOException(e);
				
		} // try-catch
		
	} // memberLogin()

} // end class
