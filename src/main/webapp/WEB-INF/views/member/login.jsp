<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="${path}/resources/css/styles.css">
 <link href="https://fonts.googleapis.com/earlyaccess/notosanskr.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div class="login-form">
    <form>
      <input type="text" name="email" class="text-field" placeholder="아이디">
      <input type="password" name="password" class="text-field" placeholder="비밀번호">
      <input type="submit" value="로그인" class="submit-btn">
    </form>
 
    <div class="links">
      <a href="#">비밀번호를 잊어버리셨나요?</a>
    </div>
  </div>
</body>
</html>