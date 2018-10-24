package mvc.command.CRUD;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.model.service.CRUD.DeleteDocService;
import mvc.model.vo.AuthVo;

public class DeleteDocComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
		String userId = auth.getUserId();
		String flag = req.getParameter("isPermanent");
		String groupId = req.getParameter("group_id");
		int docId = Integer.parseInt(req.getParameter("doc_id"));
		DeleteDocService deleteDocService = new DeleteDocService();
		
		if(flag.equals("permanent")){
			deleteDocService.service(userId, groupId, docId);
		}else{
			deleteDocService.service(userId, docId);
		}
		return "/backComHan.do";
	}

}
