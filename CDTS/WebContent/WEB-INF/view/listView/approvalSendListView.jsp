<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/default/circle.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="viewDocListComHan.do">
<input type="hidden" name="archi" value="sub"/>
<input type="hidden" name="subArchi" value="list"/>
<input type="hidden" name="table" value="personal"/>
<input type="hidden" name="table_id" value="R_SEND"/>
<input type="hidden" name="state" value="wait"/>
<input type="hidden" name="approvalPageNum" value="1"/>
<input type="hidden" name="personalPageNum" value="1"/>
<input type="hidden" name="boardPageNum" value="1"/>
<c:choose>
<c:when test="${cnt[0] == null}">
<input type="submit" value="결재대기  0건" class="waiting-approv" style="height:160px"/>
</c:when>
<c:otherwise>
<input type="submit" value="결재대기 ${cnt[0]}건" class="waiting-approv" style="height:160px"/>
</c:otherwise>
</c:choose>
</form>
<form action="viewDocListComHan.do">
<input type="hidden" name="archi" value="sub"/>
<input type="hidden" name="subArchi" value="list"/>
<input type="hidden" name="table" value="personal"/>
<input type="hidden" name="table_id" value="R_SEND"/>
<input type="hidden" name="state" value="ing"/>
<input type="hidden" name="approvalPageNum" value="1"/>
<input type="hidden" name="personalPageNum" value="1"/>
<input type="hidden" name="boardPageNum" value="1"/>
<c:choose>
<c:when test="${cnt[1] == null}">
<input type="submit" value="결재진행  0건" class="waiting-approv" style="height:160px"/>
</c:when>
<c:otherwise>
<input type="submit" value="결재진행 ${cnt[1]}건" class="ing-approv" style="height:160px"/>
</c:otherwise>
</c:choose>
</form>
<form action="viewDocListComHan.do">
<input type="hidden" name="archi" value="sub"/>
<input type="hidden" name="subArchi" value="list"/>
<input type="hidden" name="table" value="personal"/>
<input type="hidden" name="table_id" value="R_SEND"/>
<input type="hidden" name="state" value="success"/>
<input type="hidden" name="approvalPageNum" value="1"/>
<input type="hidden" name="personalPageNum" value="1"/>
<input type="hidden" name="boardPageNum" value="1"/>
<c:choose>
<c:when test="${cnt[2] == null}">
<input type="submit" value="결재완료  0건" class="waiting-approv" style="height:160px"/>
</c:when>
<c:otherwise>
<input type="submit" value="결재완료 ${cnt[2]}건" class="success-approv" style="height:160px"/>
</c:otherwise>
</c:choose>
</form>
<form action="viewDocListComHan.do">
<input type="hidden" name="archi" value="sub"/>
<input type="hidden" name="subArchi" value="list"/>
<input type="hidden" name="table" value="personal"/>
<input type="hidden" name="table_id" value="R_SEND"/>
<input type="hidden" name="state" value="deny"/>
<input type="hidden" name="approvalPageNum" value="1"/>
<input type="hidden" name="personalPageNum" value="1"/>
<input type="hidden" name="boardPageNum" value="1"/>
<c:choose>
<c:when test="${cnt[3] == null}">
<input type="submit" value="결재반송  0건" class="waiting-approv" style="height:160px"/>
</c:when>
<c:otherwise>
<input type="submit" value="결재반송 ${cnt[3]}건" class="deny-approv" style="height:160px"/>
</c:otherwise>
</c:choose>
</form>
</body>
</html>