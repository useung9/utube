<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/includeFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
<link href="${path}/resources/css/home.css" rel="stylesheet">
<script src="${path}/resources/js/home.js"></script>

<body>
		
	<input type="hidden" value="${path}" id="path">
	<%@ include file="./top.jsp"%>
	
	<div style="display: flex;  width: 100%;">
		<div>
				<c:if test="${sessionScope.user_auth == 3}"> 
						<button class="boardList">게시리스트</button>
						<button class="userList">유저목록</button>
				</c:if>
				
				<div class="UserSub">
					<input type="hidden" value="${user_email}" id="email">
				</div>
		</div>
		

		<div class="maincontent">
			sublist
		</div>

	
	</div>
<div >

</div>
</body>
</html>