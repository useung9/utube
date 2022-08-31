// 초기작업
function set() {
	$(".replyfooter").hide();
	$(".reReplyBody").remove();
}

// 로그인이되어있을때.
function expressLikeclick() {
	// db 좋아요 증가.
	console.log('기본값 : ' + $("#likebtn").attr('src'));
	var cnt;
	var boardkey = $("#boardkey").val();

	// 만약 싫어요가 되어있다면.
	if ($("#dislikebtn").attr('src').indexOf('dislike_after') > 0) {
		console.log("싫어요 버튼이 before != -1 => 싫어요 ")
		cnt = 2;
	}
	// 만약 기본값이라면.
	else if ($("#likebtn").attr('src').indexOf('like_before') != -1) {
		console.log($("#likebtn").attr('src').indexOf('like_before') + " 만약 like 전 상태라면");
		cnt = 1;

	}
	// 좋아요 버튼이 클릭이 되어있는 상태라면. 
	else {
		console.log($("#likebtn").attr('src').indexOf('like_after') + " 만약 like  후  상태라면");
		cnt = -1;
	}
	console.log('접속 정보 : ' + $("#user_email").val() + "\n cnt : " + cnt + "\nboardkey : " + boardkey);
	$.ajax({
		url: "http://localhost:8090/utube/board/boardLikes",
		type: "GET",
		data: {
			"cnt": cnt,
			"boardkey": boardkey,
			"user_email": $("#user_email").val()
		},
		success: function(data) {
			console.log("Board 정보가 담겨나와야한다. " + data);
			console.log("view : " + data['board_views']);
			console.log("board_likes   : " + data['board_likes']);
			$("#viewsCnt").text(data['board_views']);
			$("#likescnt").text(data['board_likes']);
			$("#dislikescnt").text(data['board_dislikes']);
			// cnt 1 == 좋아요 클릭 전. 
			if (cnt == 1) {
				$("#dislikebtn").attr("src", "/utube/resources/img/dislike_before.png");
				$("#likebtn").attr("src", "/utube/resources/img/like_after.png");
				// 클릭전 으로.
				// 싫어요버튼이  눌려있는 상태 
			} else if (cnt == 2) {
				$("#dislikebtn").attr("src", "/utube/resources/img/dislike_before.png");
				$("#likebtn").attr("src", "/utube/resources/img/like_after.png");
			} else {
				$("#likebtn").attr("src", "/utube/resources/img/like_before.png");
			}
		}
	});
}

//싫어요. 클릭
function expressDisLikeclick() {
	// db 좋아요 증가.
	console.log('기본값 : ' + $("#dislikebtn").attr('src'));
	var cnt;
	var boardkey = $("#boardkey").val();

	// 만약 좋아요가 되어있다면.
	if ($("#likebtn").attr('src').indexOf('like_after') > 0) {
		console.log($("#likebtn").attr('src').indexOf('like_after') != -1 + " / cnt == 2 좋아요가 되어있을때");
		cnt = 2;
	}
	// 만약 기본값이라면.
	else if ($("#dislikebtn").attr('src').indexOf('dislike_before') != -1) {
		console.log($("#dislikebtn").attr('src').indexOf('dislike_before') + " 만약 like 전 상태라면");
		cnt = 1;

	}
	// 싫어요 버튼이 클릭이 되어있는 상태라면. 
	else if ($("#dislikebtn").attr('src').indexOf('dislike_after') != -1) {
		console.log($("#dislikebtn").attr('src').indexOf('dislike_after') + " 만약 like  후  상태라면");
		cnt = -1;
	}
	console.log('접속 정보 : ' + $("#user_email").val())
	$.ajax({
		url: "http://localhost:8090/utube/board/boardDisLikes",
		type: "GET",
		data: {
			"cnt": cnt,
			"boardkey": boardkey,
			"user_email": $("#user_email").val()
		},
		success: function(data) {
			console.log("Board 정보가 담겨나와야한다. " + data);
			console.log("view : " + data['board_views']);
			console.log("board_likes   : " + data['board_likes']);
			$("#viewsCnt").text(data['board_views']);
			$("#likescnt").text(data['board_likes']);
			$("#dislikescnt").text(data['board_dislikes']);
			// cnt 1 == 싫어요 클릭 전. 
			if (cnt == 1) {
				$("#likebtn").attr("src", "/utube/resources/img/like_before.png");
				$("#dislikebtn").attr("src", "/utube/resources/img/dislike_after.png");
				// 클릭전 으로.

			} else if (cnt == 2) {
				$("#likebtn").attr("src", "/utube/resources/img/like_before.png");
				$("#dislikebtn").attr("src", "/utube/resources/img/dislike_after.png");
			} else {
				$("#dislikebtn").attr("src", "/utube/resources/img/dislike_before.png");
			}
		},
		error: console.error()
	});
}



