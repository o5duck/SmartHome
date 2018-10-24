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
	<input type="hidden" name="des2" value="none"/>
	<input type="hidden" name="des3" value="none"/>
<table style="font-size:16px; font-weight:bold">
	<tr>
		<td>수신 그룹</td>
		<td width="750px" height="34px">
			<c:if test="${param.doc_type == 'board'}">
			<input type="hidden" name="doc_type" value="board"/>
			<select name="des1" size="1" class="select4">
			<option value="none">선택하세요</option>
			<c:forEach var="groupName" items="${groupList}" varStatus="index">
				<c:choose>
				<c:when test="${param.board_id == groupName}">
					<option value="${groupName}" selected>${groupName}</option>
				</c:when>
				<c:otherwise>
					<option value="${groupName}">${groupName}</option>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			</select>
			</c:if>
			<c:if test="${param.doc_type == 'answer'}">
			<input type="hidden" name="doc_type" value="answer"/>
			<input type="hidden" name="des1" value="${param.des1}"/>
			<input type="hidden" name="old_gen_user" value="${param.old_gen_user}"/>
			<input type="hidden" name="old_doc_id" value="${param.old_doc_id}"/>
				${param.des1}
			</c:if>
		</td>
	</tr>
	<tr>
		<td>제　　 목</td>
		<td height="34px">
			<c:if test="${param.doc_type == 'board'}">
			<input type="text" name="title" class="read"/>
			</c:if>
			<c:if test="${param.doc_type == 'answer'}">
			[답변]${param.title}
			<input type="hidden" name="title" value="[답변]${param.title}"/>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="34px"><textarea name="content"></textarea></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td height="34px"><input type="file" name="attached"/></td>
	</tr>
</table>
<div style="float:right; margin-right:540px; margin-top:10px"><input type="button" value="취소" class="btn5" onclick="javascript:history.go(-1);"/></div>
<div style="float:right; margin-right:100px; margin-top:10px"><input type="submit" value="작성" class="btn5"/></div>
<div style="float:bottom; margin-right:-100px; margin-top:70px; font:13pt bold Tahoma; font-weight:bold; color:rgb(255,0,0)">${msg}</div>
</form>

</body>
</html>