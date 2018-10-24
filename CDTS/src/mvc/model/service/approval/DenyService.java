package mvc.model.service.approval;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import mvc.model.dao.ApprovalDesDao;
import mvc.model.dao.PersonalDao;
import mvc.model.service.ServiceModel;
import mvc.model.vo.ApprovalDesVo;

public class DenyService implements ServiceModel {
	public void service(int docId, String user) throws SQLException{
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			PersonalDao personalDao = new PersonalDao();
			ApprovalDesDao approvalDesDao = new ApprovalDesDao();
			ApprovalDesVo approvalDesVo = approvalDesDao.convertApprovalDesVo(conn, docId);
			personalDao.updateState(conn, "결재반송", docId);
			personalDao.updateListId(conn, approvalDesVo.getNowDes()[0], docId, "R_OLD");
			conn.commit();
		}
	}
}
