package mvc.command.CRUD;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.model.service.CRUD.UpdateDocService;
import mvc.model.vo.AuthVo;
import mvc.model.vo.DocumentVo;

public class UpdateDocComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		AuthVo auth = (AuthVo)req.getSession().getAttribute("auth"); String gen_user = auth.getUserId();
		String docType = req.getParameter("doc_type"); 
		int docId = Integer.parseInt(req.getParameter("doc_id"));
		int step = 0;
		boolean isModifiedDes = false;
		String msg = ""; String subArchi = ""; DocumentVo doc = null;
		UpdateDocService updateDocService = new UpdateDocService();
		
		if(docType.equals("personal")){
			subArchi = "detailPersonalView";
			step = Integer.parseInt(req.getParameter("step"));
			String oldDes = req.getParameter("doc_des"); 
			String newDes = req.getParameter("des1").split("-")[0];
			String oldTitle = req.getParameter("doc_title");
			if(!oldDes.equals(newDes))
				isModifiedDes = true;
			if(newDes.equals("none"))
				newDes = null;
			doc = new DocumentVo(
					req.getParameter("title"), req.getParameter("content"), new Date(), null, gen_user, newDes, null, null, "personal");
			msg = updateDocService.personalService(isModifiedDes, doc, step, docId, oldDes, oldTitle);
		}else if(docType.equals("board")){
			subArchi = "detailBoard";
			String newDes = req.getParameter("des1").split("-")[0];
			doc = new DocumentVo(
					req.getParameter("title"), req.getParameter("content"), new Date(), null, gen_user, newDes, null, null, "board");
			msg = updateDocService.boardService(doc, docId, newDes);
		}else if(docType.equals("approval")){
			subArchi = "detailApprovalSendView";
			String oldDes[] = {req.getParameter("oldDes1"), req.getParameter("oldDes2"), req.getParameter("oldDes3")};
			String newDes[] = {req.getParameter("des1").split("-")[0], req.getParameter("des2").split("-")[0], req.getParameter("des3").split("-")[0]};
			boolean isModified = false;
			if(!oldDes[0].equals(newDes[0]) || !oldDes[1].equals(newDes[1]) || !oldDes[2].equals(newDes[2])){
				isModified = true;
			}
			if(newDes[0].equals(""))
				newDes[0] = null;
			if(newDes[1].equals(""))
				newDes[1] = null;
			if(newDes[2].equals(""))
				newDes[2] = null;
			doc = new DocumentVo(
					req.getParameter("title"), req.getParameter("content"), new Date(), null, gen_user, newDes[0], newDes[1], newDes[2], "approval");
			msg = updateDocService.approvalService(isModified, doc, docId, oldDes);
		}
		if(!msg.equals("success")){
			req.setAttribute("msg", msg);
			req.setAttribute("doc", doc);
			String url = "/detailFormComHan.do?archi=sub&subArchi="+subArchi+"&doc_type="+docType+"&docId="+docId+"&table_id=R_SEND&des="+step+"&msg="+msg;
			return url;
		}
		return "/backComHan.do";
	}

}
