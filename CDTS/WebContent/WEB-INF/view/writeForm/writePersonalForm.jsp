<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/default/form.css">
<link rel="stylesheet" href="css/default/doc.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="writeDocComHan.do">
	<input type="hidden" name="gen_user" value="${auth.userId}"/>
	<input type="hidden" name="doc_type" value="${param.doc_type}"/>
<table style="font-size:16px; font-weight:bold">
	<tr>
		<td>수신자</td>
		<td width="250px" height="34px">
		<select name="des1" size="1" class="select4">
			<option value="none" selected>선택하세요</option>
			<c:forEach var="userName" items="${userMap}" varStatus="index">
			<option value="${userName.key}-${userName.value}">${userName.key}-${userName.value}</option>
			</c:forEach>
		</select>
		</td>
		<td width="250px" height="34px">
		<select name="des2" size="1" class="select4">
			<option value="none" selected>선택하세요</option>
			<c:forEach var="userName" items="${userMap}" varStatus="index">
				<option value="${userName.key}-${userName.value}">${userName.key}-${userName.value}</option>
			</c:forEach>
		</select>		
		</td>
		<td width="250px" height="34px">
			<select name="des3" size="1" class="select4">
			<option value="none" selected>선택하세요</option>
			<c:forEach var="userName" items="${userMap}" varStatus="index">
				<option value="${userName.key}-${userName.value}">${userName.key}-${userName.value}</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>제　목</td>
		<td height="34px" colspan="3"><input type="text" name="title" class="read"/></td>
	</tr>
	<tr>
		<td colspan="4" height="34px"><textarea name="content"></textarea></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td height="34px" colspan="3"><input type="file" name="attached"/></td>
	</tr>
</table>
<div style="float:right; margin-right:540px; margin-top:10px"><input type="button" value="취 소" class="btn5" onclick="javascript:history.go(-1);"/></div>
<div style="float:right; margin-right:100px; margin-top:10px"><input type="submit" value="작 성" class="btn5"/></div>
<div style="float:bottom; margin-right:-100px; margin-top:70px; font:13pt bold Tahoma; font-weight:bold; color:rgb(255,0,0)">${msg}</div>
</form>

</body>
</html>