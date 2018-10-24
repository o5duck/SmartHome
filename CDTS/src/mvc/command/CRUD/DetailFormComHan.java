package mvc.command.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import mvc.model.dao.UserDao;
import mvc.model.service.CRUD.DetailFormService;
import mvc.model.vo.ApprovalDesVo;
import mvc.model.vo.AuthVo;
import mvc.model.vo.ContentVo;

public class DetailFormComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String docId = req.getParameter("docId");
		AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
		String userId = auth.getUserId();
		try{
			Connection conn = ConnectionProvider.getConnection();
			UserDao userDao = new UserDao();
			HashMap<String, String> userMap = new HashMap<>();
			userMap = userDao.selectAllUserMap(conn);
			req.setAttribute("userMap", userMap);
		}catch(SQLException e){}
		
		DetailFormService detailFormService = new DetailFormService();
		ContentVo contentVo = detailFormService.service(Integer.parseInt(docId), userId);
		if(req.getParameter("subArchi").startsWith("detailApproval")){
			ApprovalDesVo approvalDes = detailFormService.getApprovalDes(Integer.parseInt(docId));
			req.setAttribute("approvalDes", approvalDes); 
		}
		req.setAttribute("doc", contentVo);
		return "/WEB-INF/view/viewModule/mainArchitecture.jsp?archi="+req.getParameter("archi")+"&subArchi="+req.getParameter("subArchi")+"&table_id="+req.getParameter("table_id")+"&des="+req.getParameter("des");
	}
}
