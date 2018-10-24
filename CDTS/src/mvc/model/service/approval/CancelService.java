package mvc.model.service.approval;

import java.sql.Connection;

import jdbc.connection.ConnectionProvider;
import mvc.model.dao.ApprovalDesDao;
import mvc.model.dao.DocumentDao;
import mvc.model.dao.PersonalDao;
import mvc.model.service.ServiceModel;

public class CancelService implements ServiceModel {
	public String service(int docId, String user) throws Exception{
		try(Connection conn = ConnectionProvider.getConnection()){
			conn.setAutoCommit(false);
			PersonalDao personalDao = new PersonalDao();
			ApprovalDesDao approvalDesDao = new ApprovalDesDao();
			DocumentDao docDao = new DocumentDao();
			String state = personalDao.getState(conn, docId);
			if(state.equals("결재대기")){
				personalDao.delete(conn, docId, user);
				personalDao.delete(conn, docId);
				approvalDesDao.delete(conn, docId);
				docDao.delete(conn, docId);
				conn.commit();
			}else{
				return "결재중일 때는 결재 취소할 수 없습니다.";
			}
			return null;
		}
	}
}
