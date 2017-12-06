<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
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

<script src="../resources/SE2/js/HuskyEZCreator.js"></script>

<script type="text/javascript">
		function del(del) {
			del.closest("div").remove();
		}
		
	$(function() {
		
		//SmartEditor start 
		var editor_object = [];

		nhn.husky.EZCreator.createInIFrame({
			oAppRef : editor_object,
			//textarea ID
			elPlaceHolder : "contents",
			/* 주소 바꿀껏  */
			sSkinURI : "../resources/SE2/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,
			}
		});
		
		
		$("#savebutton").click(function() {
			if($(".f1").length < 5){
			$.get("${pageContext.request.contextPath}/resources/fileAdd/fileAdd.jsp", function(re) {
				$(".f").append(re);
			});
			}else{
				alert("최대 5개만 가능함");
			}
			
		});
		
		
		
	});
</script>
</head>
<body>
	${board } Write
	
	<form action="${board }Write" method="post" enctype="multipart/form-data" id="frm">
			<div class="f">
				
			</div>
		<p>작성자 <input type="text" name="writer"></p>
		<p>제목 <input type="text" name="title"></p>
		<p>내용 <textarea style="resize: none;" rows="25" cols="150" name="contents" id="contents"></textarea></p>
		<button>쓰기</button>
		
		<input type="button" value="file add" id="savebutton">
		
		</form>
		
</body>
</html>