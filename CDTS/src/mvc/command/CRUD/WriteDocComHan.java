package mvc.command.CRUD;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.model.exception.writeException.WriteAnswerException;
import mvc.model.service.CRUD.WriteAnswerDocService;
import mvc.model.service.CRUD.WriteApprovalDocService;
import mvc.model.service.CRUD.WriteBoardDocService;
import mvc.model.service.CRUD.WriteDocService;
import mvc.model.service.CRUD.WritePersonalDocService;
import mvc.model.vo.AuthVo;
import mvc.model.vo.DocumentVo;
import mvc.model.vo.WriteResultVo;

public class WriteDocComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res){
		// TODO Auto-generated method stub
		int docId = 0; int oldDocId = 0; String subArchi = "";
		WriteResultVo wr = null; DocumentVo documentVo = null;
		AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
		String gen_user = auth.getUserId();
		String docType = req.getParameter("doc_type");
		String des1 = req.getParameter("des1").split("-")[0];
		String des2 = req.getParameter("des2").split("-")[0];
		String des3 = req.getParameter("des3").split("-")[0];
		WriteDocService writeDocService = null;
		Date now = new Date();
		try{
			if(des1.equals("none"))
				des1 = null;
			if(des2.equals("none"))
				des2 = null;
			if(des3.equals("none"))
				des3 = null;
			
			documentVo = new DocumentVo(
					req.getParameter("title"), req.getParameter("content"), now, null, gen_user, des1, des2, des3, req.getParameter("doc_type"));
			if(docType.equals("personal")){
				writeDocService = new WritePersonalDocService();
				subArchi="detailPersonalView";
				wr = writeDocService.service(documentVo);
				docId = wr.getDocId();
			}else if(docType.equals("board")){
				documentVo.setBoardId(des1);
				writeDocService = new WriteBoardDocService();
				subArchi="detailBoardView";
				wr = writeDocService.service(documentVo);
			}else if(docType.equals("approval")){
				writeDocService = new WriteApprovalDocService();
				subArchi="detailApprovalSendView";
				wr = writeDocService.service(documentVo);
			}else if(docType.equals("answer")){
				String des = req.getParameter("old_gen_user");
				oldDocId = Integer.parseInt(req.getParameter("old_doc_id"));
				documentVo.setBoardId(des1);
				WriteAnswerDocService writeAnswerDocService = new WriteAnswerDocService();
				subArchi="detailBoardView";
				wr = writeAnswerDocService.service(documentVo, des, oldDocId);
			}
			docId = wr.getDocId();
		}catch(WriteAnswerException e){
			req.setAttribute("msg", "더이상 답변을 남길수 없습니다(3계층이 최대입니다.)");
			return "detailFormComHan.do?archi=sub&subArchi=detailBoardView&docId="+oldDocId+"&table_id=R_OLD";
		}catch(Exception e){}
		if(!wr.getMsg().equals("success")){
			req.setAttribute("msg", wr.getMsg());
			req.setAttribute("doc", documentVo);
			String url = "/writeFormComHan.do?table=personal&doc_type="+docType;
			return url;
		}
		return "detailFormComHan.do?archi=sub&subArchi="+subArchi+"&docId="+docId+"&table_id=R_SEND"+"&des=1";
		
	}

}
