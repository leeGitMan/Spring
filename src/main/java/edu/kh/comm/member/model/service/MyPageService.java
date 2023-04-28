package edu.kh.comm.member.model.service;

import java.io.IOException;
import java.util.Map;

import edu.kh.comm.member.model.vo.Member;

public interface MyPageService {

	



	/** 회원 탈퇴 서비스
	 * @param loginMember
	 * @param pw
	 * @return
	 */
	int secession(Member loginMember, String pw);

	/** 회원 정보 변경 서비스
	 * @param paramMap
	 * @return
	 */
	int updateInfo(Map<String, Object> paramMap);

	/** 프로필 이미지 수정 서비스
	 * @param map
	 * @return
	 */
	int updateProfile(Map<String, Object> map) throws IOException;

	
	
	/** 비밀번호 변경 서비스
	 * @param map
	 * @return
	 */
	int changePassword(Map<String, Object> map);

}
