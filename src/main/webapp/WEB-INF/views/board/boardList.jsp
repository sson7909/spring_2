<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
		var message = '${message}';
		
		if(message != ' '){
			alert(message);
		}
		
		$(".list").click(function() {
			var cur = $(this).prop("title");
			var s = '${pager.search}';
			var t = '${pager.kind}';
			
			document.frm.curPage.value = cur;
			document.frm.search.value = s;
			document.frm.kind.value = t
			
			document.frm.submit();
		});
	});
</script>

<style type="text/css">
.list{
	cursor: pointer;
}
</style>
</head>
<body>
	<h2>${board } list</h2>
	
	<form action="./${board }List" method="get" name="frm">
		<input type="hidden" name="curPage" value="1">
		<select name="kind">
			<option>Title</option>
			<option>Writer</option>
			<option>Contents</option>
		</select>
			<input type="text" name="search">
			<button>검색</button>
	</form>
	
	
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
				<td>
				<c:catch>
					<c:forEach begin="1" end="${dto.depth }">
					--
					</c:forEach>
				</c:catch>
				 <a href="./${board }View?num=${dto.num}">${dto.title}</a></td>
				<td>${dto.contents}</td>
				<td>${dto.reg_date}</td>
				<td>${dto.hit}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div>
		<c:if test="${pager.curBlock gt 1}">
			<span class="list" title="${pager.startNum-1 }">이전</span>
		</c:if>
		
		<c:forEach begin="${pager.startNum }" end="${pager.lastNum }" var="i">
			<span class="list" title="${i}">${i }</span>
		</c:forEach>
		
		<c:if test="${pager.curBlock lt pager.totalBlock}">
			<span class="list" title="${pager.lastNum+1 }">다음</span>
		</c:if>
	</div>
	
	<a href="${board }Write">Write</a>
	
</body>
</html>