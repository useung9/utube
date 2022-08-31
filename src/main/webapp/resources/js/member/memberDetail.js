/* ** 
function pwinput() {
	// 비밀번호 입력창 div  
	
	if($("#pwChange").text()=='비밀번호변경'){
		$(".pwbox").append(
			"<div class=pwdiv>"+
			"<label id=pswd1>비밀번호 </label>"+
			"<input type=password class=form-control id=user_pswd1 name=user_password>"+
			"<label id=pswd2>재확인 비밀번호</label>"+
			"<label id=viewPW style=float: right; >보이기 / 감추기</label>"+
			"<input type=password class=form-control id=user_pswd2 >"+
			"<div>"
		);
		$("#pwChange").text('취소');
		
	}else {
		$("#pwChange").text('비밀번호변경');
		$(".pwbox").remove();
		
	}
	
	
}

function passwordCheck() {
	// 비밀번호 들어오면.
	var pswd1 = '';
	var pswd2 = '';
	$("#user_pswd1").change(function() {
		pswd1 = $("#user_pswd1").val();

	});

	$("#user_pswd2").change(function() {
		$("label[id='pswd2']").text('재확인 비밀번호 ');
		$("label[id='pswd2']").css("color", "black");
		pswd2 = $("#user_pswd2").val();


		if (pswd1 == pswd2) {
			$("label[id='pswd2']").text('일치 ');
			$("label[id='pswd2']").css("color", "green");
			$("label[id='pswd2']").css("font-size", "15px");
			$("label[id='pswd2']").css("font-weight", "bold");
		}
		// 비밀번호 일치 하지않을 경우
		else {
			$("label[id='pswd2']").text('비밀번호가 일치하지않습니다. ');
			$("label[id='pswd2']").css("color", "red");
			$("label[id='pswd2']").css("font-size", "15px");
			$("label[id='pswd2']").css("font-weight", "bold")
			$("user_pswd2").focus();
			
			alert("flash 값: "+msg);
		}
	})
}
 
	
 $(document).ready(function(){
	// 비밀번호 변경키 클릭
	
	
	// 확인버튼
	$("#updateBtn").click(function(){
		$("#userUpdate").submit();
	})
	
	// 취소버튼
	$("#cancelBtn").click(function(){
		location.href = "http://localhost:8090/utube";
	})
});*/


/**
 * 
 */




function check() {
	alert('체크 함수 실행');
	$('input').each(function() {

		if ($(this).id == 'user_Email') {
			alert('이름을 다시 입력해주세요.');
			$(this).val('');
			return;
		}
		$('#userUpdate').submit();
	});
}


function passwordCheck() {
	// 비밀번호 들어오면.
	var pswd1 = '';
	var pswd2 = '';
	$("#user_pswd1").change(function() {
		pswd1 = $("#user_pswd1").val();

	});

	$("#user_pswd2").change(function() {
		$("label[id='pswd2']").text('재확인 비밀번호 ');
		$("label[id='pswd2']").css("color", "black");
		pswd2 = $("#user_pswd2").val();


		if (pswd1 == pswd2) {
			$("label[id='pswd2']").text('일치 ');
			$("label[id='pswd2']").css("color", "green");
			$("label[id='pswd2']").css("font-size", "15px");
			$("label[id='pswd2']").css("font-weight", "bold");
		}
		// 비밀번호 일치 하지않을 경우
		else {
			$("label[id='pswd2']").text('비밀번호가 일치하지않습니다. ');
			$("label[id='pswd2']").css("color", "red");
			$("label[id='pswd2']").css("font-size", "15px");
			$("label[id='pswd2']").css("font-weight", "bold")
			$("user_pswd2").focus();

			alert("flash 값: " + msg);
		}
	})
}



$(document).ready(function() {
	// 비밀번호 확인
	passwordCheck();
	// 회원가입 버튼 클릭

	$("#updateBtn").click(function() {
		check();
	})
	$("#cancelBtn").click(function() {
		updateCancel();
	})


	// 회원 탈퇴
	$("#delUser").click(function() {
		userDel();
	});
});



function updateCancel() {

	var email = $("#user_Email").val();
	var pw1 = $("#user_pswd1").val();
	var pw2 = $("#user_pswd2").val();
	var name = $("#user_name").val();
	console.log(email + pw1 + pw2 + name);
	var result = confirm('수정 취소하시겠습니까>?');
	if (result) {
		location.href = "http://localhost:8090/utube";
	}

};

// 회원탈퇴.
function userDel() {
	var user_email = $("#detail_email").val();
	console.log("아이디 : " + $("#detail_email").val() +
		" / 이름" + $("#user_name").val());
	// 회원 탈퇴 ajax 
	var result = confirm("탈퇴 하시겠습니까?");
	if (result) {
		$.ajax({
			url: "http://localhost:8090/utube/member/deleteUser",
			type: "POST",
			data: {
				'user_email': user_email
			},
			success: function(data) {
				console.log(data);
				
			
			},
			error: console.error()

		});
	}
}


