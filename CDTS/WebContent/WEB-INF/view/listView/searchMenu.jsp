<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/default/form.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="1500" style="margin-top:15px; margin-bottom:10px;">
<tr><td align=left>
<form method="post" action="writeFormComHan.do">
	<input type="hidden" name="table" value="personal"/>
	<select name="doc_type" size="1" class="select1">
		<option value="personal">개인문서</option> <option value="approval">결재문서</option> <option value="board">그룹문서</option>
	</select>
	<input type="submit" value="WRITE" class="btn1"/>
</form>
</td>
<td align=right>
<form method="post" action="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=${param.table_id}&approvalPageNum=1&personalPageNum=1&boardPageNum=1">
	<select name="searchId" size="1" class="select2">
		<option value="approval">결재 문서</option> <option value="personal">개인문서</option> <option value="board">그룹문서</option>
	</select>
	<select name="searchCondition" size="1" class="select2">
		<option value="title">제목</option> <option value="content">제목+내용</option> 
		<c:if test="${param.table_id == 'R_SEND' }">
		<option value="receiver">수신자</option>
		</c:if>
		<c:if test="${param.table_id != 'R_SEND' }">
		<option value="writer">작성자</option>
		</c:if>
	</select>
	<input type="text" name="searchWord"/>
	<input type="submit" value="SEARCH" class="btn2"/>
</form>
</td></tr>
</table>
</body>
</html>