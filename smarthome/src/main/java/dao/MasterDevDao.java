package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.MasterDevice;

public class MasterDevDao {
	private JdbcTemplate jdbcTemplate;

	public MasterDevDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean isExist(String dev_id, String dev_password){
		List<MasterDevice> results = jdbcTemplate.query(
				"select * from sh_master_device where dev_name = ? and dev_password = ?",
				new RowMapper<MasterDevice>(){
					@Override
					public MasterDevice mapRow(ResultSet rs, int rowNum)
							throws SQLException{
						MasterDevice masterDev = new MasterDevice(rs.getString("dev_name"),
								rs.getString("dev_ip"),
								rs.getString("dev_password"));
						return masterDev;
					}
				},
				dev_id, dev_password);
		
		return results.isEmpty() ? false : true;
	}
	
	public boolean isExist(String dev_id){
		List<MasterDevice> results = jdbcTemplate.query(
				"select * from sh_master_device where dev_name = ?",
				new RowMapper<MasterDevice>(){
					@Override
					public MasterDevice mapRow(ResultSet rs, int rowNum)
							throws SQLException{
						MasterDevice masterDev = new MasterDevice(rs.getString("dev_name"),
								rs.getString("dev_ip"),
								rs.getString("dev_password"));
						return masterDev;
					}
				},
				dev_id);
		
		return results.isEmpty() ? false : true;
	}
}
