/**
 * 
 */


$(document).ready(function() {
	// 전체 영상리스트 출력
	videolist();

	var user_email = $("#email").val();
	console.log(user_email);

	var resultData = [];
	// 계정 구독 리스트 출력
	if (user_email != 'null' && user_email != '') {
		$.ajax({
			url: "./sublist",
			type: "GET",
			data: { "user_email": user_email },

			success: function(data) {
				console.log(data);
				// 데이터 저장
				resultData = data;
				console.log("길이 : " + resultData.length);
				$.each(data, function(key, value) {

					console.log(data[key]);


				});
				console.log("resultData size : " + resultData.length);

				if (resultData != null && resultData != "") {
					$('.UserSub').append(
						"<hr>" +
						/*"<thead>" +
						"<tr>" +
						"<td>이미지</td>" +
						"<td>링크</td>" +
						"</tr>" +
						"</thead>" +*/
						"<tbody>"

					);


					var listText = "";
					for (var i = 0; i < resultData.length; i++) {
						var trText = "<tr>";
						trText += "<td><img class=sublmg alt=불러오기 실패 src=http://localhost:8090/utube/savedir/" + resultData[i].sub_subimg + "></td>";
						trText += "<td><a href=" + resultData[i].sub_suburl + ">" + resultData[i].sub_subname + "</td>";
						trText += "</tr>";

						listText += trText;
					}
					$('.UserSub').append(listText);
					$('.UserSub').append("</tbody>");
				}
				else {

					$('.UserSub').append(
						"<h2> 구독한 정보가없습니다. 구독을 해주세요</h2>"
					);

				}
			},
			error: console.error()
		});


	} else {
		$('.UserSub').hide();
		/*$('.UserSub').append(
					"<h2> 로그인해주세요</h2>"
					);*/
	}





	

	/*	 동적 생성된 애들 잡아서 클릭할때
	$(document).on("click",".album-img",function(){
			alert()
			
		})*/

	// 게시리스트
	$(".boardList").click(function() {
		// maincontent hide();
		// 리스트 불러오기
		$(".maincontent").empty();
		boardlist();
	});

	// 유저목록
	$(".userList").click(function() {
		$(".maincontent").empty();
		userList();
	})

	$(document).on("click", ".delBoard", function() {

		/*$(".boardlist").find("input:checkbox[name='idx']").each(function(){
				
				console.log($(this).attr('id'));
		});*/
		$("input[name=idx]:checked").each(function() {
			var test = $(this).val();
			console.log(test);
			console.log('id (id= pk): ' + $(this).attr('id'))
			
			// ajax 게시판 삭제 시키기
		});
	});

	$(document).on("click", ".delMember",function(){
		$("input[name=idx]:checked").each(function() {
			var test = $(this).val();
			console.log(test);
			console.log('value : ' + $(this).attr('value'));
			
			// ajax 사용자 목록 삭제
		});
	})

});
// 게시판 목록
function boardlist() {
	var resultData = [];
	$.ajax({
		url: "http://localhost:8090/utube/board/boardList",
		type: "GET",
		dataType: "json",
		success: function(data) {
			console.log(data);
			resultData = data;
			console.log("길이 : " + resultData.length);
			if (resultData.length > 0) {
				$(".maincontent").append(
					"<table class=boardlist style=text-align: center>" +

					"<tr>" +
					"<th></th>" +
					"<th>번호</th>" +
					"<th>제목</th>" +
					"<th>작성자</th>" +
					"<th>작성날짜</th>" +
					"</tr>" +
					"<tbody>"

				);

				var appdata = "";
				for (var i = 0; i < resultData.length; i++) {

					var text = "<tr>" +
						"<td> <input type=checkbox name=idx id=idx" + resultData[i].BOARD_IDX + "></td>" +
						"<td>" + resultData[i].BOARD_IDX + "</td>" +
						"<td>" + resultData[i].BOARD_TITLE + "</td>" +
						"<td>" + resultData[i].BOARD_WRITER + "</td>" +
						"<td>" + resultData[i].BOARD_REGIDATE + "</td>"
					"</tr>";
					appdata += text;
				}
				$(".maincontent").append("</tbody>" + "</table>");

				$(".maincontent").append(appdata);
				$(".maincontent").append(
					"<button id=delBoard class=delUser>삭제</button>"
				);


			} else {
				$(".maincontent").append("<h5>등록된 게시물이 없습니다.</h5>");
			}
		},
		error: console.error
	});


};


