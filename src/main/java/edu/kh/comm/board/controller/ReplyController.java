package edu.kh.comm.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import edu.kh.comm.board.model.service.ReplyService;
import edu.kh.comm.board.model.vo.Reply;


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
	
	
	/* by cho */
	@GetMapping("/selectReplyList")
	public String selectReplyList(int boardNo) {
		List<Reply> rList = service.selectReplyList(boardNo); 
		return new Gson().toJson(rList);
	}

	
	// 댓글 등록
	@PostMapping("/insert")
	public int insertReply(Reply reply) {
		
		
		int result = service.insertReply(reply);
		
		return result;
	}
	
	// 댓글 수정
	@PostMapping("/update")
	public int updateReply(int replyNo, String replyContent) {
		
		int result = service.updateReply(replyNo, replyContent);
		
		return result;
	}
	
	
	
	// 댓글 삭제
	@GetMapping("/delete")
	public int deleteReply(int replyNo) {
		int result = service.deleteReply(replyNo);
		return result;
	}
	

}
