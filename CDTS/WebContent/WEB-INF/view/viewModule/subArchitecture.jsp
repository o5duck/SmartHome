<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/mainHome/listArchi.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${param.subArchi == 'list'}">
		<jsp:include page="../listView/listArchitecture.jsp" flush="false"/>
	</c:when>
	<c:when test="${param.subArchi == 'boardView'}">
		<jsp:include page ="../boardView/boardView.jsp" flush="false"/>
	</c:when>
	<c:when test="${param.subArchi == 'detailApprovalSendView'}">
		<jsp:include page="../detailView/detailApprovalSendView.jsp" flush="false"/>
	</c:when>
	<c:when test="${param.subArchi == 'detailApprovalReceiveView'}">
		<jsp:include page="../detailView/detailApprovalRecieveView.jsp" flush="false"/>
	</c:when>
	<c:when test="${param.subArchi == 'detailBoardView'}">
		<jsp:include page="../detailView/detailBoardView.jsp" flush="false"/>
	</c:when>
	<c:when test="${param.subArchi == 'detailPersonalView'}">
		<jsp:include page="../detailView/detailPersonalView.jsp" flush="false"/>
	</c:when>
</c:choose>
</body>
</html>