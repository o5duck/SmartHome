package mvc.model.service.CRUD;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.model.dao.BoardDao;
import mvc.model.dao.DocumentDao;
import mvc.model.dao.PersonalDao;
import mvc.model.exception.deleteException.DeleteException;
import mvc.model.service.ServiceModel;

public class DeleteDocService implements ServiceModel {
	
	public void service(String userId, int docId) throws SQLException{
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			PersonalDao personalDao = new PersonalDao();
			if(personalDao.delete(conn, docId, userId) == -1)
				throw new DeleteException();
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public void service(String userId, String groupId, int docId) throws SQLException{
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			DocumentDao docDao = new DocumentDao();
			BoardDao boardDao = new BoardDao();
			PersonalDao personalDao = new PersonalDao();
			if(boardDao.delete(conn, docId)){
				docDao.delete(conn, docId);
			}
			personalDao.delete(conn, groupId, docId);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
}
