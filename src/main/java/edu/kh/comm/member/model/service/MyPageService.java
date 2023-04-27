package edu.kh.comm.member.model.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import edu.kh.comm.member.model.vo.Member;

public interface MyPageService {

	

	/** 비밀번호 변경 서비스
	 * @param currentPw
	 * @param newPw
	 * @param loginMember
	 * @return
	 */
	int changePw(String currentPw, String newPw, Member loginMember);

	/** 회원 탈퇴 서비스
	 * @param loginMember
	 * @param pw
	 * @return
	 */
	int secession(Member loginMember, String pw);

	int updateInfo(Member loginMember, Map<String, Object> paramMap);

}
