package edu.kh.comm.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.member.model.vo.Member;

@Repository // 영속성을 가지는 DB/파일과 연결되는 클래스임을 명시 + bean 등록
public class MemberDAO {
	
	// DAO는 DB랑 연결하기 위한 Connection이 공통적으로 필요하다!
	// -> 필드에 선언
	// + Mybatis 영속성 프레임워크를 이용하려면 Connection을 이용해 만들어진 객체
	//	SqlSessionTemplate을 사용
	
	@Autowired // root-context.xml 에서 생성된 SqlSessionTemplate bean을 의존성 주입(DI)
	private SqlSessionTemplate sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	public Member login(Member inputMember) {
		
		// 1행 조회(파라미터 X) 방법
		//int count = sqlSession.selectOne("namespace값.id값");
		//int count = sqlSession.selectOne("memberMapper.test1");
		//logger.debug(count + "");
		
		
		// 1행 조회(파라미터 O) 방법
		//String memberNickname = sqlSession.selectOne("memberMapper.test2", inputMember.getMemberEmail() );
		//logger.debug(memberNickname);
		
		
		// 1행 조회(파라미터가 VO인 경우)
		//String memberTel = sqlSession.selectOne("memberMapper.test3", inputMember); 
																// memberEmail, memberPw
		//logger.debug(memberTel);
		
		
		
		// 1행 조회(파라미터가 VO, 반환되는 결과도 VO)
		Member loginMember = sqlSession.selectOne("memberMapper.login", inputMember);
		
		
		return loginMember;
	}

	/** 이메일 중복 검사 DAO
	 * @param memberEmail
	 * @return
	 */
	public int emailDupCheck(String memberEmail) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.emailDupCheck", memberEmail);
	}

	/** 닉네임 중복 검사 DAO
	 * @param memberNickname
	 * @return
	 */
	public int nickNameDup(String memberNickname) {
		return sqlSession.selectOne("memberMapper.memberNickDupCheck", memberNickname);
	}

	/** 회원 가입 DAO
	 * @param inputMember
	 * @return
	 */
	public int signUpFinal(Member inputMember) {
		
		// insert(), update(), delete() 메서드의 반환 값은 int 고정 
		// -> mapper메서드 resultType이 항상 "_int"로 고정
		// -> resultType -> 생략 가능
		// -> 묵시적으로 리턴 타입은 "_int"
		return sqlSession.update("memberMapper.signUp", inputMember);
	}

	/** 회원 조회(1행) DAO
	 * @param inputMember
	 * @return
	 */
	public Member selectOneMember(Member inputMember) {
		Member selectMember = sqlSession.selectOne("memberMapper.selectMember", inputMember);
		return selectMember;
	}

	/** 회원 목록 조회 DAO
	 * @return
	 */
	public List selectAll() {
		return sqlSession.selectList("memberMapper.selectAll");
	}

}
