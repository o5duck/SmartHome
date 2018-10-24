package mvc.model.service.CRUD;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.model.dao.BoardDao;
import mvc.model.dao.DocumentDao;
import mvc.model.dao.PersonalDao;
import mvc.model.exception.writeException.WriteAnswerException;
import mvc.model.exception.writeException.WriteException;
import mvc.model.vo.DocumentVo;
import mvc.model.vo.WriteResultVo;

public class WriteAnswerDocService{

	public WriteResultVo service(DocumentVo docVo, String des_user, int oldDocId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = ConnectionProvider.getConnection();
		try{
			String msg = validate(docVo);
			if(msg.equals("success")){
				conn.setAutoCommit(false);
				DocumentDao docDao = new DocumentDao();
				BoardDao boardDao = new BoardDao();
				PersonalDao personalDao = new PersonalDao();
				int docId = docDao.insert(conn, docVo);
				if(docId == -1)
					throw new WriteException();
				
				String boardseqId = boardDao.getBoardSeqNum(conn, oldDocId);
				int answerCnt = boardDao.getAnswerCnt(conn, oldDocId);
				boardDao.setAnswerCnt(conn, oldDocId);
				;
				if(boardseqId.endsWith("999999")){
					String newBoardseqId = boardseqId.substring(0, boardseqId.length()-6);
					int newNo = Integer.parseInt(boardseqId.substring(boardseqId.length()-6, boardseqId.length()-4));
					newNo -= (answerCnt+1);
					newBoardseqId = newBoardseqId + newNo + boardseqId.substring(boardseqId.length()-4, boardseqId.length());
					boardseqId = newBoardseqId;
				}else if(boardseqId.endsWith("9999")){
					String newBoardseqId = boardseqId.substring(0, boardseqId.length()-4);
					int newNo = Integer.parseInt(boardseqId.substring(boardseqId.length()-4, boardseqId.length()-2));
					newNo -= (answerCnt+1);
					newBoardseqId = newBoardseqId + newNo + boardseqId.substring(boardseqId.length()-2, boardseqId.length());
					boardseqId = newBoardseqId;
				}else if(boardseqId.endsWith("99")){
					String newBoardseqId = boardseqId.substring(0, boardseqId.length()-2);
					int newNo = Integer.parseInt(boardseqId.substring(boardseqId.length()-2, boardseqId.length()));
					newNo -= (answerCnt+1);
					newBoardseqId = newBoardseqId + newNo;
					boardseqId = newBoardseqId;
				}else{
					System.out.println("test:::::::::::::::::::::::");
					return new WriteResultVo(0, "더이상 답변을 남길수 없습니다(3계층이 최대입니다.)");
				}
				
				if(boardDao.insert(conn, docId, docVo.getBoardId(), boardseqId)==-1)
					throw new WriteException();
				
				if(personalDao.insert(conn, docId, des_user, "R_NEW", null)==-1 || personalDao.insert(conn, docId, docVo.getGen_user(), "R_SEND", null)==-1){
					throw new WriteException();
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
		}else if(doc.getDes1()!=null&&doc.getDes2()==null&&doc.getDes3()!=null){
			System.out.println("getget");
			msg = "중간 수신자를 생략할 수 없습니다.";
		}
		return msg;
	}

}
