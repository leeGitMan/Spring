package edu.kh.comm.member.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.comm.member.model.service.MyPageService;
import edu.kh.comm.member.model.vo.Member;


@RequestMapping("/member/myPage")
@SessionAttributes({"loginMember"})
@Controller
public class MyPageController {
	
	
	private Logger logger = LoggerFactory.getLogger(MyPageController.class);
	
	@Autowired
	private MyPageService service;
	
	
	// 프로필 눌렀을 때, 이동하는 경로
	@GetMapping("/profile")
	public String myPageProfile() {
		
		return "member/myPage-profile";
	}
	
	// 닉네임 눌렀을 때, 이동하는 경로
	@GetMapping("/info")
	public String myPageInfo() {
		
		return "member/myPage-info";
	}

	
	// 비밀번호 변경 페이지 이동
	@GetMapping("/changePw")
	public String changePw() {
		
		return "member/myPage-changePw";
	}
	
	
	// 회원 탈퇴 하기 페이지 이동
	@GetMapping("/secession")
	public String secession() {
		return "member/myPage-secession";
	}
	
	
	
	// 비밀번호 변경 하기
	@PostMapping("/changePw")
	public int changePassword(@RequestParam("currentPw") String pw,
								Member inputMember) {
		
		return service.changePw(pw);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
