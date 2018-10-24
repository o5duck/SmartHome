package mvc.command.approval;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.model.service.approval.ApprovalService;
import mvc.model.vo.AuthVo;

public class ApprovalComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		int docId = Integer.parseInt(req.getParameter("doc_id")); AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
		String user = auth.getUserId(); boolean isStored = false;
		if(req.getParameter("isStored").equals("true")){
			isStored = true;
		}
		
		ApprovalService approvalService = new ApprovalService();
		approvalService.service(isStored, docId, user);
		return "/backComHan.do";
	}

}
