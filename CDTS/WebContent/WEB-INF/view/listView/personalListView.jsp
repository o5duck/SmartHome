<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="mvc.model.vo.*, mvc.model.dao.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/default/table.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${documents = personalPaging.content;''}
${tableId = param.table_id;''}
<div class="list-content">
<h3>개인문서</h3><hr>
<div class="table">
	<div class="row header green">
			<div class="cell">번호</div>
		<c:choose>
			<c:when test="${tableId == 'R_NEW' || tableId == 'R_OLD'}">
			<div class="cell">발신자</div>
			</c:when>
			<c:when test="${tableId == 'R_SEND'}">
			<div class="cell">수신자</div>
			</c:when>
		</c:choose>
			<div class="cell">제목</div>
			<div class="cell">작성일</div>
	</div>

	<c:forEach var="document" items="${documents}">
	<div class="row">
		<div class="cell">${document.docId}</div>
		<c:choose>
			<c:when test="${tableId == 'R_NEW' || tableId == 'R_OLD'}">
			<div class="cell">${document.gen_user[1]}</div>
			</c:when>
			<c:when test="${tableId == 'R_SEND'}">
			<div class="cell">${document.des1[1]}</div>
			</c:when>
		</c:choose>
		<div class="cell" style="text-align:left;"><a class="content" href="detailFormComHan.do?archi=sub&subArchi=detailPersonalView&docId=${document.docId}&table_id=${tableId}&des=1">${document.title}</a></div>
		<div class="cell">${ContentVo.convertDateFormat(document.c_time)}</div>
	</div>
	<c:if test="${tableId == 'R_SEND' && document.des2[1] != ''}">
	<div class="row">
		<div class="cell">${document.docId}</div>
		<div class="cell">${document.des2[1]}</div>
		<div class="cell" style="text-align:left;"><a class="content" href="detailFormComHan.do?archi=sub&subArchi=detailPersonalView&docId=${document.docId}&table_id=${tableId}&des=2">${document.title}</a></div>
		<div class="cell">${ContentVo.convertDateFormat(document.c_time)}</div>
	</div>
	</c:if>
	<c:if test="${tableId == 'R_SEND' && document.des3[1] != ''}">
	<div class="row">
		<div class="cell">${document.docId}</div>
		<div class="cell">${document.des3[1]}</div>
		<div class="cell" style="text-align:left;"><a class="content" href="detailFormComHan.do?archi=sub&subArchi=detailPersonalView&docId=${document.docId}&table_id=${tableId}&des=3">${document.title}</a></div>
		<div class="cell">${ContentVo.convertDateFormat(document.c_time)}</div>
	</div>
	</c:if>
	</c:forEach>
</div>
</div>
<div class="list-page">
<c:if test="${personalPaging.startPage > 5 }">
<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${tableId}&approvalPageNum=${approvalPaging.currentPage}&personalPageNum=${personalPaging.startPage-1}&boardPageNum=${boardPaging.currentPage}&searchId=${param.searchId}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}"> << </a>
</c:if>
<c:choose>
<c:when test="${personalPaging.startPage == 0 }">
	<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${tableId}&approvalPageNum=${approvalPaging.currentPage}&personalPageNum=1&boardPageNum=${boardPaging.currentPage}&searchId=${param.searchId}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}">1</a>
</c:when>
<c:otherwise>
<c:forEach var="pageNum" begin="${personalPaging.startPage}" end="${personalPaging.endPage}">
	<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${tableId}&approvalPageNum=${approvalPaging.currentPage}&personalPageNum=${pageNum}&boardPageNum=${boardPaging.currentPage}&searchId=${param.searchId}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}">${pageNum}</a>
</c:forEach>
</c:otherwise>
</c:choose>
<c:if test="${personalPaging.endPage < personalPaging.totalPages}">
<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${tableId}&approvalPageNum=${approvalPaging.currentPage}&personalPageNum=${personalPaging.endPage+1}&boardPageNum=${boardPaging.currentPage}&searchId=${param.searchId}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}"> >> </a>
</c:if>
</div>
</body>
</html>