package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import mvc.model.vo.AuthVo;

public class ClubDao implements DaoModel {
	public void select(Connection conn, String id, AuthVo auth){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> clubs = new ArrayList<>();
		try{
			pstmt = conn.prepareStatement("select club_name from club where user_id=?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			while(rs.next()){
				clubs.add(rs.getString("club_name"));
			}
			auth.setClubs(clubs);
		} catch(SQLException e){
			
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
