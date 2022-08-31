<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원가입</title>


<script type="text/javascript" src="${path}/resources/js/member/signup.js">	</script>

</head>
<body>
 <!-- 회원가입 -->
 	<div class="container">
 		<div class="join_context">
 		
 			<h4>이메일</h4>
 			<form  id="frmSignUp" action="${path}/member/signUp" method="post" enctype="multipart/form-data" >
 				<div class="form-group">
				    <label for="exampleInputEmail1">Email address</label>
				    <input type="email" class="form-control" id="user_Email" name="user_email" aria-describedby="emailHelp"  placeholder="name@example.com">
				    <label id="pswd1">비밀번호 </label>
				    <input type="password" class="form-control" id="user_pswd1" name="user_password">
				    <label id="pswd2">재확인 비밀번호</label>
				    <label id="viewPW" style="float: right;" >보이기 / 감추기</label>
				    <input type="password" class="form-control" id="user_pswd2" >
				    <label for="">이름</label>
				    <input type="text" class="form-control" id="user_name" name="user_name" >
				    <label>이미지</label>
				    <input type="file" id="file" name="file">
			  	</div>
 			</form>
			  	<button type="button" id="signUp"  class="btn btn-success">회원가입</button>
			  	<button type="button" id="signCancel"  class="btn btn-secondary" >취소</button>
 		</div>
 		
 	</div>
</body>
</html>