package mvc.command.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import mvc.model.dao.UserDao;
import mvc.model.exception.urlException.UrlNotFoundException;
import mvc.model.vo.AuthVo;

public class WriteFormComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String table = req.getParameter("table"); String docType = req.getParameter("doc_type"); String boardId = req.getParameter("board_id");
		AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
		String userId = auth.getUserId();
		req.setAttribute("gen_user", userId);
		
		try{
			Connection conn = ConnectionProvider.getConnection();
			UserDao userDao = new UserDao();
			if(!docType.equals("board")){
				HashMap<String, String> userMap = new HashMap<>();
				userMap = userDao.selectAllUserMap(conn);
				req.setAttribute("userMap", userMap);
			}else{
				List<String> userGroup = new ArrayList<>();
				userGroup = userDao.selectUserGroup(conn, "21460059");
				req.setAttribute("groupList", userGroup);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		if(docType.equals("personal")){
			return "/WEB-INF/view/viewModule/mainArchitecture.jsp?archi=writePersonal&doc_type=personal&table_id=write";
		}else if(docType.equals("approval")){
			return "/WEB-INF/view/viewModule/mainArchitecture.jsp?archi=writePersonal&doc_type=approval&table_id=write";
		}else if(docType.equals("board")){
			if(table.equals("personal")){
				return "/WEB-INF/view/viewModule/mainArchitecture.jsp?archi=writeBoard&doc_type=board&board_id=none&table_id=write";
			}else if(table.equals("board")){
				return "/WEB-INF/view/viewModule/mainArchitecture.jsp?archi=writeBoard&doc_type=board&table_id=write&board_id=" + boardId;
			}else{
				throw new UrlNotFoundException();
			}
		}else if(docType.equals("answer")){
			String url = "/WEB-INF/view/viewModule/mainArchitecture.jsp?archi=writeBoard&doc_type=answer&table_id=write&des1=" + req.getParameter("des1") + "&title=" + req.getParameter("title") + "&old_gen_user=" + req.getParameter("old_gen_user") + "&old_doc_id=" + req.getParameter("old_doc_id");
			return url;
		}else{
			throw new UrlNotFoundException();
		}
	}

}