// 계정별 좋아요 싫어요 정보확인
function expressionCheck() {
	var resultData = [];
	$.ajax({
		url: "http://localhost:8090/utube/board/expressionCheck",
		type: "GET",
		data: {
			"boardidx": $("#boardkey").val(),
			"user_email": $("#user_email").val()
		},
		success: function(data) {
			resultData = data
			console.log("성공 " + data);
			console.log("표현 값 : " + resultData['user_expression']);
			console.log("1이면 좋아요, 0이면 싫어요.");
			var expression = resultData['user_expression'];
			if (expression == 1) {
				$("#likebtn").attr("src", "/utube/resources/img/like_after.png");
			} else if (expression == 0) {
				$("#dislikebtn").attr("src", "/utube/resources/img/dislike_after.png");
			}
		},
		error: console.error()
	});
}

/**
 * 
 */


// 댓글 리스트업 (게시물번호 조회)
var replyListUp = function() {
	var resultData = [];
	var boardidx = $("#boardkey").val();
	$.ajax({
		url: "http://localhost:8090/utube/board/board_ReplyList",
		type: "GET",
		data: {
			'boardidx': boardidx
		},
		success: function(data) {
			resultData = data;
			console.log("받아온 데이터 : " + data + " / " + resultData);
			/*console.log("data["+i+"]idx : "+data[i].board_idx+
								"data["+i+"]writer : "+data[i].reply_writer+
								"data["+i+"]content : "+data[i].reply_content
			);*/
			console.log("result Data : " + resultData);
			$.each(resultData, function(key, value) {

				console.log("key : " + data[key].REPLY_REGIDATE + "content" + data[key].REPLY_CONTENT);


			});
			if (data.length == 0) {
				$(".replyList").append(
					"<p class=nonReply>등록된 댓글이 없습니다.</p>"
				);
			} else {
				var text = "";
				text += "<div class=card>";
				$.each(resultData, function(key, value) {
					if (resultData[key].REPLY_RELEVEL == 0) {
						text +=
							"<div class=media mt-3>" +
							"<a class=pr-3 href=http://localhost:8090/utube/member/detail>" +
								"<img  style='width:60px; height:60px;'class=rounded-circle alt=Bootstrap Media Another Preview src=http://localhost:8090/utube/savedir/" + resultData[key].USER_IMG + ">"+
							"</a>"
							
					} else {
						text +=
							"<div class=media mt-3  style= margin-left:30px;>" +
							"<a class=pr-3 href=http://localhost:8090/utube/member/detail>" +

							"<img  style='width:30px; height:30px;' class=rounded-circle alt=Bootstrap Media Another Preview src=http://localhost:8090/utube/savedir/" + resultData[key].USER_IMG + ">"+
							"</a>"
					}
					console.log("key : " + resultData[key].REPLY_REGIDATE + "content" + resultData[key].REPLY_CONTENT);
					text +=


						"<div class=media-body>" +
						"<div class=row>" +
						"<div class=col-12 d-flex>" +
						"<h5>" + resultData[key].REPLY_WRITER + "</h5>" +
						"<span>" + resultData[key].USER_NAME + "</span>" +
						"</div>" +
						"</div>" +
						"<p id=content>" + resultData[key].REPLY_CONTENT + "</p>" +
						"<div id=reReplyBtn style=margin:7px;>" +
						"<input class=level type=hidden id=level value=" + resultData[key].REPLY_RELEVEL + ">" +
						"<input class=step type=hidden id=step value=" + resultData[key].REPLY_RESTEP + ">" +
						"<input class=idx type=hidden id=idx value=" + resultData[key].REPLY_IDX + ">" +
						"좋아요" +
						"싫어요" +
						"<button type=button id=reReplyAdd class=btn btn-outline-secondary btn-icon-text>" +
						"댓글" +
						"<i class=fa fa-pencil btn-icon-append></i>" +
						"</button>" +
						"</div>" +
						"<div class=replylist id=replylist></div>" +
						"</div>" +
						"</div>";

				});
				text += "</div>";
				$(".replyList").empty();
				$(".replyList").append(
					text
				);
			}
		},
		error: console.error()
	});
}

