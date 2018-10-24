package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import mvc.model.vo.ContentVo;

public class PersonalDao implements DaoModel {
	
	//검색조건이 없는 개인 문서함 문서 갯수(결재, 개인, 그룹 선택 가능)
	public int selectCount(Connection conn, String userId, String doc_type, String tableId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if(tableId.equals("R_NEW") || tableId.equals("R_OLD") || tableId.equals("R_SEND"))
				pstmt = conn.prepareStatement(
						"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ?");
			else
				pstmt = conn.prepareStatement(
						"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = 'R_SEND' and state = ? and doc_type = ?");
			pstmt.setString(1, userId);
			pstmt.setString(2, tableId);
			pstmt.setString(3, doc_type);
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
	
	//검색조건이 있는 개인 문서함 문서 갯수(결재, 개인, 그룹 선택 가능)
	public int selectCount(Connection conn, String userId, String doc_type, String tableId, String searchCondition, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			searchWord = "%"+searchWord+"%";
			if(searchCondition.equals("title")){
				if(tableId.equals("R_NEW") || tableId.equals("R_OLD") || tableId.equals("R_SEND"))
					pstmt = conn.prepareStatement(
							"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? and title like ?");
				else
					pstmt = conn.prepareStatement(
							"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = 'R_SEND' and state = ? and doc_type = ? and title like ?");
			}else if(searchCondition.equals("content")){
				if(tableId.equals("R_NEW") || tableId.equals("R_OLD") || tableId.equals("R_SEND"))
					pstmt = conn.prepareStatement(
							"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? and content like ?");
				else
					pstmt = conn.prepareStatement(
							"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = 'R_SEND' and state = ? and doc_type = ? and content like ?");
			}else if(searchCondition.equals("writer")){
				pstmt = conn.prepareStatement(
						"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? and (gen_user_id like ? or gen_user_id in (select user_id from user where name like ?))");
				pstmt.setString(5, searchWord);
			}else if(searchCondition.equals("receiver")){
				if(tableId.equals("R_NEW") || tableId.equals("R_OLD") || tableId.equals("R_SEND"))
					pstmt = conn.prepareStatement(
							"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? and (des_1 in (select user_id from user where name like ?) or des_2 in (select user_id from user where name like ?) or des_3 in (select user_id from user where name like ?) or des_1 in (select board_id from board where board_id like?))");
				else
					pstmt = conn.prepareStatement(
							"select count(*) from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = 'R_SEND' and state = ? and doc_type = ? and (des_1 in (select user_id from user where name like ?) or des_2 in (select user_id from user where name like ?) or des_3 in (select user_id from user where name like ?) or des_1 in (select board_id from board where board_id like?))");
				pstmt.setString(5, searchWord);
				pstmt.setString(6, searchWord);
				pstmt.setString(7, searchWord);
			}
			pstmt.setString(1, userId);
			pstmt.setString(2, tableId);
			pstmt.setString(3, doc_type);
			pstmt.setString(4, searchWord);
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
	
	//검색조건이 없는 개인 문서함 각 페이지별 문서 추출(결재, 개인, 그룹 선택가능)
	public List<ContentVo> select(Connection conn, int startRow, int size, String userId, String tableId, String doc_type) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			DocumentDao docDao = new DocumentDao();
			if(tableId.equals("R_NEW") || tableId.equals("R_OLD") || tableId.equals("R_SEND"))
				pstmt = conn.prepareStatement("select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? order by document.doc_id desc limit ?, ?");
			else
				pstmt = conn.prepareStatement("select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = 'R_SEND' and state = ? and doc_type = ? order by document.doc_id desc limit ?, ?");
			pstmt.setString(1, userId);
			pstmt.setString(2, tableId);
			pstmt.setString(3, doc_type);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, size);
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
	
	//검색조건이 있는 개인 문서함 각 페이지별 문서 추출(결재, 개인, 그룹 선택가능)
	public List<ContentVo> select(Connection conn, int startRow, int size, String userId, String tableId, String doc_type, String searchCondition, String searchWord) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			searchWord = "%"+searchWord+"%";
			DocumentDao docDao = new DocumentDao();
			if(searchCondition.equals("title")){
				if(tableId.equals("R_NEW") || tableId.equals("R_OLD") || tableId.equals("R_SEND"))
					pstmt = conn.prepareStatement(
						"select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? and title like ? order by document.doc_id desc limit ?, ?");
				else
					pstmt = conn.prepareStatement(
							"select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = 'R_SEND' and state = ? and doc_type = ? and title like ? order by document.doc_id desc limit ?, ?");
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, size);
			}else if(searchCondition.equals("content")){
				if(tableId.equals("R_NEW") || tableId.equals("R_OLD") || tableId.equals("R_SEND"))
					pstmt = conn.prepareStatement(
						"select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? and content like ? order by document.doc_id desc limit ?, ?");
				else
					pstmt = conn.prepareStatement(
							"select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = 'R_SEND' and state = ? and doc_type = ? and content like ? order by document.doc_id desc limit ?, ?");
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, size);
			}else if(searchCondition.equals("writer")){
				pstmt = conn.prepareStatement(
						"select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? and (gen_user_id like ? or gen_user_id in (select user_id from user where name like ?)) order by document.doc_id desc limit ?, ?");
				pstmt.setString(5, searchWord);
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, size);
			}else if(searchCondition.equals("receiver")){
				if(tableId.equals("R_NEW") || tableId.equals("R_OLD") || tableId.equals("R_SEND"))
					pstmt = conn.prepareStatement(
						"select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = ? and doc_type = ? and (des_1 in (select user_id from user where name like ?) or des_2 in (select user_id from user where name like ?) or des_3 in (select user_id from user where name like ?) or des_1 in (select board_id from board where board_id like ?)) order by document.doc_id desc limit ?, ?");
				else
					pstmt = conn.prepareStatement(
							"select * from document, personal where document.doc_id = personal.doc_id and user_id = ? and list_id = 'R_SEND' and state = ? and doc_type = ? and (des_1 in (select user_id from user where name like ?) or des_2 in (select user_id from user where name like ?) or des_3 in (select user_id from user where name like ?) or des_1 in (select board_id from board where board_id like ?)) order by document.doc_id desc limit ?, ?");
				pstmt.setString(5, searchWord);
				pstmt.setString(6, searchWord);
				pstmt.setString(7, searchWord);
				pstmt.setInt(8, startRow);
				pstmt.setInt(9, size);
			}
			
			pstmt.setString(1, userId);
			pstmt.setString(2, tableId);
			pstmt.setString(3, doc_type);
			pstmt.setString(4, searchWord);
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
	
	//문서생성시 personal insert
	public int insert(Connection conn, int docId, String userId, String list_id, String state) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"insert into personal (doc_id, user_id, list_id, state) values (?,?,?,?)");
			pstmt.setInt(1, docId);
			pstmt.setString(2, userId);
			pstmt.setString(3, list_id);
			pstmt.setString(4, state);
			int insertedCount = pstmt.executeUpdate();
			if(insertedCount > 0)
				return 0;
			else return -1;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//personal document 보관 삭제시
	public int delete(Connection conn, int docId, String userId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"delete from personal where doc_id = ? and user_id = ?");
			pstmt.setInt(1, docId);
			pstmt.setString(2, userId);
			int removedCount = pstmt.executeUpdate();
			if(removedCount > 0)
				return 0;
			else return -1;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//board document 삭제시
	public void delete(Connection conn, String groupId, int docId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"delete from personal where doc_id = ? and user_id in (select user_id from user_group where group_id like ?)");
			pstmt.setInt(1, docId);
			pstmt.setString(2, groupId);
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//docId에 대한 모든 문서 삭제 원할시
	public int delete(Connection conn, int docId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"delete from personal where doc_id = ?");
			pstmt.setInt(1, docId);
			int removedCount = pstmt.executeUpdate();
			if(removedCount > 0)
				return 0;
			else return -1;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//personal list_id 변경시
	public void updateListId(Connection conn, String userId, int docId, String listId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"update personal set list_id=? where user_id=? and doc_id=?");
			pstmt.setString(1, listId);
			pstmt.setString(2, userId);
			pstmt.setInt(3, docId);
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//personal list_id 변경시(board수정)
	public void updateListId(Connection conn, String userId, String groupId, int docId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"update personal set list_id='R_NEW' where user_id in (select user_id from user_group where group_id=? and user_id!=?) and doc_id=?");
			pstmt.setString(1, groupId);
			pstmt.setString(2, userId);
			pstmt.setInt(3, docId);
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
		
	public String getState(Connection conn, int docId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select state from personal where doc_id=?");
			pstmt.setInt(1, docId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString("state");
			}
			return null;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//state를 변경해주는 함수
	public void updateState(Connection conn, String state, int docId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"update personal set state=? where doc_id=?");
			pstmt.setString(1, state);
			pstmt.setInt(2, docId);
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//timestamp를 date로 바꿔주는 함수
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	//발신 결재 문서 중 상태별 갯수 출력
	public int selectCntByState(Connection conn, String userId, String state) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
						"select count(*) from personal where user_id = ? and list_id='R_SEND' and state=?");
			
			pstmt.setString(1, userId);
			pstmt.setString(2, state);
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
	
	
}
