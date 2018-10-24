package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.SubDevice;

public class SubDevDao {
	private JdbcTemplate jdbcTemplate;

	public SubDevDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean isExist(String dev_id, String dev_bd_addr){
		List<SubDevice> results = jdbcTemplate.query(
				"select * from sh_sub_device where dev_name = ? and dev_bd_addr = ?",
				new RowMapper<SubDevice>(){
					@Override
					public SubDevice mapRow(ResultSet rs, int rowNum)
							throws SQLException{
						SubDevice subDev = new SubDevice(rs.getString("dev_name"),
								rs.getString("dev_bd_addr"));
						return subDev;
					}
				},
				dev_id, dev_bd_addr);
		
		return results.isEmpty() ? false : true;
	}
}