// 댓글 등록버튼
$(document).on("click", "button[id='reReplyAdd']", function(e) {
	// 바로 위 부모 가서 부모의 값 읽어오기.
	var $tmp = $(this).closest("div").find('input[type=hidden]');

	var idx = $(this).closest("div").children('#idx').val();
	var level = $(this).closest("div").children('#level').val();
	var step = $(this).closest("div").children('#step').val();

	alert("idx = :" + idx + "\n level : " + level + "\n step" + step);


	$.each($tmp, function(idx, item) {
		console.log("idx : " + (idx + 1) + " / value = " + item);
	});


	//reReplyBody
	//$("#reReplyBtn[]").append(
	// 
	$(this).closest("div").append(
		"<div class=reReplyBody>" +
		"<input id=inputreReply type=text class=form-control placeholder=댓글 추가..>" +
		"<div class=reReplyFooter style=float: right;>" +
		"<button id=reReplyCancel>취소</button>" +
		"<button id=reReplyPost>등록</button>" +
		"<input type=hidden id=level value=" + level + ">" +
		"<input type=hidden id=step value=" + step + ">" +
		"<input type=hidden id=idx value=" + idx + ">" +
		"</div>" +
		"</div>"
	);


});



// 대댓글 등록 
$(document).on("click", "button[id='reReplyPost']", function() {
	var content = $("#inputreReply").val();
	if ($("#inputreReply").val().length > 0) {
		// 부모 값 
		var idx = $(this).closest("div").children('#idx').val();
		var level = $(this).closest("div").children('#level').val();
		var restep = $(this).closest("div").children('#step').val();
		var levelint = parseInt(level + 1);
		var resultlevel = 0;
		console.log('대댓글 등록 클릭 level' + parseInt(level + 1) + ' / step : ' + parseInt(step + 1) + ' / idx' + idx);
		//alert("$(this).parents() : " + $(this).parents());
		//$(this).parents("#replylist").append(
		//find(".media-body").


		$.ajax({
			url: "http://localhost:8090/utube/board/board_maxRelevel",
			type: "GET",
			dataType: "json",
			data: {
				'board_idx': $("#boardkey").val()
			},
			success: function(data) {
				console.log(data);
				resultlevel = data;

			},
			error: console.error()
		});

	


		//$(".reReplyBody").empty();
		var boardidx = $("#boardkey").val();
		var writer = $("#user_email").val();

		$.ajax({
			url: "http://localhost:8090/utube/board/board_reReplyAdd",
			type: "POST",
			dataType: "json",
			data: {
				"boardidx": boardidx,
				"writer": writer,
				"content": content,
				"restep": restep,
				"level": parseInt(resultlevel + 1)
			},
			success: function(data) {
				console.log("ajax 답글 등록 성공" + data);


			},
			error: console.error()

		});
		
			$(this).parents(".media-body").children(".replylist").append(
			"<div class=reReply style=margin-left:30px; > " +
			"<div id=reReplyWriter>" + $("#user_email").val() + "</div>" +
			"<p id=reContent>" + $("#inputreReply").val() + "</p>" +
			"<input type=hidden id=level value=" + parseInt(resultlevel + 1) + ">" +
			"<input type=hidden id=step value=" + restep + ">" +
			"<input type=hidden id=idx value=" + idx + ">" +
			"좋아요 / 싫어요" +
			"<button class=reReliseAdd>답글</button>" +
			"</div>"
		);
	}
	else {
		alert("1글자 이상 등록해주세요.");

	}
});

// 대댓글 취소 
$(document).on("click", "button[id='reReplyCancel']", function() {
	alert("대댓글 취소버튼");
	$(".reReplyBody").remove();
});


// 댓글 추가버튼 클릭(부모 : step, level  = 1)
var replyAdd = function() {
	// ajax 데이터 보내는곳
	// 게시물 번호, 작성자, 내용
	var boardidx = $("#boardkey").val();
	var writer = $("#user_email").val();
	var content = $("#inputReply").val();
	var sendData = {
		"boardidx": boardidx,
		"writer": writer,
		"content": content,
		"level": 0
	}

	$.ajax({
		url: "http://localhost:8090/utube/board/board_ReplyAdd",
		type: "POST",
		dataType: "json",
		async: false, // 비동기 -> 동기
		data: sendData
		,
		success: function(data) {
			if (data == 1) {
				set();
				replyListUp();
				$("#inputReply").val('');
			} else {
				alert("등록시오류 발생 다시 시도해주세요.");
				set();
				$("#inputReply").val('');
				replyListUp();
			}


		},
		error: console.error()
	});
}