// 사용자 목록
function userList() {
	var resultData = [];
	$.ajax({
		url: "http://localhost:8090/utube/member/memberList",
		type: "GET",
		dataType: "json",
		success: function(data) {
			console.log(data);
			resultData = data;
			console.log("길이 : " + resultData.length);
			if (resultData.length > 0) {
				$(".maincontent").append(
					"<table class=memberlist style=text-align: center>" +
					"<tr>" +
					"<th>  </th>"+
					"<th>이메일</th>" +
					"<th>이름</th>" +
					"<th>권한</th>" +
					"</tr>" +
					"<tbody>"


				);

				var appdata = "";
				for (var i = 0; i < resultData.length; i++) {
					console.log('for문 반복 횟수' + i);
					var text = "<tr>" +
						"<td> <input type=checkbox name=idx value="+resultData[i].USER_EMAIL+"></td>" +
						"<td><a href= http://localhost:8090/utube/member/detail?email="+resultData[i].USER_EMAIL+">" + resultData[i].USER_EMAIL + "</a></td>" +
						"<td>" + resultData[i].USER_NAME + "</td>" +
						"<td>" + resultData[i].USER_AUTH + "</td>" +
						"</tr>";
					appdata += text;
				}
				$(".maincontent").append("</tbody>" + "</table>");
				$(".maincontent").append(appdata);
				$(".maincontent").append(
					"<button id=delMember class=delMember>삭제</button>"
				);


			} else {
				$(".maincontent").append("<h5>등록된 게시물이 없습니다.</h5>");
			}


		},
		error: console.error
	});
};

//전체 리스트
function videolist() {
	var boardresult = [];
	var result;
	$.ajax({
		url: "board/getUserBoard",
		type: "GET",
		//data: { "user_email": user_email },
		success: function(data) {
			boardresult = data;
			console.log(boardresult);
			$.each(data, function(key, value) {

				console.log('key : '+data[key].BOARD_WRITER);
				console.log(data.length);


			});
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
					"<li>" +
					"<div class=" + "album-item" + ">" +
						"<div class=" + "album-cover" + ">" +
						//이미지 클릭시 detail 페이지 이동
						"<a href=" + "board/boardDetail?boardkey=" + boardresult[i].BOARD_IDX + ">" +
						// 이미지 링크 변경할것 영상등록할때 비디오 html 말고 영상 링크 가져올것.
							"<img id=thumimg class=" + "'album-img" + boardresult[i].BOARD_IDX + "'" + 
								"alt='불러오기 실패' src=" + "https://img.youtube.com/vi/" + boardresult[i].BOARD_THUM + "/mqdefault.jpg>" +
							
							//"<div class=" + "album-info" + ">" +
								"<div style=display:flex; id=info>"+
									"<div>"+
										"<img id=board_thum alt='표시할수없음' src=http://localhost:8090/utube/savedir/thum/"+boardresult[i].USER_IMG+">"+
									"</div>"+
									"<div id=contentinfo>"+	
										"<p class=title id=album-title" + i + ">" + boardresult[i].BOARD_TITLE + "</p>" +
										"<p class=subinfo id=boardwriter>"+boardresult[i].USER_NAME+"</p><br>"+
										"<p class=subinfo id=views>조회수   "  +boardresult[i].BOARD_VIEWS+"</p><br>"+
										"<p class=subinfo id=regidate>"+boardresult[i].BOARD_REGIDATE+"</p>"+
									"</div>"+
								"</div>"+
							"</a>" +	
						"</div>" +
						"</div>"+
					"</li>"
				);

				$('.maincontent').append("</ul>");
			}
		}


	});
}
