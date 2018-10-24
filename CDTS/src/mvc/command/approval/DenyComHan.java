package mvc.command.approval;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.model.service.approval.DenyService;
import mvc.model.vo.AuthVo;

public class DenyComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		int docId = Integer.parseInt(req.getParameter("doc_id")); AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
		String user = auth.getUserId();
		
		DenyService denyService = new DenyService();
		denyService.service(docId, user);
		return "/backComHan.do";
	}

}
