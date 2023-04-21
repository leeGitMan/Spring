package edu.kh.comm.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.comm.member.model.vo.Member;

// POJO 기반 프레임워크: 외부 라이브러리 상속 x

// class : 객체를 만들기 위한 설계도
// -> 객체로 생성 되어야지 기능 수행 가능하다.
// --> IOC(제어의 역전, 객체 생명주기를 스프링이 관리)를 이용해서 객체 생성
// ** 이 때, 스프링이 생성한 객체를 bean이라고 한다.


// bean 등록 == 스프링이 객체로 만들어서 가지고 있어라



// @Component // 해당 클래스를 bean으로 등록하라고 프로그램에게 알려주는 주석(Annotation)

@Controller // 생성된 bean이 Controller임을 명시해주는 역할하면서 bean 등록
@RequestMapping("/member") // localhost:8080/comm/member 이하의 요청을 처리하는 컨트롤러

public class MemberController {
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// Controller : 요청/응답을 제어하는 역하를 하는 클래스
	
	/* @RequestMapping : 클라이언트의 요청(url)에 맞는 클래스 or 메서드를 연결 시켜주는 어노테이션
	 * 
	 * [ 위치에 따른 해석 ]
	 * - 클래스 레벨 : 공통 주소
	 * - 메서드 레벨 : 공통 주소 외 나머지 주소
	 * 
	 * 단, 클래스 레벨 @RequestMapping이 존재하지 않는다면
	 * - 메서드 레벨 : 단독 요청 처리 주소
	 * 
	 * [ 작성법에 따른 해석 ]
	 * 
	 * 1) @RequestMapping("url")
	 * --> 요청 방식(GET/POST) 관계 없이 url만 맞으면 처리함
	 * 
	 * 2) @RequestMapping(value = "url", method=RequestMethod.GET | POST)
	 * --> 요청 방식에 따라 요청 처리함
	 * 
	 * ** 메서드 레벨에서 GET/POST 방식을 구분하여 매핑할 경우 **
	 * @GetMapping("url") / @PostMapping("url") 사용하는 것이 일반적
	 * (메서드 레벨에서만 가능)
	 * 
	 * 
	 * */
	
	// 로그인
	// 요청 시 파라미터를 얻어오는 방법 1
	// -> HttpServletRequest를 이용
	
	/*	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		
		logger.info("로그인 요청됨");
		
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		logger.debug("inputEmail : "  + inputEmail);
		logger.debug("inputPw : "  + inputPw);
		
		return "redirect:/"; //sendRedirect 안써도 된다. 이게 리다이렉트 구문임.
	} */
	
	// 요청 시 파라미터를 얻어오는 방법 2
	// -> @RequestParam 어노테이션 사용
	
	// @RequestParam("name 속성값") 자료형 변수명
	// - 클라이언트 요청 시 같이 전달된 파라미터를 변수에 저장
	// -> 어떤 파라미터를 변수에 저장할지는 "name 속성값"을 이용해 지정
	
	// 매개변수 지정 시 데이터 타입 파싱을 자유롭게 진행할 수 있음 ex) String -> int로 변환
	
	// [속성]
	// value: input 태그의 name 속성 값
	
	// required: 입력된 name 속성 값이 필수적으로 파라미터에 포함되어야 하는지 지정-> 기본값은 true
	
	// defaultValue : required가 false인 상태에서 parameter가 존재하지 않을 경우 값을 지정해줌
	
	
	/* @RequestMapping("/login")
	public String login( @RequestParam("inputEmail") String inputEmail,
						@RequestParam("inputPw") String inputPw,
						@RequestParam(value="inputName", required=false, defaultValue="홍길동") String inputName
					) {
						
		
		logger.debug(inputEmail);
		logger.debug(inputPw);
		logger.debug(inputName);
		
		// email 숫자만 입력받는다고 가정
		// logger.debug(inputEmail + 100 + "");
		
		return "redirect:/";
	} */
	
	
	// 요청 시 파라미터를 얻어오는 방법 3
	// -> @ModelAttribute 어노테이션을 사용
	
	// @ModelAttribute VO타입 변수명
	// -> 파라미터 중 name 속성 값이 VO의 필드와 일치하면
	// 해당 VO 객체의 필드에 값을 세팅
	
	// *** @ModelAttribute를 이용해서 객체에 값을 직접 담는 경우에 대한 주의사항 ***
	// 반드시 필요한 내용
	// - VO 기본 생성자
	// - VO 필드에 대한 Setter
	
	
	@PostMapping("/login")
	public String login( @ModelAttribute Member memberEmail) {
		
		logger.info("로그인 기능 수행됨");
	
		return "redirect:/";
	}
	
	// 회원 가입 화면 전환
	@GetMapping("/signUp") // Get 방식 : /comm/member/signUp 요청  
	public String signUp() {
		
		
		
		return "member/signUp";
	}
	
	
	
	
	
	
	
	
	
	
}
