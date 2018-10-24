<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="mvc.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/mainHome/menu.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="user" value="${sessionScope.auth}"/>
${clubs=user.clubs;''}
${subjects=user.subjects;''}
${generals=user.generals;''}
${allURL = "viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=전체&boardPageNum=1";''}
<%-- ${positionURL = "viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=" += user.position += "&boardPageNum=1";''}
${gradeURL = "viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=" += user.getGrade() += "&boardPageNum=1";''}
${classURL = "viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id="+= user.getClassName() += "&boardPageNum=1";'' } --%>

<ul>
  <li>개인 문서함
  	<ul>
      <li><a href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=R_NEW&approvalPageNum=1&personalPageNum=1&boardPageNum=1">새 수신 문서함</a></li>
      <li><a href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=R_OLD&approvalPageNum=1&personalPageNum=1&boardPageNum=1">보관 수신 문서함</a></li>
      <li><a href="viewDocListComHan.do?archi=sub&subArchi=list&table=personal&table_id=R_SEND&approvalPageNum=1&personalPageNum=1&boardPageNum=1">발신 문서함</a></li>
    </ul>
  </li>
  <li><a href="${allURL}">전체 게시판</a></li>
  <li>일반 게시판
    <ul>
      <c:forEach var="general" items="${generals}">
		${generalURL= "viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=" += general += "&boardPageNum=1";'' }
		<li><a href="${generalURL}"> ${general} 게시판 </a></li>
	  </c:forEach>
<%--       <li><a href="${positionURL}">${user.position} 게시판</a></li>
      <li><a href="${gradeURL}">${user.grade} 게시판</a></li>
      <li><a href="${classURL}">${user.className} 게시판</a></li> --%>
    </ul>
  </li>
  <li>동아리 게시판
  	<ul>
  	  <c:forEach var="club" items="${clubs}">
		${clubURL= "viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=" += club += "&boardPageNum=1";'' }
		<li><a href="${clubURL}"> ${club} 게시판 </a></li>
	  </c:forEach>
  	</ul>
  </li>
  <li>수강 게시판
  	<ul>
  	  <c:forEach var="subject" items="${subjects}">
		${subjectURL= "viewDocListComHan.do?archi=sub&subArchi=boardView&table=board&table_id=" += subject += "&boardPageNum=1";'' }
		<li><a href="${subjectURL}"> ${subject} 게시판 </a></li>
	  </c:forEach>
  	</ul>
  </li>
</ul>
</body>
</html>