package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import mvc.model.vo.AuthVo;

public class LectureDao implements DaoModel {
	public void select(Connection conn, String id, AuthVo auth){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> subjects = new ArrayList<>();
		try{
			pstmt = conn.prepareStatement("select subject from lecture where attending_stu=?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			while(rs.next()){
				subjects.add(rs.getString("subject"));
			}
			auth.setSubjects(subjects);
		} catch(SQLException e){
			
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
