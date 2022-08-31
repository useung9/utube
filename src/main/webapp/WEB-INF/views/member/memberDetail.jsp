<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${path}/resources/js/member/memberDetail.js"></script>
<script src="${path}/resources/js/home.js"></script>
<link href="${path}/resources/css/home.css" rel="stylesheet">
</head>
<body>
	<div>
		<%@ include file="../top.jsp" %>
	</div>
	
		개인정보 출력
		${userinfo}
	<div>
	<form action="${path}/member/memberUpdate" method="POST" id="userUpdate" enctype="multipart/form-data">
	    <img  id="detailimg" alt="등록된 이미지 없음" src="${path}/savedir/thum/${userinfo.user_img}">
	    <input type="file" name="file" id="file" >
		<br>
		
			<label>아이디</label>
			<input type="text" value="${userinfo.user_email}" name="user_email" readonly="readonly" id="detail_email">
			<hr>
			<label>이름</label>
			<input type="text" value="${userinfo.user_name}" name="user_name"  id="user_name">
			<br>
			<label id="pswd2">비밀번호</label>
			<input type="password" id="pswd1" name="user_password"><hr>
			<label id="pswd2">비밀번호 확인</label>
			<input type="password" id="pswd2">
			
			<hr>
			<label>등급</label>
			<c:choose>
				<c:when test="${sessionScope.user_auth == 3}">
					<select id="sel_userAuth" name="user_auth">
						<option 
						<c:if test="${userinfo.user_auth == 1}"> selected="selected"</c:if>>1</option>
						<option
						<c:if test="${userinfo.user_auth == 2}"> selected="selected"</c:if>>2</option>
						<option
						<c:if test="${userinfo.user_auth == 3}"> selected="selected"</c:if>>3</option>
					</select>
				</c:when>
				<c:otherwise>
					<input type="text" value="${userinfo.user_auth}" readonly="readonly">		
				</c:otherwise>
			</c:choose>
		</form>
		<button id="updateBtn">확인</button>
		<br>
		<button id="cancelBtn">취소</button>
		<button id="delUser">탈퇴</button>
	</div>
</body>
</html>