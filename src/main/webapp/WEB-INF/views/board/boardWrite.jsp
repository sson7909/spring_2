<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${board } Write
	
	<form action="${board }Write" method="post">
		<p>작성자 <input type="text" name="writer"></p>
		<p>제목 <input type="text" name="title"></p>
		<p>내용 <textarea style="resize: none;" rows="10" cols="50" name="contents"></textarea></p>
		<button>쓰기</button>
	</form>
</body>
</html>