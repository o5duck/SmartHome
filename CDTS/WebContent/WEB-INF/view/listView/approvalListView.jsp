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
${documents = approvalPaging.content;''}
${tableId = param.table_id;''}
<div class="list-content">
<c:if test="${tableId == 'R_NEW' || tableId == 'R_OLD' }">
<h3>결재문서</h3><hr>
</c:if>
<c:if test="${tableId == 'R_SEND' }">
<c:choose>
<c:when test="${param.state == 'wait' }">
<h3>결재대기문서</h3><hr>
</c:when>
<c:when test="${param.state == 'ing' }">
<h3>결재진행문서</h3><hr>
</c:when>
<c:when test="${param.state == 'success' }">
<h3>결재완료문서</h3><hr>
</c:when>
<c:when test="${param.state == 'deny' }">
<h3>결재반송문서</h3><hr>
</c:when>
</c:choose>
</c:if>
<div class="table">
	<div class="row header blue">
		<div class="cell">번호</div>
		<c:choose>
			<c:when test="${tableId == 'R_NEW' || tableId == 'R_OLD'}">
			<div class="cell">발신자</div>
			${subArchi = 'detailApprovalReceiveView';''}
			</c:when>
			<c:when test="${tableId == 'R_SEND'}">
			<div class="cell">수신자</div>
			${subArchi = 'detailApprovalSendView';''}
			</c:when>
		</c:choose>
		<div class="cell">제목</div>
	</div>

	<c:forEach var="document" items="${documents}">
	<div class="row">
		<div class="cell">${document.docId}</div>
		<c:choose>
			<c:when test="${tableId == 'R_NEW' || tableId == 'R_OLD'}">
			<div class="cell">${document.gen_user[1]}</div>
			</c:when>
			<c:when test="${tableId == 'R_SEND'}">
			${docId = document.docId+="";''}
			<div class="cell">${approvMap[docId]}</div>
			</c:when>
		</c:choose>
		<div class="cell" style="text-align:left;"><a class="content" href="detailFormComHan.do?archi=sub&subArchi=${subArchi}&docId=${document.docId}&table_id=${tableId}">${document.title}</a></div>
	</div>
	</c:forEach>
</div>
</div>
<div class="list-page">
<c:if test="${approvalPaging.startPage > 5 }">
<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${tableId}&approvalPageNum=${approvalPaging.startPage-1}&personalPageNum=${personalPaging.currentPage}&boardPageNum=${boardPaging.currentPage}&searchId=${param.searchId}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}"> << </a>
</c:if>
<c:choose>
<c:when test="${approvalPaging.startPage == 0 }">
	<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${tableId}&approvalPageNum=1&personalPageNum=${personalPaging.currentPage}&boardPageNum=${boardPaging.currentPage}&searchId=${param.searchId}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}">1</a>
</c:when>
<c:otherwise>
<c:forEach var="pageNum" begin="${approvalPaging.startPage}" end="${approvalPaging.endPage}">
	<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${tableId}&approvalPageNum=${pageNum}&personalPageNum=${personalPaging.currentPage}&boardPageNum=${boardPaging.currentPage}&searchId=${param.searchId}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}">${pageNum}</a>
</c:forEach>
</c:otherwise>
</c:choose>
<c:if test="${approvalPaging.endPage < approvalPaging.totalPages}">
<a class="content" style="font-size:20px; color:#fff; " href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${tableId}&approvalPageNum=${approvalPaging.endPage+1}&personalPageNum=${personalPaging.currentPage}&boardPageNum=${boardPaging.currentPage}&searchId=${param.searchId}&searchCondition=${param.searchCondition}&searchWord=${param.searchWord}"> >> </a>
</c:if>
</div>
</body>
</html>