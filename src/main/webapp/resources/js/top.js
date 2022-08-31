/**
 * 
 */

$(document).ready(function() {

	// 작성
	$("#write").click(function() {
		location.href = "http://localhost:8090/utube/board/addboard";
	});
	// 로그인 버튼
	var code = '';
	var msg = '';
	var name = '';
	// 로그인 버튼
	$('.login').click(function() {
		var user_email = $('.user_email').val();
		var user_password = $('.user_password').val();

		// 이메일 여부 확인용
		var check = user_email.indexOf('@');
		if (user_email == "" || check == -1) {
			alert('아이디를 다시 입력해주세요.');
			$('.user_email').val('');
			$('.user_password').val('');
			return;
		}

		if (user_password == "") {
			alert('아이디를 다시 입력해주세요.');
			$('.user_email').val('');
			$('.user_password').val('');

			return;
		}

		$.ajax({
			url: "http://localhost:8090/utube/member/login",
			type: "GET",
			data: {
				"user_email": user_email,
				"user_password": user_password
			},
			dataType: "json",
			success: function(data) {
				$.each(data, function(key, value) {
					if (key == "msg") {
						msg = value;
					}
					if (key == "code") {
						code = value;
					}
					if (key == "name") {
						name == value;
					}
				});

				if (code == "1") {
					/*	// 로그인성공
						alert("로그인 성공");
							$(".loginfrm").hide();
							$(".logStatus").html('<h5>'+name+' 님</h5>');
							$(".logStatus").html('<button class="logout"> 로그아웃</button>');
							*/
					// 
					location.reload();
				}
				else {
					alert(msg);
					$('.user_email').val('');
					$('.user_password').val('');
				}

			},
			error: function(e) {
				alert("error" + e)
			}

		})

	});
	// 로그아웃 버튼
	$('.logout').click(function() {
		if (confirm('로그아웃 하시겠습니까?')) {
			location.href = "http://localhost:8090/utube/member/logout";
		}


	});

	// 가입버튼
	$('.signUp').click(function() {
		location.href = "http://localhost:8090/utube/signup";
	});


	// 구독 추가하기
	$('#addSub').click(function() {
		location.href = "http://localhost:8090/utube/member/addsub";
	});

	$('.userImg').click(function() {
		location.href = "http://localhost:8090/utube/member/detail";
	});


	// 검색 버튼 클릭
	$(".searchBtn").click(function() {
		var inputData = $("#serchData").val();
		alert(inputData);
		// 숨기기
		var resultData ;
		$(".maincontent").empty();
		
	var boardresult = [];
	var result;
	$.ajax({
		url: "board/board_SearchData",
		type: "GET",
		data: { "inputData": inputData },
		success: function(data) {
			boardresult = data;
			console.log(boardresult);
			$.each(data, function(key, value) {

				console.log('key : '+data[key].BOARD_WRITER);
				console.log(data.length);


			});
			
			$("#serchData").val(inputData);
			$('.maincontent').append(
				"<ul>"
			);

			for (var i = 0; i < boardresult.length; i++) {
				
				//var title = boardresult[i].board_title.length<50?
				var board_title = boardresult[i].board_title;
				/*if (boardresult[i].board_title.length > 20) {
					board_title = boardresult[i].board_title.substring(0, 50);
					console.log(board_title);
					board_title += '..';
				}*/

				$('.maincontent').append(
					
					"<li id=Searchli>" +
						"<div class=" + "Search-item" + ">" +
							"<div class=" + "Search-cover" + ">" +
							//이미지 클릭시 detail 페이지 이동
								"<a href=" + "board/boardDetail?boardkey=" + boardresult[i].BOARD_IDX + ">" +
								// 이미지 링크 변경할것 영상등록할때 비디오 html 말고 영상 링크 가져올것.
									"<img id=thumimg class=" + "'album-img" + boardresult[i].BOARD_IDX + "'" + 
										"alt='불러오기 실패' src=" + "https://img.youtube.com/vi/" + boardresult[i].BOARD_THUM + "/mqdefault.jpg>" +
									//"<div class=" + "album-info" + ">" +
								"</a>" +
								
							"<div id=Search-info>"+
										"<a href=" + "board/boardDetail?boardkey=" + boardresult[i].BOARD_IDX + ">"+
										"<div id=contentinfo>"+	
											"<p class=title id=album-title" + i + ">" + boardresult[i].BOARD_TITLE + "</p>" +
											"<p class=subinfo id=views>조회수   "  +boardresult[i].BOARD_VIEWS+" </p> * "+
											"<p class=subinfo id=regidate>등록일자  "+boardresult[i].BOARD_REGIDATE+"</p><br>"+
												"<div clss=Search-data>"+
													"<img id=Search_thum alt='표시할수없음' src=http://localhost:8090/utube/savedir/thum/"+boardresult[i].USER_IMG+">    - "+
													"<p class=subinfo id=boardwriter>"+boardresult[i].BOARD_WRITER+"</p><br>"+
												"</div>"+
											"<p>"+boardresult[i].BOARD_CONTENT+"</p>"+
										"</div>"+
										"</a>"+
									"</div>"+
							"</div>" +
						"</div>"+
					"</li>"
				);

				$('.maincontent').append("</ul>");
			}
		}

	
	});
	
	});

	 $("#serchData").keydown(function (key) {
        if (key.keyCode == 13) {
         $(".searchBtn").click();
        }
    });

})

