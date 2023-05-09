package edu.kh.comm.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.comm.board.model.service.ReplyService;


// REST (REprestational State Transfer) : 
// - 자원을 이름으로 구분(REprestational, 자원의 표현) 하여
// 자원의 상태(State)를 주고받는 것(Transfer)

// -> 특정한 이름(주소)으로 요청이 오면 값으로 응답

// @RestController : 요청에 따른 응답이 모두 데이터(값) 자체인 컨트롤러
// -> @Controller + @ReponseBody

@RequestMapping("/reply")
@RestController
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	// 댓글 목록 조회
	@GetMapping("/selectReplyList")
	public String selectReplyList() {
		
	}
	
	
	
	// 댓글 등록
	
	// 댓글 수정
	
	// 댓글 삭제

}
