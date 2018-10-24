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

import model.User;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	
	public UserDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void insert(final User user) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into sh_user (user_id, user_name, user_password) "+
						"values (?, ?, ?)");
				pstmt.setString(1,  user.getUser_id());
				pstmt.setString(2,  user.getUser_name());
				pstmt.setString(3,  user.getUser_password());
				return pstmt;
			}
		});
	}
	
	public User selectById(String user_id) {
		List<User> results = jdbcTemplate.query(
				"select * from sh_user where user_id = ?",
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User(rs.getString("user_id"),
								rs.getString("user_name"),
								rs.getString("user_password"));
						return user;
					}
				},
				user_id);

		return results.isEmpty() ? null : results.get(0);
	}
}
