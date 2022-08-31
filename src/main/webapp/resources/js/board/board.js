/**
 * 
 */
 
 
 $(document).ready(function(){
	// 등록 버튼 클릭시
	$("#board_add").click(function(e){
		e.preventDefault();
		$("#board_writer").val();
		$("#board_subject").val();
		$("#board_content").val();
		$("#board_url").val();
	if($("#board_subject").val() == ""){
		alert('제목을 다시 입력해주세요. ');
		$("#board_subject").text('');
		$("#board_subject").focus();
		return ;
	}
	if($("#board_content").val() == ""){
		alert('내용을 다시 입력해주세요. ');
		$("#board_content").text('');
		$("#board_content").focus();
		return ;
	}
	
	if($("#board_url").val() == ""){
		alert('경로 다시 입력해주세요. ');
		$("#board_url").text('');
		$("#board_url").focus();
		return ;
	}
	
	if($("#board_thumN").val() == ""){
		alert("URL 다시입력햊쉐요.")
		$("#board_thumN").text('');
		$("#board_thumN").focus();
		return ;
	}
	
	
	alert('세션 이메일값 :'+'${user_email}'+'작성자 : ' + $("#board_writer").val()+$("#board_subject").val()+$("#board_content").val()+$("#board_url").val());
		
		
		$("#boardFrm").submit();
	});
	
});
	
