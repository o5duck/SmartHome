package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import mvc.model.exception.deleteException.DeleteException;
import mvc.model.exception.updateException.UpdateException;
import mvc.model.vo.ContentVo;
import mvc.model.vo.DocumentVo;

public class DocumentDao implements DaoModel {
	//document 삽입시
	public int insert(Connection conn, DocumentVo doc) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into document (title, content, c_time, attached, gen_user_id, des_1, des_2, des_3, doc_type) values (?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, doc.getTitle());
			pstmt.setString(2, doc.getContent());
			pstmt.setTimestamp(3, toTimestamp(doc.getC_time()));
			pstmt.setString(4, doc.getAttached());
			pstmt.setString(5, doc.getGen_user());
			pstmt.setString(6,  doc.getDes1());
			pstmt.setString(7, doc.getDes2());
			pstmt.setString(8, doc.getDes3());
			pstmt.setString(9, doc.getDoc_type());
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from document");
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
			return -1;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	//document 영구삭제시
	public void delete(Connection conn, int docId) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("delete from document where doc_id = ?");
			pstmt.setInt(1, docId);
			int removedCount = pstmt.executeUpdate();
			if(removedCount == 0)
				throw new DeleteException();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	//document update시
	public void update(Connection conn, DocumentVo doc, int step, int docId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if(step == 1){
				pstmt = conn.prepareStatement(
						"update document set title=?, content=?, c_time=?, des_1=? where doc_id=?");
				pstmt.setInt(5, docId);
			}else if(step == 2){
				pstmt = conn.prepareStatement(
						"update document set title=?, content=?, c_time=?, des_2=? where doc_id=?");
				pstmt.setInt(5, docId);
			}else if(step == 3){
				pstmt = conn.prepareStatement(
						"update document set title=?, content=?, c_time=?, des_3=? where doc_id=?");
				pstmt.setInt(5, docId);
			}else if(step == 0){
				pstmt = conn.prepareStatement(
						"update document set title=?, content=?, c_time=?, des_1=?, des_2=?, des_3=? where doc_id=?");
				pstmt.setString(5, doc.getDes2());
				pstmt.setString(6, doc.getDes3());
				pstmt.setInt(7, docId);
			}
			pstmt.setString(1, doc.getTitle());
			pstmt.setString(2, doc.getContent());
			pstmt.setTimestamp(3, toTimestamp(doc.getC_time()));
			pstmt.setString(4, doc.getDes1());
			
			int updatedCnt = pstmt.executeUpdate();
			if(updatedCnt == 0)
				throw new UpdateException();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//id를 넘겨주면 documentVo로 변환해서 return
	public ContentVo selectById(Connection conn, int no, String userId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from document where doc_id = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ContentVo contentVo = null;
			if (rs.next()) {
				contentVo = convertContentVo(conn, rs, userId);
			}
			return contentVo;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//정보를 documentVo로 바꿔주는 함수
	public ContentVo convertContentVo(Connection conn, ResultSet rs, String userId) throws SQLException {
		String[] gen_user = {rs.getString("gen_user_id"), convertUserName(conn, rs.getString("gen_user_id"))};
		String[] des1 = {rs.getString("des_1"), convertUserName(conn, rs.getString("des_1"))};
		String[] des2 = {"",""};
		String[] des3 = {"",""};
		if(rs.getString("des_2") != null){
			des2[0] = rs.getString("des_2");
			des2[1] = convertUserName(conn, rs.getString("des_2"));
		}
		if(rs.getString("des_3") != null){
			des3[0] = rs.getString("des_3");
			des3[1] = convertUserName(conn, rs.getString("des_3"));
		}
		ContentVo contentVo = new ContentVo(
				rs.getString("title"),
				rs.getString("content"),
				toDate(rs.getTimestamp("c_time")),
				gen_user,
				des1,
				des2,
				des3,
				rs.getString("doc_type"),
				getDocState(conn, rs.getInt("doc_id"), userId),
				getDocListId(conn, rs.getInt("doc_id"), userId));
		contentVo.setAttached(rs.getString("attached"));
		contentVo.setDocId(rs.getInt("doc_id"));
		return contentVo;
	}
		
	//id를 user name으로 바꿔주는 함수
	public String convertUserName(Connection conn, String user_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select name from user where user_id = ?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			String name = null;
			if(rs.next()){
				name = rs.getString("name");
			}
			return name;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//personal document의 state를 출력해주는 함수
	private String getDocState(Connection conn, int docId, String userId)  throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select state from personal where doc_id= ? and user_id= ?");
			pstmt.setInt(1, docId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			String state = null;
			if(rs.next()){
				state = rs.getString("state");
			}
			return state;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private String getDocListId(Connection conn, int docId, String userId)  throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select list_id from personal where doc_id= ? and user_id= ?");
			pstmt.setInt(1, docId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			String listId = null;
			if(rs.next()){
				listId = rs.getString("list_id");
			}
			return listId;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	


	
}
