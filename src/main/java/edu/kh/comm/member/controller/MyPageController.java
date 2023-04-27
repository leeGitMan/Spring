package edu.kh.comm.member.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.comm.member.model.service.MyPageService;
import edu.kh.comm.member.model.vo.Member;


@RequestMapping("/member/myPage")
@Controller
@SessionAttributes({"loginMember"}) // session scope에 loginMember를 얻어옴
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
	public String changePassword(@RequestParam("currentPw") String currentPw,
								@RequestParam("newPw") String newPw,
								@ModelAttribute("loginMember") Member loginMember) {
		
		int result = service.changePw(currentPw, newPw, loginMember);
		
		if(result > 0) {
			return "redirect:/";
		}else {
			return "redirect:info";
		}
	}
	
	
	// 회원 탈퇴
	@PostMapping("/secession")
	public String secession(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam("secessionPw") String pw,
			RedirectAttributes ra, 
			SessionStatus status) {
		
		int result = service.secession(loginMember, pw);
		
		if(result > 0) {
			ra.addFlashAttribute("msge", "회원 탈퇴 완료");
			status.setComplete();
			return "redirect:/";
		}else {
			ra.addFlashAttribute("msge", "회원 탈퇴 실패");
			return "redirect:secession";
		}
	}
	
	
	
	
	
	
	
	
	// 회원정보 수정
	@PostMapping("/info")
	
	public String updateInf(@ModelAttribute("loginMember") Member loginMember, 
							@RequestParam Map<String, Object> paramMap, // 요청 시 전달된 파라미터를 구분하지 않고 모두 Map에 담아서 얻어옴
							String[] updateAddress,
							RedirectAttributes ra) {
		
		
		
		// 필요한 값
		// 닉네임
		// 전화번호 
		// 주소(String[] 로 얻어와 String.join()을 이용해서 문자열로 변경)
		// 회원번호(Session -> 로그인한 회원 정보를 통해서 얻어옴)
		// --> @ModelAttribute, @SessionAttributes 필요
		
		// @SessionAttributes의 역할 2가지
		// 1) Model에 세팅 데이터의 key값을 @SessionAttributes에 작성하면
		// 해당 key값과 같은 Model에 세팅된 데이터를 request ->  session scope로 이동
		
		
		// 2) 기존에 session scope로 이동시킨 값을 얻어오는 역할
		// @ModelAttribute("loginMember") Member loginMember 파라미터에 객체 담아주는 역할
		//					[session key]
		// --> @SessionAttributes를 통해 session scope에 값을 얻어와서 
		// 세션에 등록되어 있는 애를 Member loginMember 변수에 바로 넣어준다
		// 단, @SessionAttributes({"loginMember"})가 클래스 위에 작성되어있어야 가능하다
		
		// *** 매개변수를 이용해서 session, 파라미터 데이터를 동시에 얻어올 때 문제점 ***
		
		// session에서 객체를 얻어오기 위해서 매개변수에 작성한 상태
		// -> @ModelAttribute("loginMember") Member loginMember
		
		// 파라미터의 name 속성 값이 매개변수에 작성된 객체(loginMember)의 필드와 일치하면
		// name="memberNickname"
		// session에서 얻어온 객체의 필드에 덮어쓰기가 된다.
		
		// [해결 방법] 파라미터의 name속성을 변경해서 얻어오면 문제 해결
		// (필드명 겹쳐서 문제니깐 겹치지 않게 하자)
		
		
		return "redirect:info";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
