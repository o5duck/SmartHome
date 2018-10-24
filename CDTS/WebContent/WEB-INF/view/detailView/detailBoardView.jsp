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
${table = param.table;''}
${tableId = param.table_id;''}
${docType = param.docType;''}
<c:choose>
<c:when test="${tableId == 'R_NEW' || tableId == 'R_OLD' || (tableId == '' && doc.gen_user[0] != auth.userId)}">
<table style="font-size:16px; font-weight:bold">
	<tr>
		<td>작성자</td>
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
<c:when test="${table != 'personal'}">
<div style="float:right; margin-right:500px; margin-top:10px"><form action="backComHan.do"><input type="submit" value="목록보기" class="btn5"/>
<input type="hidden" name="doc_id" value="${doc.docId}"/>
<input type="hidden" name="read" value="true"></form></div>
<div style="float:right; margin-right:100px; margin-top:10px"><form action="writeFormComHan.do"><input type="submit" value="답변" class="btn5"/>
<input type="hidden" name="doc_type" value="answer"/>
<input type="hidden" name="des1" value="${doc.des1[0]}"/>
<input type="hidden" name="title" value="${doc.title}"/>
<input type="hidden" name="old_gen_user" value="${doc.gen_user[0]}"/>
<input type="hidden" name="old_doc_id" value="${doc.docId}"/></form></div>
</c:when>

<c:when test="${table == 'personal' && tableId == 'R_NEW'}">
<div style="float:right; margin-right:400px; margin-top:10px"><form action="backComHan.do"><input type="submit" value="목록보기" class="btn5"/>
<input type="hidden" name="doc_id" value="${doc.docId}"/>
<input type="hidden" name="read" value="true"></form></div>
<div style="float:right; margin-right:60px; margin-top:10px"><form action="writeFormComHan.do">
<input type="submit" value="답변" class="btn5"/>
<input type="hidden" name="doc_type" value="answer"/>
<input type="hidden" name="des1" value="${doc.des1[0]}"/>
<input type="hidden" name="title" value="${doc.title}"/>
<input type="hidden" name="old_gen_user" value="${doc.gen_user[0]}"/>
<input type="hidden" name="old_doc_id" value="${doc.docId}"/></form></div>
<div style="float:right; margin-right:60px; margin-top:10px"><form action="deleteDocComHan.do">
	<input type="submit" value="보관삭제" class="btn5"/>
	<input type="hidden" name="doc_id" value="${doc.docId}"/>
	<input type="hidden" name="isPermanent" value="stored"/></form></div>
<div style="float:right; margin-right:60px; margin-top:10px"><form action="backComHan.do"><input type="submit" value="재게시" class="btn5"/></form></div>
</c:when>

<c:when test="${table == 'personal' && tableId != 'R_NEW'}">
<div style="float:right; margin-right:400px; margin-top:10px"><form action="backComHan.do"><input type="submit" value="목록보기" class="btn5"/>
<input type="hidden" name="doc_id" value="${doc.docId}"/>
<input type="hidden" name="read" value="true"></form></div>
<div style="float:right; margin-right:100px; margin-top:10px"><form action="writeFormComHan.do"><input type="submit" value="답변" class="btn5"/>
<input type="hidden" name="doc_type" value="answer"/>
<input type="hidden" name="des1" value="${doc.des1[0]}"/>
<input type="hidden" name="title" value="${doc.title}"/>
<input type="hidden" name="old_gen_user" value="${doc.gen_user[0]}"/>
<input type="hidden" name="old_doc_id" value="${doc.docId}"/></form></div>
<div style="float:right; margin-right:100px; margin-top:10px"><form action="deleteDocComHan.do">
	<input type="submit" value="보관삭제" class="btn5"/>
	<input type="hidden" name="doc_id" value="${doc.docId}"/>
	<input type="hidden" name="isPermanent" value="stored"/></form></div>
</c:when>
</c:choose>
</c:when>

<c:when test="${tableId == 'R_SEND' || (docType == 'answer' && doc.gen_user[0] == auth.userId)}">
<form action="updateDocComHan.do">
<input type="hidden" name="doc_id" value="${doc.docId}"/>
<input type="hidden" name="des1" value="${doc.des1[0]}"/>
<input type="hidden" name="doc_type" value="board"/>
<table style="font-size:16px; font-weight:bold">
	<tr>
		<td>수신 그룹</td>
		<td width="750px" height="34px">${doc.des1[0]}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td height="34px"><input type="text" name="title" value="${doc.title}"  class="read"></td>
	</tr>
	<tr>
		<td colspan="2" height="34px"><textarea name="content"> ${doc.content} </textarea></td>
	</tr>
	<tr>
		<td>첨부 파일</td>
		<td height="34px"><input type="file" name="attached"></td>
	</tr>
</table>
<div style="float:right; margin-right:400px; margin-top:10px"><input type="submit" value="수정"  class="btn5"/></div></form>
<div style="float:right; margin-right:100px; margin-top:10px"><form action="backComHan.do"><input type="submit" value="목록보기"  class="btn5"/></form></div>
<div style="float:right; margin-right:100px; margin-top:10px"><form action="deleteDocComHan.do">
	<input type="submit" value="문서삭제"  class="btn5"/>
	<input type="hidden" name="doc_id" value="${doc.docId}"/>
	<input type="hidden" name="isPermanent" value="permanent"/>
	<input type="hidden" name="group_id" value="${doc.des1[0]}"/>
</form></div>
<div style="float:bottom; margin-right:-100px; margin-top:70px; font:13pt bold Tahoma; font-weight:bold; color:rgb(255,0,0)">${msg}</div>
</c:when>
</c:choose>
</body>
</html>