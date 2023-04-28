package edu.kh.comm.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.member.model.vo.Member;

@Repository
public class MyPageDAO {
	
	@Autowired // root-context.xml 에서 생성된 SqlSessionTemplate bean을 의존성 주입(DI)
	private SqlSessionTemplate sqlSession;
	private Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	

	
	
	/** DB 비밀번호 조회 DAO
	 * @param memberNo 
	 * @return
	 */
	public String selectPw(int memberNo ) {
		return sqlSession.selectOne("myPageMapper.selectPw", memberNo);
	}
	
	
	/** 비밀번호 변경 DAO
	 * @param map
	 * @return
	 */
	public int changePw(Map<String, Object> map) {
		return sqlSession.update("myPageMapper.changePw", map);
	}

	
	/** 회원탈퇴 DAO
	 * @param loginMember
	 * @return
	 */
	public int secession(Member loginMember) {
		return sqlSession.update("myPageMapper.updateSecession", loginMember);
	}



	/** 회원 정보 수정 DAO
	 * @param paramMap
	 * @return 
	 */
	public int updateInfo(Map<String, Object> paramMap) {
		
		return sqlSession.update("myPageMapper.updateInfo", paramMap);
	}


 
	/** 프로필 이미지 수정
	 * @param map
	 * @return
	 */
	public int updateProfile(Map<String, Object> map) {
		return sqlSession.update("myPageMapper.updateProfile", map);
	}


	



	


	

}
