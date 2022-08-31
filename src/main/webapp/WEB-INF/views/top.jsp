<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/includeFile.jsp"%>
<!-- 세션값 저장 -->
<%@ page import = "com.prac.utube.vo.Member" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/resources/css/home.css" rel="stylesheet">
</head>
<script src="${path}/resources/js/top.js"></script>
<body>
	<input id="useremail" type="hidden" value="${sessionScope.user_email}">
	<div id="topmenu" style="display: flex; float:left;" align="center" class="header" >
		
			<div class="logo">  
				<img class="logoimg" alt="none" src="${path}/resources/img/logo.png"  onclick="location.href='http://localhost:8090/utube/'" >
			</div>
			<div class="search">	
				<input style="width: 300px;"type="text" id="serchData" class="searchData" placeholder="검색어 입력">
				<a><img class="searchBtn" id="searchBtn" alt="none" src="${path}/resources/img/searchlogo.png"></a>
			</div>	
	
		<br>
		
		
			<div class="loginfrm" >
				<c:if test="${sessionScope.user_email == null}">
				
					<button id="signUp" class="signUp">가입하기</button>
					<input type="text" name="user_email" class="user_email" placeholder="아이디"> 
					<input type="password" name="user_password"	class="user_password" placeholder="비밀번호"> 
					<button id="login" class="login">로그인</button>
				</c:if>	
			
				
				<c:if test="${sessionScope.user_email != null}">
					<div>
						<img class="addvideo" id="write" alt="이미지없음" src="${path}/resources/img/addVideo.png">
						<img class="userImg" alt="이미지없음" src="${path}/savedir/thum/${sessionScope.user_img}">	
						<p id="user_email"> 삭제 할 내용 - ${sessionScope.user_name} </p>
						<button id="addSub">수기 구독 추가</button>
						<button id="logout" class="logout">로그아웃</button>
					</div>
				</c:if>
			</div>
		
	</div>
	
</body>
</html>