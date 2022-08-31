<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${path}/resources/js/member/addsub.js"></script>
<body>
		<%@ include file="../top.jsp" %>
아이디 + 구독페이지명 + 이미지 + 링크 등록

세션 값 읽어오기 아이디 : ${sessionScope.user_email}
	<form id="addsubfrm" action="${path}/member/addsub" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="sub_email" value="${sessionScope.user_email}">
	구독 페이지명 <input type="text" id="sub_subname" name="sub_subname">
	파일	<input type="file" name="file" id="file">
	페이지 링크 <input type="text" id="url"  name="sub_suburl">
		<button id="addSubBtn"> 등록</button>
	</form>
	
	
</body>
</html>