package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import jdbc.JdbcUtil;
import mvc.model.vo.ApprovalDesVo;


public class ApprovalDesDao implements DaoModel {
	public int insert(Connection conn, int docId, String nowDes, String nextDes, int step) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into approval_des (doc_id, now_des, next_des, step) values (?,?,?,?)");
			pstmt.setInt(1, docId);
			pstmt.setString(2, nowDes);
			pstmt.setString(3, nextDes);
			pstmt.setInt(4, step);
			int insertedCount = pstmt.executeUpdate();
			return insertedCount;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int delete(Connection conn, int docId) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("delete from approval_des where doc_id=?");
			pstmt.setInt(1, docId);
			int removedCount = pstmt.executeUpdate();
			return removedCount;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int update(Connection conn, int docId, String nowDes, String nextDes, int step) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("update approval_des set now_des=?, next_des=?, step=? where doc_id=?");
			pstmt.setString(1, nowDes);
			pstmt.setString(2, nextDes);
			pstmt.setInt(3, step);
			pstmt.setInt(4, docId);
			int updatedCount = pstmt.executeUpdate();
			return updatedCount;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	public ApprovalDesVo convertApprovalDesVo(Connection conn, int docId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DocumentDao docDao = new DocumentDao();
		try {
			pstmt = conn.prepareStatement("select * from approval_des where doc_id = ?");
			pstmt.setInt(1, docId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String[] nowDes = {rs.getString("now_des"), docDao.convertUserName(conn, rs.getString("now_des"))};
				String[] nextDes = {rs.getString("next_des"), docDao.convertUserName(conn, rs.getString("next_des"))};
				if(rs.getString("next_des") != null && rs.getString("next_des").equals("")){
					nextDes[0] = null;
				}
				return new ApprovalDesVo(
						rs.getInt("doc_id"),
						nowDes,
						nextDes,
						rs.getInt("step"));
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public HashMap<String, String> getApprovalDes(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			DocumentDao docDao = new DocumentDao();
			pstmt = conn.prepareStatement("select doc_id, now_des from approval_des");
			rs = pstmt.executeQuery();
			HashMap<String, String> mapNowDes = new HashMap<>();
			while (rs.next()) {
				mapNowDes.put(String.valueOf(rs.getInt("doc_id")), docDao.convertUserName(conn, rs.getString("now_des")) );
			}
			return mapNowDes;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
