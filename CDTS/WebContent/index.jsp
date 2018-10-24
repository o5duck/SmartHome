<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/login/login.css">
<link rel="stylesheet" href="css/font-awesome-4.7.0/css/font-awesome.css">
<title>Closed Document Transfer System</title>
</head>

<body>
<div class="container-login">
	<img src="img/man.png">
<form method="post" action="loginProc.jsp">
	<div class="form-login">
		<center><input type="text" name="user_id" placeholder="Enter Username"></center>
	</div>
	<div class="form-login">
		<center><input type="password" name="pwd" placeholder="Enter Password"/></center>
	</div>	
		<center><input type="submit" name="submit" value="L O G I N" class="btn-login"/></center>
		<c:if test="${param.msg != null}"><center><h5 style="color:#fff;">${param.msg}</h5></center></c:if>
</form>
</div>
</body>
</html>