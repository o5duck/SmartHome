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

import response.OutsideResponse;

public class OutsideModeDao {
	private JdbcTemplate jdbcTemplate;
	
	public OutsideModeDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addInfo(final String master_dev_id, final String auto_lighting_time, final String auto_lighting_room_id){
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into sh_user_outside (master_dev_id, auto_lighting_room_id, auto_lighting_time) values(?,?,?)");
				pstmt.setString(1, master_dev_id);
				pstmt.setString(2, auto_lighting_room_id);
				pstmt.setString(3, auto_lighting_time);
				return pstmt;
			}
		});
	}
	
	public OutsideResponse searchInfo(final String master_dev_id){
		List<OutsideResponse> list = jdbcTemplate.query(
				"select * from sh_user_outside where master_dev_id = ?",
				new RowMapper<OutsideResponse>(){
					@Override
					public OutsideResponse mapRow(ResultSet rs, int rowNum) throws SQLException{
						return new OutsideResponse(rs.getString("auto_lighting_room_id"), rs.getString("auto_lighting_time"));
					}
				},
				master_dev_id);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public void updateInfo(final String master_dev_id, final String auto_lighting_time, final String auto_lighting_room_id){
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(
						"update sh_user_outside set auto_lighting_room_id = ?, auto_lighting_time = ? where master_dev_id = ?");
				pstmt.setString(1, auto_lighting_room_id);
				pstmt.setString(2, auto_lighting_time);
				pstmt.setString(3, master_dev_id);
				return pstmt;
			}
			
		});
	}
}
