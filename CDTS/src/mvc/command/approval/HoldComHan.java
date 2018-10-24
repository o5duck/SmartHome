package mvc.command.approval;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import mvc.model.dao.PersonalDao;

public class HoldComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		PersonalDao personalDao = new PersonalDao();
		int docId = Integer.parseInt(req.getParameter("doc_id"));
		try{
			Connection conn = ConnectionProvider.getConnection();
			personalDao.updateState(conn, "결재중", docId);
		}catch(SQLException e){}
		return "/backComHan.do";
	}

}
