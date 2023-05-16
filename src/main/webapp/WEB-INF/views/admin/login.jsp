<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" href="/resources/css/admin/login.css">
	<script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>
    <!-- include favicon -->
	<%@include file="/WEB-INF/views/common/favicon.jsp" %>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    
</head>
<body>
    <div class="login">
        <img src="/admin_page/image/logo.png" width="200">
        <h2>관리자 페이지</h2>
        
        <form action="/admin/login" method="POST">
	        <input type="text" title="아이디 입력" placeholder="아이디를 입력해 주세요."  name="id"><br>
	        <input type="password" title="비밀번호 입력" placeholder="비밀번호를 입력해 주세요." name="password"><br>
	
	        <button class="loginBtn" id="loginBtn">로그인</button>
		</form>
    </div>
</body>
<script>
 
    /* 로그인 버튼 클릭 메서드 */
	var loginBtn = document.querySelector('#loginBtn');
	
	//목록 버튼을 눌러 목록으로 돌아가기
	listBtn.addEventListener('click', function() {
		console.log('loginBtn clicked');
		location.href='/admin/main';
	});
 
</script>

</html>