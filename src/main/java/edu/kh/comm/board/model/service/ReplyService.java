package edu.kh.comm.board.model.service;

import java.util.List;

import edu.kh.comm.board.model.vo.Reply;

public interface ReplyService {

	/** 댓글 목록 조회 서비스
	 * @param boardNo
	 * @return list
	 */
	List<Reply> selectReply(int boardNo);

	/** 댓글 등록 서비스
	 * @param replyContent
	 * @param memberNo
	 * @param boardNo
	 * @return result
	 */
	int insertReply(Reply reply);

	/** 댓글 수정 서비스
	 * @param replyNo
	 * @param replyContent
	 * @return
	 */
	int updateReply(int replyNo, String replyContent);

	/** 댓글 삭제 서비스
	 * @param replyNo
	 * @return
	 */
	int deleteReply(int replyNo);

	/**@author by cho
	 * @param boardNo
	 * @return rList
	 */
	List<Reply> selectReplyList(int boardNo);
	
}
