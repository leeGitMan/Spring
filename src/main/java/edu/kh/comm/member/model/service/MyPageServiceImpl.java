package edu.kh.comm.member.model.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.dao.MyPageDAO;
import edu.kh.comm.member.model.vo.Member;

@Service
public class MyPageServiceImpl implements MyPageService{
	
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired
	private MyPageDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	/**
	 * 비밀번호 변경 서비스
	 * @author lee
	 */
	@Override
	public int changePw(String currentPw, String newPw, Member loginMember) {
		
		int result = 0;
		// 비번 변경을 위해서 현재 DB의 비번을 가져오기
		String memberPw = dao.loginMemberNo(loginMember);
		
		if(bcrypt.matches(currentPw, memberPw)) { // DB에서 가져온 비번과 현재 입력한 비번 비교하기 (암호화로 인해 bcrypt matches로 비교)
			String newPassword = bcrypt.encode(newPw); // 암호가 맞으면 새로운 비번 암호화해서 변수에 담아주기
			loginMember.setMemberPw(newPassword); // 로그인 멤버 객체에 새로운 비번 세팅해주기
			result = dao.changePw(loginMember); // 새로운 비번을 로그인 멤버 객체에 담아서 보내주기
		}else {
			result = 0;
		}
		return result;
	}
	
	

	/** 
	 * 회원 탈퇴 서비스
	 * @author lee
	 */
	@Override
	public int secession(Member loginMember, String pw) {
		
		// 비번 변경과 마찬가지로 디비 비번과 입력 비번이 같아야지 탈퇴 진행
		// 디비 비번 가져오기
		
		int result;
		// 디비에서 가져온 비번 변수에 저장
		String memberPw = dao.loginMemberNo(loginMember);
		
		if(bcrypt.matches(pw, memberPw)) { // 디비 비번과 입력 비번 비교하기
			result = dao.secession(loginMember); // 맞으면 로그인멤버 DAO로 넘기기
		}else {
			result = 0;
		}
		
		
		
		return result;
	}



	@Override
	public int updateInfo(Member loginMember, Map<String, Object> paramMap) {
		
		// 맵안에 있는 값들을 로그인 멤버 안에 세팅해주기
		
		
		loginMember.setMemberNickname((String) paramMap.get("updateNickname"));
		loginMember.setMemberTel((String) paramMap.get("updateTel"));
		loginMember.setMemberAddress((String) paramMap.get("updateAddress"));
		
		return dao.updateInfo(loginMember);
		
		
	}

	
	
	
	


		
		
	
	

	

	

	

}
