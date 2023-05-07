/*
$(document).ready(function() {

    // 로컬 스토리지에서 자동 로그인 상태 가져오기
    var autoLogin = localStorage.getItem("autoLogin");
    if (autoLogin === "on") {
        // 자동 로그인 상태를 체크박스에 체크 표시
        $("#autoLogin").prop("checked", true);
    }

    // 로그인 버튼 클릭 이벤트
    $("#signInButton").on("click", function(e) {
        e.preventDefault();

        if ($("#autoLogin").is(":checked")) {
            // 자동 로그인 체크박스가 체크되어 있으면 로컬 스토리지에 자동 로그인 상태 저장
            alert("자동 로그인으로 로컬 스토리지에 쿠키 저장");
            localStorage.setItem("autoLogin", "on");
        } else {
            // 체크박스가 체크되어 있지 않으면 로컬 스토리지에서 자동 로그인 상태 삭제
            localStorage.removeItem("autoLogin");
        }

        // 로그인 메서드 서버 요청
        $("#login_form").attr("action", "/login/main");
        $("#login_form").submit();
    });

});
*/

$("#signInButton").on("click", function(e) {
    e.preventDefault();

    if ($("#autoLogin").is(":checked")) {
        // 자동 로그인 체크박스가 체크되어 있으면 로컬 스토리지에 자동 로그인 상태 저장
        localStorage.setItem("autoLogin", "on");
    } else {
        // 체크박스가 체크되어 있지 않으면 로컬 스토리지에서 자동 로그인 상태 삭제
        localStorage.removeItem("autoLogin");
    }

    // 로그인 메서드 서버 요청
    $("#login_form").attr("action", "/login/main");
    // 자동 로그인이 체크되어 있다면, 히든 필드에 값을 설정
    if ($("#autoLogin").is(":checked")) {
        $('<input>').attr({
            type: 'hidden',
            id: 'autoLogin',
            name: 'autoLogin',
            value: 'on'
        }).appendTo('#login_form');
    }
    $("#login_form").submit();
});

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
