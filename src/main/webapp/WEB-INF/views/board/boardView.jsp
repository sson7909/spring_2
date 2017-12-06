<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>작성자 : ${one.writer }</h2>
	<h2>제목 : ${one.title }</h2>
	<h2>글 : ${one.contents }</h2>
	<h2>날짜 : ${one.reg_date }</h2>
	
	<div>
		<c:forEach items="${one.ar}" var="file">
			<a href="../resources/upload/${file.filename }">${file.oriname }</a>
		</c:forEach>
	</div>
	
	<c:if test="${board eq 'qna'}">
		<a href="${board }Reply">reply</a>
	</c:if>
	
	<a href="./${board }Update?num=${one.num }">Update</a>
	<a href="./${board }Delete?num=${one.num }">Delete</a>
	
</body>
</html>