package edu.kh.comm.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.comm.board.model.dao.BoardDAO;
import edu.kh.comm.board.model.vo.Board;
import edu.kh.comm.board.model.vo.BoardType;
import edu.kh.comm.board.model.vo.Pagination;




@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	//게시판 코드, 이름 조회
	@Override
	public List<BoardType> selectBoardType() {
		
		
		return dao.selectBoardType();
	}

	
	// 게시글 조회 목록 서비스 구현
	@Override
	public Map<String, Object> selectBoardList(int cp, int boardCode) {
		
		// 게시글 목록 조회 서비스 호출
		// 1) 게시판 이름 조회 -> 인터셉터로 application에 올려둔 boardTypeList 쓸 수 있음
		
		// 2) 페이지네이션 생성(ListCount)
		int listCount = dao.getListCount(boardCode);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		// 3) 게시글 목록 조회
		
		List<Board> boardList = dao.selectBoardList(pagination, boardCode);
		
		// map 만들어 담기
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
	}

}
