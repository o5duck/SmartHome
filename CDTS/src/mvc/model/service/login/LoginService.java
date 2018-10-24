package mvc.model.service.login;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.model.dao.ClubDao;
import mvc.model.dao.LectureDao;
import mvc.model.dao.UserDao;
import mvc.model.exception.LoginFailException;
import mvc.model.service.ServiceModel;
import mvc.model.vo.AuthVo;

public class LoginService implements ServiceModel {
	private UserDao userDao = new UserDao();
	//private ClubDao clubDao = new ClubDao();
	//private LectureDao lectureDao = new LectureDao();
	
	public AuthVo login(String id, String pwd) throws SQLException{
		Connection conn = ConnectionProvider.getConnection();
		try{
			AuthVo auth = userDao.selectById(conn, id);
			//해당 id가 없다면
			if(auth == null){
				throw new LoginFailException();
			}
			//pw가 일치하지 않으면
			if(!auth.matchPassword(pwd)){
				throw new LoginFailException();
			}
			//clubDao.select(conn, id, auth);
			//lectureDao.select(conn, id, auth);
			userDao.selectGroup(conn, id, "일반", auth);
			userDao.selectGroup(conn, id, "동아리", auth);
			userDao.selectGroup(conn, id, "수강", auth);
			return auth;
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
