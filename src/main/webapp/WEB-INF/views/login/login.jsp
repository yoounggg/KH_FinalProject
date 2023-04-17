<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>

	<!-- 로그인 메인창 css -->
    <link rel="stylesheet" href="/resources/css/Login_Main.css">

	<!-- 파비콘 -->
    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
	
	<!-- 로그인 js -->
	
	
	<!-- 로그인 제이쿼리 by 셍나 -->
	<script
		src="https://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous">
	</script>

</head>

<body>
	
	<div id="entire">

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>

        <div class="login_wrapper">

            <form action="/login" id="login_form" method="post">
                <div class="login_title">로그인</div>

                <!-- 아이디 입력창 -->
                <div class="id_class">
                    <label for="userId"></label>
                    <input id="userId" type="text" name="id" placeholder="아이디" required>
                </div>

                <!-- 비밀번호 입력창 -->
                <div class="password_class">
                    <label for="password"></label>
                    <input id="password" type="password" name="password" placeholder="비밀번호" required>
                </div>

                <!-- 아이디 저장, 자동 로그인, 아이디 찾기, 비밀번호 찾기 -->
                <div class="login_box">

                    <!-- 아이디 저장 체크박스 -->
                    <div class="rememberId">
                        <input type="checkbox" id="rememberId" name="rememberId" value="off">
                        <label for="rememberId2">아이디 저장</label>
                    </div>

                    <!-- 자동 로그인 체크박스 -->
                    <div class="autoLogin">
                        <input type="checkbox" id="autoLogin" name="autoLogin" value="off">
                        <label for="autoLogin2">자동 로그인</label>
                    </div>
                    
                    <!-- 아이디 비밀번호 찾기 -->
                    <span class="finding">
                        <li>
                            <a href="#" class="find_id">아이디 찾기</a><span>|</span> <a href="#" class="find_pw">비밀번호 찾기</a>
                        </li>
                    </span>

                </div>

                <div class="signIn"> <!-- 로그인 버튼 -->
                    <button type="button" id="signInButton">로그인</button>
                </div>
        
                <div class="naverLogin"> <!-- 네이버로 로그인 -->
                    <button type="button" id="naverLoginButton">
                        <img src="/resources//imgs/btnG_완성형.png" class="nimg"></button>
                </div>

                <div class="kakaoLogin"> <!-- 카카오로 로그인 -->
                    <button type="button" id="kakaoLoginButton">
                        <img src="/resources//imgs/kakao_login_large_narrow.png" class="kimg"></button>
                </div>
        
            </div>
        </form>
    </main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>
    
    </div>
</body>
    <script>
	    /* 셍나 로그인 버튼 클릭 메서드 */
	    $(".signInButton").click(function(){
	        
	        alert("로그인 버튼 작동 성공이에요!");
	        
	        <!--
	        /* 로그인 메서드 서버 요청 */
	        $("#login_form").attr("action", "/login/login");
	        $("#login_form").submit(); -->
	        
	    });
 
	</script>

</html>