package edu.kh.comm.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.comm.board.model.vo.BoardType;

public interface BoardService {

	/** 게시판 코드, 이름 조회
	 * @return boardTypeList
	 */
	List<BoardType> selectBoardType();

	/** 게시글 조회 목록 서비스
	 * @param cp
	 * @param boardCode
	 * @return
	 */
	Map<String, Object> selectBoardList(int cp, int boardCode);

}
