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
<div class="search-header">
<jsp:include page="../listView/searchMenu.jsp" flush="false"/>
</div>
<c:choose>
<c:when test="${param.appr == 'list' }">
<div class="list1">
<jsp:include page="../listView/approvalListView.jsp" flush="false"/>
</div>
</c:when>
<c:when test="${param.appr == 'send' }">
<div class="list1">
<jsp:include page="../listView/approvalSendListView.jsp" flush="false"/>
</div>
</c:when>
</c:choose>
<div class="list2">
<jsp:include page="../listView/personalListView.jsp" flush="false"/>
</div>
<div class="list3">
<jsp:include page="../listView/boardListView.jsp" flush="false"/>
</div>
</body>
</html>