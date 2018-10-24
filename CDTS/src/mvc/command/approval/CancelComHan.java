package mvc.command.approval;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.model.service.approval.CancelService;
import mvc.model.vo.AuthVo;

public class CancelComHan implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		int docId = Integer.parseInt(req.getParameter("doc_id")); AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
		String user = auth.getUserId();
		
		CancelService cancelService = new CancelService();
		String msg = cancelService.service(docId, user);
		
		if(msg == null){
			return "/backComHan.do";
		}else{
			req.setAttribute("msg", msg);
			String url = "detailFormComHan.do?archi=sub&subArchi=detailApprovalSendView&docId="+docId+"&table_id=R_SEND";
			return url;
		}
		
	}
}
