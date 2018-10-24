package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import mvc.model.vo.ContentVo;

public class BoardDao implements DaoModel {
	
	//게시판 문서생성시 board테이블 추가
	public int insert(Connection conn, int docId, String boardId, String boardseqId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"insert into board (doc_id, board_id, boardseq_id, answer_cnt) values (?,?,?,?)");
			pstmt.setInt(1, docId);
			pstmt.setString(2, boardId);
			pstmt.setString(3, boardseqId);
			pstmt.setInt(4, 0);
			int insertedCount = pstmt.executeUpdate();
			if(insertedCount > 0)
				return 0;
			else return -1;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//게시판 문서 삭제시
	public boolean delete(Connection conn, int docId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isRemoved = false;
		try{
			if(getAnswerCnt(conn, docId) > 0){
				pstmt = conn.prepareStatement(
						"update document set title='삭제된 글입니다.', content='', attached='', gen_user_id='', des_1='' where doc_id = ?");
			}else{
				pstmt = conn.prepareStatement(
						"delete from board where doc_id = ?");
				isRemoved = true;
			}
			pstmt.setInt(1, docId);
			pstmt.executeUpdate();
			return isRemoved;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//답글 달린 수를 알려주는 함수
	public int getAnswerCnt(Connection conn, int docId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select answer_cnt from board where doc_id = ?");
			pstmt.setInt(1, docId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
			return 0;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//답글 업데이트 해주는 함수
	public void setAnswerCnt(Connection conn, int docId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"update board set answer_cnt = answer_cnt+1 where doc_id = ?");
			pstmt.setInt(1, docId);
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//검색조건이 없는 게시판 문서 갯수(각 그룹별 선택 가능)
	public int selectCount(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select count(*) from document, board where document.doc_id = board.doc_id and board_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//검색조건이 있는 게시판 문서 갯수(각 그룹별 선택 가능)
	public int selectCount(Connection conn, String id, String searchCondition, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			searchWord = "%"+searchWord+"%";
			if(searchCondition.equals("title")){
				pstmt = conn.prepareStatement(
						"select count(*) from document, board where document.doc_id = board.doc_id and board_id = ? and title like ?");
			}else if(searchCondition.equals("content")){
				pstmt = conn.prepareStatement(
						"select count(*) from document, board where document.doc_id = board.doc_id and board_id = ? and content like ?");
			}else if(searchCondition.equals("writer")){
				pstmt = conn.prepareStatement(
						"select count(*) from document, board where document.doc_id = board.doc_id and board_id = ? and (gen_user_id like ? or gen_user_id in (select user_id from user where name like ?))");
				pstmt.setString(3, searchWord);
			}
			pstmt.setString(1, id);
			pstmt.setString(2, searchWord);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//검색조건이 없는 게시판 각 페이지별 문서 추출(각 그룹별 선택 가능)
	public List<ContentVo> select(Connection conn, int startRow, int size, String userId, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			DocumentDao docDao = new DocumentDao();
			pstmt = conn.prepareStatement("select * from document, board where document.doc_id = board.doc_id and board_id = ? order by boardseq_id desc limit ?, ?");
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<ContentVo> result = new ArrayList<>();
			while (rs.next()) {
				result.add(docDao.convertContentVo(conn, rs, userId));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//검색 조건이 있는 게시판 각 페이지별 문서 추출(각 그룹별 선택 가능)
	public List<ContentVo> select(Connection conn, int startRow, int size, String userId, String id, String searchCondition, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			searchWord = "%"+searchWord+"%";
			DocumentDao docDao = new DocumentDao();
			if(searchCondition.equals("title")){
				pstmt = conn.prepareStatement(
						"select * from document, board where document.doc_id = board.doc_id and board_id = ? and title like ? order by boardseq_id desc limit ?, ?");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, size);
			}else if(searchCondition.equals("content")){
				pstmt = conn.prepareStatement(
						"select * from document, board where document.doc_id = board.doc_id and board_id = ? and content like ? order by boardseq_id desc limit ?, ?");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, size);
			}else if(searchCondition.equals("writer")){
				pstmt = conn.prepareStatement(
						"select * from document, board where document.doc_id = board.doc_id and board_id = ? and (gen_user_id like ? or gen_user_id in (select user_id from user where name like ?)) order by boardseq_id desc limit ?, ?");
				pstmt.setString(3, searchWord);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, size);
			}

			pstmt.setString(1, id);
			pstmt.setString(2, searchWord);
			rs = pstmt.executeQuery();
			List<ContentVo> result = new ArrayList<>();
			while (rs.next()) {
				System.out.println("수행횟수1");
				result.add(docDao.convertContentVo(conn, rs, userId));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public String getBoardSeqNum(Connection conn, int docId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select boardseq_id from board where doc_id=?");
			pstmt.setInt(1, docId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
