package edu.kh.comm.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.comm.board.model.dao.ReplyDAO;
import edu.kh.comm.board.model.vo.Reply;
import edu.kh.comm.common.Util;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDAO dao;
	
	
	/** 게시판 코드, 이름 조회
	 *  by cho 
	 */
	@Override
	public List<Reply> selectReplyList(int boardNo) {
		return dao.selectReplyList(boardNo);
	}
	

	/** 댓글 등록 서비스
	 *
	 */
	@Override
	public int insertReply(Reply reply) {
		
		// XSS, 개행문자 처리
		reply.setReplyContent(Util.XSSHandling(reply.getReplyContent()));
		reply.setReplyContent(Util.newLineHandling(reply.getReplyContent()));
		
		return dao.insertReply(reply);

	}

	/** 댓글 수정 서비스
	 *
	 */
	@Override
	public int updateReply(int replyNo, String replyContent) {
		
		Reply reply = new Reply();
		
		reply.setReplyNo(replyNo);
		reply.setReplyContent(replyContent);
		
		return dao.updateReply(reply);
	}

	/** 댓글 삭제 서비스
	 *
	 */
	@Override
	public int deleteReply(int replyNo) {
		return dao.deleteReply(replyNo);
	}

}
