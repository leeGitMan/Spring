package edu.kh.comm.member.model.service;

import java.io.IOException;
import java.util.Map;

import edu.kh.comm.member.model.vo.Member;

public interface MyPageService {

	/** 회원 정보 수정 서비스
	 * @param paramMap
	 * @return result
	 */
	int updateInfo(Map<String, Object> paramMap);

	/** 비밀번호 변경 서비스
	 * @param paramMap
	 * @return result
	 */
	int changePw(Map<String, Object> paramMap);

	/** 회원 탈퇴 서비스
	 * @param loginMember
	 * @return result
	 */
	int secession(Member loginMember);

	/** 프로필 이미지 수정 서비스
	 * @param map
	 * @return result
	 */
	int updateProfile(Map<String, Object> map) throws IOException;

}
