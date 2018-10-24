<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mvc.model.vo.ContentVo" %>
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
${tableId = param.table_id;''}

<c:choose>
<c:when test="${tableId == 'R_NEW' || tableId == 'R_OLD'}">
<table style="font-size:16px; font-weight:bold">
	<tr>
		<td>작성자</td>
		<td width="750px" height="34px">${doc.gen_user[1]}</td>
	</tr>
	<tr>
		<td>제목 </td>
		<td height="34px">${doc.title}</td>
	</tr>
	<tr>
		<td colspan="2"><textarea readonly="readonly"> ${doc.content} </textarea></td>
	</tr>
	<tr>
		<td>첨부 파일</td>
		<td height="34px">${doc.attached}</td>
	</tr>
</table>
<c:if test="${tableId == 'R_OLD' }">
<div style="float:right; margin-right:500px; margin-top:10px">
	<form action="backComHan.do"><input type="submit" value="목록보기" class="btn5"/>
	<input type="hidden" name="doc_id" value="${doc.docId}">
	<input type="hidden" name="read" value="true">
	</form>
</div>
<div style="float:right; margin-right:100px; margin-top:10px">
	<form action="deleteDocComHan.do">
		<input type="submit" value="보관삭제" class="btn5"/>
		<input type="hidden" name="doc_id" value="${doc.docId}"/>
		<input type="hidden" name="isPermanent" value="stored"/>
	</form>
</div>
</c:if>

<c:if test="${tableId == 'R_NEW' }">
	<div style="float:right; margin-right:400px; margin-top:10px">
		<form action="backComHan.do"><input type="submit" value="목록보기" class="btn5"/>
		<input type="hidden" name="doc_id" value="${doc.docId}">
		<input type="hidden" name="read" value="true">
		</form>
	</div>
	<div style="float:right; margin-right:100px; margin-top:10px">
		<form action="deleteDocComHan.do">
			<input type="submit" value="보관삭제" class="btn5"/>
			<input type="hidden" name="doc_id" value="${doc.docId}"/>
			<input type="hidden" name="isPermanent" value="stored"/>
		</form>
	</div>
	<div style="float:right; margin-right:100px; margin-top:10px">
		<form action="backComHan.do"><input type="submit" value="재게시" class="btn5"/></form>
	</div>
</c:if>

</c:when>

<c:when test="${tableId == 'R_SEND'}">

<form action="updateDocComHan.do">
	<input type="hidden" name="doc_id" value="${doc.docId}"/>
	<input type="hidden" name="doc_type" value="personal"/>
	<input type="hidden" name="step" value="${param.des}"/>
	<input type="hidden" name="doc_title" value="${doc.title}"/>
<table style="font-size:16px; font-weight:bold">
	<tr>
		<td>수신자</td>
		<td colspan="2">
		<c:choose>
		
			<c:when test="${param.des == '1'}">
			<select name="des1" size="1" class="select5">
				<option value="none">선택하세요</option>
				<c:forEach var="userName" items="${userMap}" varStatus="index">
					<c:if test="${userName.key == doc.des1[0]}">
					<option value="${userName.key}-${userName.value}" selected>${userName.key}-${userName.value}</option>
					</c:if>
					<c:if test="${userName.key != doc.des1[0]}">
					<option value="${userName.key}-${userName.value}">${userName.key}-${userName.value}</option>
					</c:if>
				</c:forEach>
			</select>
			<input type="hidden" name="doc_des" value="${doc.des1[0]}"/>
			</c:when>
			
			<c:when test="${param.des == '2'}">
			<select name="des1" size="1" class="select5">
				<option value="none">선택하세요</option>
				<c:forEach var="userName" items="${userMap}" varStatus="index">
					<c:if test="${userName.key == doc.des2[0]}">
					<option value="${userName.key}-${userName.value}" selected>${userName.key}-${userName.value}</option>
					</c:if>
					<c:if test="${userName.key != doc.des2[0]}">
					<option value="${userName.key}-${userName.value}">${userName.key}-${userName.value}</option>
					</c:if>
				</c:forEach>
			</select>
			<input type="hidden" name="doc_des" value="${doc.des2[0] }"/>
			</c:when>
			
			<c:when test="${param.des == '3'}">
			<select name="des1" size="1" class="select5">
				<option value="none">선택하세요</option>
				<c:forEach var="userName" items="${userMap}" varStatus="index">
					<c:if test="${userName.key == doc.des3[0]}">
					<option value="${userName.key}-${userName.value}" selected>${userName.key}-${userName.value}</option>
					</c:if>
					<c:if test="${userName.key != doc.des3[0]}">
					<option value="${userName.key}-${userName.value}">${userName.key}-${userName.value}</option>
					</c:if>
				</c:forEach>
			</select>
			<input type="hidden" name="doc_des" value="${doc.des3[0] }"/>
			
			</c:when>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td>제목</td>
		<td colspan="2"><input type="text" name="title" value="${doc.title}" class="read"></td>
	</tr>
	<tr>
		<td colspan="3"><textarea name="content"> ${doc.content} </textarea></td>
	</tr>
	<tr>
		<td>첨부 파일</td>
		<td colspan="2"><input type="file" name="attached"></td>
	</tr>

</table>
<div style="float:right; margin-right:400px; margin-top:10px"><input type="submit" value="수정" class="btn5"/></div>
</form>

<div style="float:right; margin-right:100px; margin-top:10px">
	<form action="deleteDocComHan.do">
		<input type="submit" value="보관삭제" class="btn5"/>
		<input type="hidden" name="doc_id" value="${doc.docId}"/>
		<input type="hidden" name="isPermanent" value="stored"/>
	</form>
</div>
<div style="float:right; margin-right:100px; margin-top:10px"><form action="backComHan.do"><input type="submit" value="목록보기" class="btn5"/></form></div>

<div style="float:bottom; margin-right:-100px; margin-top:70px; font:13pt bold Tahoma; font-weight:bold; color:rgb(255,0,0)">${msg}</div>
</c:when>
</c:choose>
</body>
</html>