/**
 * 
 */
 
 
 $(document).ready(function(){
	
	$("#addSubBtn").click(function(e){
		
		e.preventDefault();
		
		if($("#sub_subname").val() == ""){
			alert("구독할 대상을 다시 입력해주세요.");
			$("#sub_subname").val('');
			return ;
		} 
		if($("#sub_suburl").val() == ""){
			alert("구독할 대상 URL 다시입력해주세요.");
			$("#sub_suburl").text('');
			return ;
		} 
		
		if($("#file").val() == "" ){
			alert("파일이 올바르지않습니다 다시입력해주세요.");
			$("#file").val('');
			return ;
		}
		
		$("#addsubfrm").submit();
	});
	

});