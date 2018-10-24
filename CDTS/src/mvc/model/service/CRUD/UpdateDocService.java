package mvc.model.service.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.model.dao.ApprovalDesDao;
import mvc.model.dao.DocumentDao;
import mvc.model.dao.PersonalDao;
import mvc.model.exception.updateException.UpdateException;
import mvc.model.service.ServiceModel;
import mvc.model.vo.DocumentVo;

public class UpdateDocService implements ServiceModel {

	public String personalService(boolean isModifiedDes, DocumentVo doc, int step, int docId, String oldDes, String oldTitle) throws SQLException{
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			DocumentDao docDao = new DocumentDao();
			PersonalDao personalDao = new PersonalDao();
			String msg = validate(doc);
			if(msg.equals("success")){
				if(isModifiedDes){
					docDao.update(conn, doc, step, docId);
					personalDao.delete(conn, docId, oldDes);
					personalDao.insert(conn, docId, doc.getDes1(), "R_NEW", null);
					DocumentVo document = new DocumentVo(
							"잘못 보내진 글입니다.", "[발신자의 실수로 인해 잘못보내어진 글입니다:] "+ oldTitle, new Date(), null, doc.getGen_user(), oldDes, null, null, "personal");
					int newDocId = docDao.insert(conn, document);
					personalDao.insert(conn, newDocId, oldDes, "R_NEW", null);
					conn.commit();
				}else{
					docDao.update(conn, doc, step, docId);
					personalDao.updateListId(conn, oldDes, docId, "R_NEW");
					conn.commit();
				}
			}
			return msg;
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public String approvalService(boolean isModified, DocumentVo doc, int docId, String[] oldDes) throws SQLException{
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			String msg = validate(doc);
			if(msg.equals("success")){
				DocumentDao docDao = new DocumentDao();
				PersonalDao personalDao = new PersonalDao();
				ApprovalDesDao approvalDesDao = new ApprovalDesDao();
				String state = personalDao.getState(conn, docId);
				if(state == null)
					throw new UpdateException();
				if(state.equals("결재대기")){
					//결재 대상자가 변경되었을 경우
					if(isModified){
						docDao.update(conn, doc, 0, docId);
						for(String des : oldDes){
							if(des.equals("")) break;
							personalDao.delete(conn, docId, des);
						}
						
						personalDao.insert(conn, docId, doc.getDes1(), "R_NEW", "결재대기");
						approvalDesDao.update(conn, docId, doc.getDes1(), doc.getDes2(), 1);
						//결재 대상자는 그대로고 내용이 변경되었을 경우
					}else{
						docDao.update(conn, doc, 0, docId);
						personalDao.updateListId(conn, doc.getDes1(), docId, "R_NEW");
					}
					conn.commit();
					
				}else
					return "결재중일 때는 변경할 수 없습니다.";

				return msg;
			}else{
				return msg;
			}
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public String boardService(DocumentVo doc, int docId, String groupId) throws SQLException{
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			String msg = validate(doc);
			if(msg.equals("success")){
				DocumentDao docDao = new DocumentDao();
				PersonalDao personalDao = new PersonalDao();
				docDao.update(conn, doc, 1, docId);
				personalDao.updateListId(conn, doc.getGen_user(), groupId, docId);
			}
			return msg;
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public String validate(DocumentVo doc){
		String msg = "success";
		if(doc.getTitle().equals("") || doc.getContent().equals("")){
			msg = "제목과 내용은 필수입력사항입니다.";
		}else if(doc.getDes1() == null){
			System.out.println("getdes1");
			msg = "첫번째 수신자는 필수입력사항입니다.";
		}else if(doc.getDes1()!=null&&doc.getDes2()==null&&doc.getDes3()!=null){
			System.out.println("getget");
			msg = "중간 수신자를 생략할 수 없습니다.";
		}
		return msg;
	}

}
