 package mvc.command.back;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import mvc.model.dao.PersonalDao;
import mvc.model.vo.AuthVo;

public class BackComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if(req.getParameter("read") != null){
			AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
			String userId = auth.getUserId();
			int docId = Integer.parseInt(req.getParameter("doc_id"));
			try(Connection conn = ConnectionProvider.getConnection()){
				PersonalDao personalDao = new PersonalDao();
				personalDao.updateListId(conn, userId, docId, "R_OLD");
			}
		}
		
		String url = (String)req.getSession().getAttribute("storedUrl");
		return url;
	}

}
