package mvc.model.service.approval;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.model.dao.ApprovalDesDao;
import mvc.model.dao.DocumentDao;
import mvc.model.dao.PersonalDao;
import mvc.model.service.ServiceModel;
import mvc.model.vo.ApprovalDesVo;
import mvc.model.vo.ContentVo;

public class ApprovalService implements ServiceModel {
	public void service(boolean isStored, int docId, String user) throws SQLException{
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PersonalDao personalDao = new PersonalDao();
			ApprovalDesDao approvalDesDao = new ApprovalDesDao();
			DocumentDao docDao = new DocumentDao();
			ContentVo doc = docDao.selectById(conn, docId, user);
			ApprovalDesVo approvalDesVo = approvalDesDao.convertApprovalDesVo(conn, docId);
			
			personalDao.updateState(conn, "결재중", docId);
			if(!isStored){
				personalDao.delete(conn, docId, user);
			}else{
				personalDao.updateListId(conn, user, docId, "R_OLD");
			}
			if(approvalDesVo.getNextDes()[0] == null){
				personalDao.updateState(conn, "결재완료", docId);
			}else{
				personalDao.insert(conn, docId, approvalDesVo.getNextDes()[0], "R_NEW", "결재중");
				if(approvalDesVo.getStep() == 1){
					approvalDesDao.update(conn, docId, doc.getDes2()[0], doc.getDes3()[0], 2);
				}else if(approvalDesVo.getStep() == 2){
					approvalDesDao.update(conn, docId, doc.getDes3()[0], null, 3);
				}
			}
			conn.commit();
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
