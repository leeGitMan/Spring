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

	

	/** DB 비번과 현재 입력한 비번을 비교하기위한 DAO
	 * @param loginMember
	 * @return
	 */
	public String loginMemberNo(Member loginMember) {
		
		
		return sqlSession.selectOne("myPageMapper.selectNo", loginMember);
	}



	/** 새로운 비번을 DB에 세팅해주는 DAO
	 * @param loginMember
	 * @return
	 */
	public int changePw(Member loginMember) {
		return sqlSession.update("myPageMapper.updatePw", loginMember);
	}



	/** 회원탈퇴 DAO
	 * @param loginMember
	 * @return
	 */
	public int secession(Member loginMember) {
		return sqlSession.update("myPageMapper.updateSecession", loginMember);
	}



	public int updateInfo(Member loginMember) {
		
		return sqlSession.update("myPageMapper.updateInfo", loginMember);
	}

}
