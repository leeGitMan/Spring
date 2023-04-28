package edu.kh.comm.member.model.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.comm.common.Util;
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
	public int changePassword(Map<String, Object> map) {
		
		// DB상에 비번과 currentPw 가 맞는지 체크해야함
		// DB상 비번 불러오기
		String dbPw = dao.selectPw((int) map.get("memberNo"));
		
		
		// 불러온 db비번과 currentPw 입력값이 맞는지 비교
		if(bcrypt.matches((String) map.get("currentPw"), dbPw)) {
			// true 일 때, newPw를 세팅
			map.put("newPw", bcrypt.encode( (String)map.get("newPw")));
			
			
			
			return dao.changePw(map);
		}else {
			return 0;
		}
		
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
		String memberPw = dao.selectPw(loginMember.getMemberNo());
		
		if(bcrypt.matches(pw, memberPw)) { // 디비 비번과 입력 비번 비교하기
			result = dao.secession(loginMember); // 맞으면 로그인멤버 DAO로 넘기기
		}else {
			result = 0;
		}
		return result;
	}



	/**
	 * 회원 정보 수정 서비스
	 * @author lee
	 */
	@Override
	public int updateInfo(Map<String, Object> paramMap) {


		return dao.updateInfo(paramMap);
	}



	/**
	 * 프로필 이미지 수정 서비스
	 * @author lee
	 */
	@Override
	public int updateProfile(Map<String, Object> map) throws IOException {
								//webPath, folderPath, uploadImage ,delete, memberNo
		
		// 자주 쓰는 값 변수에 저장
		MultipartFile uploadImage = (MultipartFile) map.get("uploadImage");
		
		String delete = (String) map.get("delete"); // '0' 변경이 일어난거 '1'은 삭제
		// 프로필 이미지 삭제 여부를 확인해서
		// 삭제가 아닌 경우
		// 새 이미지로 변경 -> 업로드된 파일명을 변경
		
		// 삭제가 된 경우
		// DB에 NULL 값을 준비(DB update)
		
		// 변경될 파일명 저장 변수
		
		String renameImage = null; // 변경된 파일명 저장
		
		
		if(delete.equals("0")) { // 이미지가 변경된 경우
			
			// 파일명 변경
			// uploadImage.getOriginalFilename() : 원본 파일명
			
			renameImage = Util.fileRename(uploadImage.getOriginalFilename());
			// 2023042812314123.png
			map.put("profileImage", map.get("webPath") + renameImage);
			// 		/resources/images/memberProfile/2023042812314123.png
			
			
		}else { // 삭제된 경우
			map.put("profileImage", null);
		}
		
		// DAO 호출해서 프로필 이미지 수정
		int result = dao.updateProfile(map);

		// DB 수정 성공 시 메모리에 임시 저장되어 있는 파일을 서버에 저장
		
		if(result > 0 && map.get("profileImage") != null) {
			uploadImage.transferTo( new File(map.get("folderPath") + renameImage));
		}
		return result;
	}



	

	
	
	
	


		
		
	
	

	

	

	

}
