package mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jdbc.JdbcUtil;
import mvc.model.vo.AuthVo;

public class UserDao implements DaoModel{
	public AuthVo selectById(Connection conn, String id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuthVo auth = null;
		try{
			pstmt = conn.prepareStatement("select * from user where user_id=?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()){
				auth = new AuthVo(rs.getString("user_id"), 
								  rs.getString("pwd"), 
								  rs.getString("name"),
								  rs.getString("tel_no"), 
								  rs.getString("address"),
								  rs.getString("mail"),
								  rs.getString("position"), 
								  rs.getString("grade"), 
								  rs.getString("class"));
			}
		} catch(SQLException e){
			
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return auth;
	}
	
	public HashMap<String, String> selectAllUserMap(Connection conn){
		HashMap<String, String> userMap = new HashMap<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select user_id, name from user");
			rs=pstmt.executeQuery();
			while(rs.next()){
				userMap.put(rs.getString("user_id"), rs.getString("name"));
			}
		}catch(SQLException e){
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return userMap;
	}
	
	public List<String> selectUserGroup(Connection conn, String userId){
		List<String> groupList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select group_id from user_group where user_id=?");
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				groupList.add(rs.getString("group_id"));
			}
		}catch(SQLException e){
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return groupList;
	}
	
	public List<String> selectMemebers(Connection conn, String groupId){
		List<String> memberList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select user_id from user_group where group_id=?");
			pstmt.setString(1, groupId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				memberList.add(rs.getString("user_id"));
			}
		}catch(SQLException e){
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return memberList;
	}
	
	public void selectGroup(Connection conn, String id, String category, AuthVo auth){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> lists = new ArrayList<>();
		try{
			pstmt = conn.prepareStatement("select group_id from user_group where user_id=? and group_category=?");
			pstmt.setString(1, id);
			pstmt.setString(2, category);
			rs= pstmt.executeQuery();
			while(rs.next()){
				lists.add(rs.getString("group_id"));
			}
			switch(category){
			case "일반":
				auth.setGenerals(lists);
				break;
			case "동아리":
				auth.setClubs(lists);
				break;
			case "수강":
				auth.setSubjects(lists);
				break;
			}
			
		} catch(SQLException e){
			
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
