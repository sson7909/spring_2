<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${board } list</h2>
	
	<table>
		<c:forEach items="${dto }" var="dto">
			<tr>
				<td>num</td>
				<td>작성자</td>
				<td>제목</td>
				<td>글</td>
				<td>날짜</td>
				<td>조회</td>
			</tr>
			
			<tr>
				<td>${dto.num}</td>
				<td>${dto.writer}</td>
				<td>${dto.title}</td>
				<td>${dto.contents}</td>
				<td>${dto.reg_date}</td>
				<td>${dto.hit}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>