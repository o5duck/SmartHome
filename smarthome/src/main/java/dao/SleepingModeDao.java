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

import response.SleepingResponse;

public class SleepingModeDao {
	private JdbcTemplate jdbcTemplate;
	
	public SleepingModeDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addInfo(final String master_dev_id, final String awake_time, final int temperature){
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into sh_user_sleeping (master_dev_id, awake_time, temperature) values(?,?,?)");
				pstmt.setString(1, master_dev_id);
				pstmt.setString(2, awake_time);
				pstmt.setInt(3, temperature);
				return pstmt;
			}
		});
	}
	
	public SleepingResponse searchInfo(final String master_dev_id){
		List<SleepingResponse> list = jdbcTemplate.query(
				"select * from sh_user_sleeping where master_dev_id = ?",
				new RowMapper<SleepingResponse>(){
					@Override
					public SleepingResponse mapRow(ResultSet rs, int rowNum) throws SQLException{
						return new SleepingResponse(rs.getString("awake_time"), rs.getInt("temperature"));
					}
				},
				master_dev_id);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public void updateInfo(final String master_dev_id, final String awake_time, final int temperature){
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(
						"update sh_user_sleeping set awake_time = ?, temperature = ? where master_dev_id = ?");
				pstmt.setString(1, awake_time);
				pstmt.setInt(2, temperature);
				pstmt.setString(3, master_dev_id);
				return pstmt;
			}
			
		});
	}
}
