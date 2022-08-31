<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<script src="${path}/resources/js/board/board.js"></script>
<body>
	<%@ include file="../top.jsp" %>
	
	<!-- 제목, 내용  url -->
	<form action = "${path}/board/addboard" id="boardFrm" method="post" enctype="multipart/form-data">
	<input type="hidden" id="board_writer" name ="board_writer" value="${sessionScope.user_email}">
		<table class="table table-striped table-bordered">
	<tr>
		<th>제목</th>                                     <!-- 제목 입력 -->
		<td><input type="text" class="form-control" id="board_subject" name="board_title" ></td>
	</tr>
	<tr>
		<th>내용</th>                                     <!-- 내용 입력 -->
		<td><textarea rows="10" style="resize:none;" class="form-control" id="board_content" name="board_content"></textarea></td>
	</tr>
    <tr>
		<th>영상 소스코드</th>                                    <!-- 작성자 입력 -->
		<td><input type="text" class="form-control" id="board_url" name="board_url" ></td>
	</tr>
    <tr>
		<th>url</th>
		<td><input type="text" class="form-control" id="board_thumN" name="board_thum" ></td>
	</tr>
</table>
		<button id="board_add" >등록</button>
		<button id="board_cancel">취소</button>
	</form>
</body>
</html>