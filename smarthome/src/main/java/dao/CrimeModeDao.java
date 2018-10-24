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

import request.CrimeSearchRequest;

public class CrimeModeDao {
	private JdbcTemplate jdbcTemplate;
	
	public CrimeModeDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addInfo(final String master_dev_id, final String photo_path){
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into sh_user_crime (master_dev_id, photo_path) values (?,?)");
				pstmt.setString(1, master_dev_id);
				pstmt.setString(2, photo_path);
				return pstmt;
			}
		});
	}
	
	public List<String> searchImage(final CrimeSearchRequest req){
		String condition = "%" + req.getMaster_dev_id() + "/" + req.getMaster_dev_id() + "-" + req.get_year() + req.get_month() + req.get_date() + "-" + req.get_hour1() + "%";
		List<String> list = jdbcTemplate.query(
				"select photo_path from sh_user_crime where photo_path like ?", 
				new RowMapper<String>(){
					@Override
					public String mapRow(ResultSet rs, int rowNum)
						throws SQLException{
						String image = rs.getString("photo_path");
						return image;
					}
				},
				condition);
		return list;
	}
	
	
}
