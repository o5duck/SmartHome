<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/mainHome/simple.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-image:url('img/back4.jpg');">
<div class="header-main">
<div class="menu"><jsp:include page="mainMenu.jsp" flush="false"/></div>
<div class="pageName">
<c:choose>
<c:when test="${param.table_id == 'R_NEW' }"> INBOX DOCUMENT</c:when>
<c:when test="${param.table_id == 'R_OLD' }"> STORED DOCUMENT</c:when>
<c:when test="${param.table_id == 'R_SEND' }"> SENT DOCUMENT</c:when>
<c:when test="${param.table_id == 'write' }"> WRITE DOCUMENT</c:when>
<c:otherwise> ${param.table_id} 게시판</c:otherwise>
</c:choose>
</div>
<div class="info"><jsp:include page="topInfo.jsp" flush="false"/></div>
</div>
<div class="section-main">
<c:choose>
		<c:when test="${param.archi == 'sub'}">
			<jsp:include page="subArchitecture.jsp" flush="false"/>
		</c:when>
		<c:when test="${param.archi == 'writeBoard'}">
			<jsp:include page ="../writeForm/writeBoardForm.jsp" flush="false"/>
		</c:when>
		<c:when test="${param.archi == 'writePersonal'}">
			<jsp:include page="../writeForm/writePersonalForm.jsp" flush="false"/>
		</c:when>
</c:choose>
</div>
<div class="footer-main">
	<jsp:include page="copyrightView.jsp" flush="false"/>
</div>	
</body>
</html>