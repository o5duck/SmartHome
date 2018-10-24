<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mvc.model.vo.ContentVo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/default/table.css">
<link rel="stylesheet" href="css/default/form.css">
<link rel="stylesheet" href="css/mainHome/listArchi.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${documents = boardPaging.content;''}
<div class="search-header">
<table width="1500">
<tr><td align=left>
<form method="post" action="writeFormComHan.do">
	<input type="hidden" name="table" value="board"/>
	<input type="hidden" name="doc_type" value="board"/>
	<input type="hidden" name="board_id" value="${param.table_id}"/>
	<input type="submit" value="WRITE" class="btn3"/>
</form>
</td>
<td align=right>
<form method="post" action="viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=${param.table_id}&approvalPageNum=1&personalPageNum=1&boardPageNum=1">
	<input type="hidden" name="searchId" value="board"/>
	<select name="searchCondition" size="1" class="select3">
		<option value="title">제목</option> <option value="content">제목+내용</option> <option value="writer">작성자</option>
	</select>
	<input type="text" name="searchWord" class="board"/>
	<input type="submit" value="SEARCH" class="btn4"/>
</form>
</td></tr>
</table>
</div>

<div class="boardList">
<div class="table">
	<div class="row header blue">
		<div class="cell">글 번호</div>
		<div class="cell">작성자</div>
		<div class="cell">제목</div>
		<div class="cell">게시일</div>
	</div>
	<c:forEach var="document" items="${documents}">
	<div class="row">
		<div class="cell">${document.docId }</div>
		<div class="cell">${document.gen_user[1] }</div>
		<div class="cell" style="text-align:left;">
		<c:choose>
		<c:when test="${document.title == '삭제된 글입니다.'}">
			${document.title}
		</c:when>
		<c:when test="${document.title != '삭제된 글입니다.'}">
			<a class="content" href="detailFormComHan.do?archi=sub&subArchi=detailBoardView&docId=${document.docId}&table=board&table_id=${document.listId}&docType=${document.doc_type}">${document.title}</a>
		</c:when>
		</c:choose>
		</div>
		<div class="cell">${ContentVo.convertDateFormat(document.c_time)}</div>
	</div>
	</c:forEach>	
</div>
</div>
<div class="boardList-page">
	<c:if test="${boardPaging.startPage > 5 }">
	<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=${param.table_id}&boardPageNum=${boardPaging.startPage-1}"> << </a>
	</c:if>
	<c:choose>
	<c:when test="${boardPaging.startPage == 0 }">
	<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=${param.table_id}&boardPageNum=1">1</a>
	</c:when>
	<c:otherwise>
	<c:forEach var="pageNum" begin="${boardPaging.startPage}" end="${boardPaging.endPage}">
	<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=${param.table_id}&boardPageNum=${pageNum}">${pageNum}</a>
	</c:forEach>
	</c:otherwise>
	</c:choose>
	<c:if test="${boardPaging.endPage < boardPaging.totalPages}">
	<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=${param.table_id}&boardPageNum=${boardPaging.endPage+1}"> >> </a>
	</c:if>
</div>
</body>
</html>