var allList = function() {
	var resultData = [];
	var text = "";
	$.ajax({
		url: "http://localhost:8090/utube/board/board_DataList",
		type: "GET",
		dataType: "json",
		success: function(data) {
			resultData = data;
			console.log("==========================================" + resultData + " / length :" + resultData.length);
			$.each(resultData, function(key, value) {
				console.log("reData : " + resultData[key]);
			});
			if (resultData.length > 0) {
				$('.alldata').append(
					"<ul>"
				);
				for (var i = 0; i < resultData.length; i++) {
					$('.alldata').append(
						"<li>" +
						"<div class=" + "album-item" + ">" +
						"<div class=" + "album-cover" + ">" +
						//이미지 클릭시 detail 페이지 이동
						"<a href=" + "board/boardDetail?boardkey=" + resultData[i].BOARD_IDX + ">" +
						// 이미지 링크 변경할것 영상등록할때 비디오 html 말고 영상 링크 가져올것.
						"<img id=thumimg class=" + "'album-img" + resultData[i].BOARD_IDX + "'" +
						"alt='불러오기 실패' src=" + "https://img.youtube.com/vi/" + resultData[i].BOARD_THUM + "/mqdefault.jpg>" +

						//"<div class=" + "album-info" + ">" +
						"<div style=display:flex; id=info>" +
						"<div>" +
						"<img id=board_thum alt='표시할수없음' src=http://localhost:8090/utube/savedir/thum/" + resultData[i].USER_IMG + ">" +
						"</div>" +
						"<div id=contentinfo>" +
						"<p class=title id=album-title" + i + ">" + resultData[i].BOARD_TITLE + "</p>" +
						"<p class=subinfo id=boardwriter>" + resultData[i].USER_NAME + "</p><br>" +
						"<p class=subinfo id=views>조회수   " + resultData[i].BOARD_VIEWS + "</p><br>" +
						"<p class=subinfo id=regidate>" + resultData[i].BOARD_REGIDATE + "</p>" +
						"</div>" +
						"</div>" +
						"</a>" +
						"</div>" +
						"</div>" +
						"</li>"
					);

				}
				$('.alldata').append("</ul>");
			} else {
				$(".alldata").append(
					"<h5>등록데이터 없음</h5>"
				);
			}
		},
		error: console.error()

	});
}
$(document).ready(function() {
	// 초기화 목록  댓글
	set();
	// 댓글 리스트업
	replyListUp();
	// 오른쪽 전체 리스트
	allList();
	// 초기상태에서 좋아요 클릭시 색변화 
	/*
		1. 현 게시물에대한 계정의 좋아요 상태 확인
		2. 데이터 있으면 flag 값에 맞게끔 싫어요와 좋아요 표시.
	*/


	if ($("#user_email").val() != '' && $("#user_email").val() != 'null' || $("#user_email").val().length != 0) {
		console.log("로그인이 되어있어서 계정 표현 정보 가져오기");
		// 로그인 되어있을때만 가능하게 설정할것 .접속 계정 expression(좋아요 싫어요 여부확인)
		expressionCheck();
	} else {
		console.log("로그인되어있지않음 안가져옴");
	}








	// 댓글 입력하기
	$("#inputReply").click(function() {
		$(".replyfooter").show();

	});



	// 등록버튼 클릭
	$("#replyAdd").click(function() {
		replyAdd();

	});

	// 취소버튼 클릭시 버튼 hide
	$("#replyCancel").click(function() {
		$("#inputReply").val("");
		set();
	});


	// 좋아요
	$("#likes").click(function() {

		if ($("#user_email").val() != '' && $("#user_email").val() != 'null' || $("#user_email").val().length != 0) {
			console.log("로그인이 되어있어서 계정 표현 정보 가져오기");
			// 로그인 되어있을때만 가능하게 설정할것 .접속 계정 expression(좋아요 싫어요 여부확인)

			expressLikeclick();
		} else {
			alert("로그인 이후 가능합니다.");
			return;
		}
	});

	// 싫어요.
	$("#dislikes").click(function() {
		if ($("#user_email").val() != '' && $("#user_email").val() != 'null' || $("#user_email").val().length != 0) {
			expressDisLikeclick();
		}
		else {
			alert("로그인 이후 가능합니다.");
			return;
		}
	});


});
