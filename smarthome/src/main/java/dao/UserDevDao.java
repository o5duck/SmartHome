package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import model.UserDev;

public class UserDevDao {
	private JdbcTemplate jdbcTemplate;
	
	public UserDevDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public UserDev selectById(String user_id){
		List<UserDev> results = jdbcTemplate.query(
				"select * from sh_user_dev where user_id = ?",
				new RowMapper<UserDev>(){
					@Override
					public UserDev mapRow(ResultSet rs, int rowNum)
							throws SQLException{
						UserDev userDev = new UserDev(rs.getString("user_id"),
								rs.getString("master_dev_id"),
								rs.getString("sub_dev_id"));
						return userDev;
					}
				},
				user_id);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public void addInfo(final String user_id, final String master_dev_id, final String sub_dev_id){
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				PreparedStatement pstmt = con.prepareStatement(
						"insert into sh_user_dev (user_id, master_dev_id, sub_dev_id) values (?,?,?)");
				pstmt.setString(1, user_id);
				pstmt.setString(2, master_dev_id);
				pstmt.setString(3, sub_dev_id);
				return pstmt;
			}
		});
	}
	
	public void removeInfo(final String user_id){
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				PreparedStatement pstmt = con.prepareStatement(
						"delete from sh_user_dev where user_id = ?");
				pstmt.setString(1, user_id);
				return pstmt;
			}
		});
	}
}
