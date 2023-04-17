// 셍나 로그인 버튼 클릭 메서드
$('#signInButton').click(function() {
    <!-- alert("로그인 버튼 작동 성공이에요!"); -->
    
    // 로그인 메서드 서버 요청
    $("#login_form").attr("action", "/login/main");
    $("#login_form").submit();

});
