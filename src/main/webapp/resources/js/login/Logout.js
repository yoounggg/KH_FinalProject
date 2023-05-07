// 비동기식 로그아웃!
$("#gnb_logout").click(function() {
	$.ajax({
		type:'POST',
		url:'/login/logout',
		success:function(data){
			document.location.reload();
		}
	});
});