package mvc.model.service.CRUD;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.model.dao.ApprovalDesDao;
import mvc.model.dao.DocumentDao;
import mvc.model.service.ServiceModel;
import mvc.model.vo.ApprovalDesVo;
import mvc.model.vo.ContentVo;

public class DetailFormService implements ServiceModel {
	
	public ContentVo service(int docId, String userId) throws SQLException{
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			DocumentDao docDao = new DocumentDao();
			return docDao.selectById(conn, docId, userId);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public ApprovalDesVo getApprovalDes(int docId) throws SQLException{
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			ApprovalDesDao approvalDao = new ApprovalDesDao();
			return approvalDao.convertApprovalDesVo(conn, docId);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
