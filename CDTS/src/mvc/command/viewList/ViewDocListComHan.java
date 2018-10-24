package mvc.command.viewList;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.model.service.paging.PagingService;
import mvc.model.vo.AuthVo;
import mvc.model.vo.DocPagingVo;

public class ViewDocListComHan implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		AuthVo auth = (AuthVo)req.getSession().getAttribute("auth");
		String userId = auth.getUserId();
		String table = req.getParameter("table"); String tableId = req.getParameter("table_id"); String searchId =req.getParameter("searchId");
		String state = req.getParameter("state");
		String searchCondition = ""; String searchWord = "";
		String appr=""; String dbstate=""; int[] stateCnt = {0,0,0,0};
		if(tableId != null)
			appr = (tableId.equals("R_SEND") && state == null) ? "send" : "list";
		
		if(state != null){
			switch(state){
			case "wait":
				dbstate = "결재대기";
				break;
			case "ing":
				dbstate = "결재중";
				break;
			case "success":
				dbstate = "결재완료";
				break;
			case "deny":
				dbstate = "결재반송";
				break;
			}
		}
		
		if(table.equals("personal")){
			int approvalPageNum = Integer.parseInt(req.getParameter("approvalPageNum"));
			int personalPageNum = Integer.parseInt(req.getParameter("personalPageNum"));
			int boardPageNum = Integer.parseInt(req.getParameter("boardPageNum"));
			PagingService approvalPagingService = new PagingService("approval");
			PagingService personalPagingService = new PagingService("personal");
			PagingService boardPagingService = new PagingService("board");
			DocPagingVo approvalDocPagingVo = null;
			DocPagingVo personalDocPagingVo = null; 
			DocPagingVo boardDocPagingVo = null;

			Map<String, String> map = approvalPagingService.getApprovalDes();
			req.setAttribute("approvMap", map);
			if(searchId == null || searchId.equals("null")){
				if(!tableId.equals("R_SEND"))
					approvalDocPagingVo = approvalPagingService.service(approvalPageNum, userId, tableId);
				else{
					approvalDocPagingVo = approvalPagingService.service(approvalPageNum, userId, dbstate);
					stateCnt[0] = approvalPagingService.getCntByState(userId, "결재대기");
					stateCnt[1] = approvalPagingService.getCntByState(userId, "결재중");
					stateCnt[2] = approvalPagingService.getCntByState(userId, "결재완료");
					stateCnt[3] = approvalPagingService.getCntByState(userId, "결재반송");
					req.setAttribute("cnt", stateCnt);
				}
				personalDocPagingVo = personalPagingService.service(personalPageNum, userId, tableId);
				boardDocPagingVo = boardPagingService.service(boardPageNum, userId, tableId);
			}else{
				searchCondition = req.getParameter("searchCondition");
				searchWord = req.getParameter("searchWord");
				if(searchId.equals("approval")){
					if(!tableId.equals("R_SEND"))
						approvalDocPagingVo = approvalPagingService.service(approvalPageNum, userId, tableId, searchCondition, searchWord);
					else
						approvalDocPagingVo = approvalPagingService.service(approvalPageNum, userId, dbstate, searchCondition, searchWord);
					personalDocPagingVo = personalPagingService.service(personalPageNum, userId, tableId);
					boardDocPagingVo = boardPagingService.service(boardPageNum, userId, tableId);
				}else if(searchId.equals("personal")){
					if(!tableId.equals("R_SEND"))
						approvalDocPagingVo = approvalPagingService.service(approvalPageNum, userId, tableId);
					else
						approvalDocPagingVo = approvalPagingService.service(approvalPageNum, userId, dbstate);
					personalDocPagingVo = personalPagingService.service(personalPageNum, userId, tableId, searchCondition, searchWord);
					boardDocPagingVo = boardPagingService.service(boardPageNum, userId, tableId);
				}else if(searchId.equals("board")){
					if(!tableId.equals("R_SEND"))
						approvalDocPagingVo = approvalPagingService.service(approvalPageNum, userId, tableId);
					else
						approvalDocPagingVo = approvalPagingService.service(approvalPageNum, userId, dbstate);
					personalDocPagingVo = personalPagingService.service(personalPageNum, userId, tableId);
					boardDocPagingVo = boardPagingService.service(boardPageNum, userId, tableId, searchCondition, searchWord);
				}
			}
			
			req.setAttribute("approvalPaging", approvalDocPagingVo);
			req.setAttribute("personalPaging", personalDocPagingVo);
			req.setAttribute("boardPaging", boardDocPagingVo);
			
		}else if(table.equals("board")){
			int boardPageNum = Integer.parseInt(req.getParameter("boardPageNum"));
			System.out.println(table + ":::::" + tableId + "::::::::::" + boardPageNum + ":::::::");
			PagingService boardPagingService = new PagingService("board");
			DocPagingVo boardDocPagingVo = null;
			if(searchId == null){
				boardDocPagingVo = boardPagingService.service(boardPageNum, userId, tableId, false);
			}else{
				searchCondition = req.getParameter("searchCondition");
				searchWord = req.getParameter("searchWord");
				boardDocPagingVo = boardPagingService.service(boardPageNum, userId, tableId, searchCondition, searchWord, false);
			}
			req.setAttribute("boardPaging", boardDocPagingVo);
		}
		
		String url = req.getRequestURL().toString() + "?" + req.getQueryString();
		String[] storedUrl = url.split("/CDTS/");
		req.getSession().setAttribute("storedUrl", storedUrl[1]);
		return "/WEB-INF/view/viewModule/mainArchitecture.jsp?archi="+req.getParameter("archi")+"&subArchi="+req.getParameter("subArchi")+"&table_id="+tableId+"&appr="+appr+"&searchId="+searchId+"&searchCondition="+searchCondition+"&searchWord="+searchWord;
	}

}
