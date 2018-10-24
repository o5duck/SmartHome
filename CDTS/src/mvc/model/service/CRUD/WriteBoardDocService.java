package mvc.model.service.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.model.dao.BoardDao;
import mvc.model.dao.DocumentDao;
import mvc.model.dao.PersonalDao;
import mvc.model.dao.UserDao;
import mvc.model.exception.writeException.WriteException;
import mvc.model.vo.DocumentVo;
import mvc.model.vo.WriteResultVo;

public class WriteBoardDocService implements WriteDocService {

	@Override
	public WriteResultVo service(DocumentVo docVo) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectionProvider.getConnection();
		try{
			String msg = validate(docVo);
			if(msg.equals("success")){
				conn.setAutoCommit(false);
				DocumentDao docDao = new DocumentDao();
				BoardDao boardDao = new BoardDao();
				PersonalDao personalDao = new PersonalDao();
				UserDao userDao = new UserDao();
				
				int docId = docDao.insert(conn, docVo);
				String boardseqId = docId + "999999";
				if(docId == -1){
					throw new WriteException();
				}
				if(boardDao.insert(conn, docId, docVo.getBoardId(), boardseqId)==-1)
					throw new WriteException();
				List<String> memberList = userDao.selectMemebers(conn, docVo.getBoardId());
				for(String member : memberList){
					System.out.println(member);
					if(member.equals(docVo.getGen_user())){
						if(personalDao.insert(conn, docId, member, "R_SEND", null)==-1)
							throw new WriteException();
					}else{
						if(personalDao.insert(conn, docId, member, "R_NEW", null)==-1)
							throw new WriteException();
					}
				}
				conn.commit();
				return new WriteResultVo(docId, msg);
			}else{
				return new WriteResultVo(0, msg);
			}
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new WriteException();
		}catch(RuntimeException e){
			JdbcUtil.rollback(conn);
			throw new WriteException();
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
		}
		return msg;
	}

}
