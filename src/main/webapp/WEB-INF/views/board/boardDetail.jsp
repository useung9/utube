<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/resources/css/style.css" rel="stylesheet">
<script src="${path}/resources/js/board/boardDetail.js" type="text/javascript"></script>
</head>
<body style="margin-left: 20px; ">
	<input type="hidden" id="boardkey" value="${board.board_idx}">
	<input type="hidden" id="user_email" value="${sessionScope.user_email}">
	<div class="header">
		<%@include file="../top.jsp"%>
	</div>
	<div style="display: flex">
		<div class="video">${board.board_url}
		<c:set var="userEmail" value="${sessionScope.user_email}" />
	
			<h4>${board.board_title}</h4>
				<div id="info" style="display: flex; flex-direction: row; align-items: center">
					조회수 	<p id="viewsCnt"> ${board.board_views} 회</p>
					등록일자 - <fmt:formatDate  pattern="yyyy.MM.dd" value="${board.board_regidate}" />
					
					<a id="likes">
						<img id="likebtn" class="icon" alt="" src="${path}/resources/img/like_before.png"></a>	
					<p id="likescnt"> 	${board.board_likes} </p>
					&nbsp;	
					<a id="dislikes">싫어요 - 
					<img id="dislikebtn" class="icon" alt="" src="${path}/resources/img/dislike_before.png"></a> 
					<p id="dislikescnt"> ${board.board_dislikes} </p>
					
					
				</div>
			<hr>
			<div id="content">
					${board.board_content}
			</div>
			
			<div>
			 	${board.board_writer}
			</div> 
			 ${board.board_content}
		
			<hr>
			<div class="reply_info" style="display: flex; ">
				댓글 정보  / 정렬  기준
			</div>
			<hr>
				<c:choose>
					<c:when test="${userEmail != null}">
						<div class="replybody">
				 		<input id="inputReply" type="text" class="form-control" placeholder="댓글 추가..">
						  <div class="replyfooter" style="float: right;">
							  <button id="replyCancel">취소</button>
							  <button id="replyAdd">등록</button>
						  </div>
						</div>
					</c:when>
					<c:otherwise>
						<div>로그인 후 이용 가능합니다.</div>
					</c:otherwise>
				</c:choose>
			<div class="replyList">
				
			</div>
		</div>
		
			<div class="alldata" style="border: 1px solid; flex-direction: column;">추천 데이터 리스트 (전체
			영상 목록 + 스크롤 페이징)
		</div>
	</div>
		
</body>
</html>