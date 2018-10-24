<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="mvc.model.service.login.LoginService" %>
<%@ page import="mvc.model.vo.*" %>
<%@ page import="mvc.model.exception.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		LoginService loginService = new LoginService();
	%>
	<c:catch var="loginException">
		<%
			String user_id = request.getParameter("user_id");
			String pwd = request.getParameter("pwd");
			AuthVo auth = loginService.login(user_id, pwd);
			session.setAttribute("auth", auth);
		%>
	</c:catch>
	
	
	<c:choose>
		<c:when test="${loginException == null}">
			<jsp:forward page="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=R_NEW&approvalPageNum=1&personalPageNum=1&boardPageNum=1"/>
		</c:when>
		<c:otherwise>
			<c:redirect url="index.jsp">
				<c:param name="msg" value="ID 혹은 PW를 다시 확인하세요."/>
			</c:redirect>
		</c:otherwise>
	</c:choose>
</body>
</html>