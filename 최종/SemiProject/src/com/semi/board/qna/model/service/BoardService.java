package com.semi.board.qna.model.service;

import java.sql.Connection;

import com.semi.board.qna.model.dao.BoardDao;
import com.semi.board.qna.model.vo.Board;
import com.semi.common.JDBCTemplate;

public class BoardService {

	public int insertQna(Board b) {
		
		//qna 작성
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().insertQna(conn,b);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

}
