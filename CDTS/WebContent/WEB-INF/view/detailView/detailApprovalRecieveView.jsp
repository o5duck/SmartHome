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

<table style="font-size:16px; font-weight:bold">
	<tr>
		<td>발신자</td>
		<td width="750px" height="34px">${doc.gen_user[1]}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td height="34px">${doc.title}</td>
	</tr>
	<tr>
		<td colspan="2" height="34px"><textarea readonly="readonly"> ${doc.content} </textarea></td>
	</tr>
	<tr>
		<td>첨부 파일</td>
		<td height="34px">${doc.attached}</td>
	</tr>
</table>

<c:choose>
<c:when test="${param.table_id == 'R_NEW' }">
<div style="float:right; margin-right:400px; margin-top:10px">
<form action="holdComHan.do"><input type="submit" value="보류" class="btn5"/>
<input type="hidden" name="doc_id" value="${doc.docId}"/>
<input type="hidden" name="doc_gen_user" value="${doc.gen_user[0]}"/>
<input type="hidden" name="doc_des" value="${auth.userId}"/>
</form>
</div>
<div style="float:right; margin-right:60px; margin-top:10px">
<form action="approvalComHan.do"><input type="submit" value="승인" class="btn5"/>
<input type="hidden" name="doc_id" value="${doc.docId}"/>
<input type="hidden" name="isStored" value="false"/>
</form>
</div>
<div style="float:right; margin-right:60px; margin-top:10px">
<form action="approvalComHan.do"><input type="submit" value="승인보관" class="btn5"/>
<input type="hidden" name="doc_id" value="${doc.docId}"/>
<input type="hidden" name="isStored" value="true"/>
</form>
</div>
<div style="float:right; margin-right:60px; margin-top:10px"><form action="denyComHan.do" >
<input type="submit" value="반송" class="btn5"/>
<input type="hidden" name="doc_id" value="${doc.docId }"/> 
</form></div>
</c:when>

<c:when test="${param.table_id == 'R_OLD' }">
<div style="float:right; margin-right:530px; margin-top:10px">
<form action="backComHan.do"><input type="submit" value="목록보기" class="btn5"/></form>
</div>
<div style="float:right; margin-right:100px; margin-top:10px">
<form action="deleteDocComHan.do">
	<input type="submit" value="보관삭제" class="btn5"/>
	<input type="hidden" name="doc_id" value="${doc.docId}"/>
	<input type="hidden" name="isPermanent" value="stored"/>
</form>
</div>
</c:when>
</c:choose>
</body>
</html>