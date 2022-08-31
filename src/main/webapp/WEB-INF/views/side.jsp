<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/includeFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<script type="text/javascript">
$(document).ready(function(){
	 var uid = '<%=(String) session.getAttribute("user_email")%>';
	 console.log(uid);
		console.log('세션값 uid != null :'+uid != 'null' +'uid != '+uid != '');
	 	if (uid != 'null' && uid != '') {
			$.ajax({
				url : "./sublist",
				type : "GET",
				success:function(data){
					console.log(data);
			
				}
			});

		}
	});
</script>
<body>
	${sublist}
	<c:if test="${sublist  == null}">
	 갑섮음
	</c:if>

</body>
</html>