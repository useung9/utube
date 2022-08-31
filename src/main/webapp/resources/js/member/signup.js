/**
 * 
 */




function check() {
	alert('체크 함수 실행');
	if($("#file").val() == "" ){
		alert("이미지 등록해주세요");
		return;
	}
	$('input').each(function() {
		// 아이디 $(this).attr('id')
		// 값 $(this).val());
		if($(this).val() == null || $(this).val() == ''){
			if($(this).id == 'user_Email'){
				alert('이메일 다시입력해주세요.');
				$(this).val('');
					return;	
			}else if($(this).id == 'user_Email'){
				alert('이름을 다시 입력해주세요.');
				$(this).val('');
					return;
			}
		}
			$('#frmSignUp').submit();		
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
			
			alert("flash 값: "+msg);
		}
	})
}



$(document).ready(function() {
	// 비밀번호 확인
	passwordCheck();
	// 회원가입 버튼 클릭
	
	$("#signUp").click(function() {
	check();
	})
	$("#signCancel").click(function() {
		signCancel();
	})
	
	if("${msg}"){
		alert("${msg}");
	}
});



function signCancel() {

	var email = $("#user_Email").val();
	var pw1 = $("#user_pswd1").val();
	var pw2 = $("#user_pswd2").val();
	var name = $("#user_name").val();
	console.log(email + pw1 + pw2 + name);
	var result = confirm('회원가입을 취소하시겠습니까>?');
	if (result) {
		location.href = "http://localhost:8090/utube";
	}

};
