<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../resources/css/signup/signup_main.css">
    <%@include file="/WEB-INF/views/common/favicon.jsp" %>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
    <script src="../resources/js/signup/signup_main.js"></script>

    <title>MOYAMOGA</title>
	
</head>

<body>
<%@include file= "../common/header.jsp" %>
 

  

        <div class='wrapper'>
            
                <h1 id="title">회원가입</h1>

                <div class="signupbutton">
                     <a class="일반회원가입" href="/signup/agreement"><span id="일반"> 일반 회원가입</span></a>
                    
                     <a type="button" id='소셜회원가입' class='소셜회원가입' href="/signup/naver">
                        <img class='소셜아이콘' src="../resources/imgs/navericon.png"><span class="pp"> 네이버 회원가입 </span>
                     </a>
                     <a type="button" id='소셜회원가입2' class='소셜회원가입2' href="${naverAuthUrl}" style="display:none"></a>

                    <a href="https://kauth.kakao.com/oauth/authorize?client_id=4728fdda1dcf6b1dcbc098a9a7ece445&redirect_uri=http://localhost:8080/signup/kakao/callback&response_type=code" class='소셜회원가입'>
                        <img class='소셜아이콘' src='../resources/imgs/kakaoicon.png'><span class="pp"> 카카오 회원가입 </span>
                    </a>
                        
            </div>

            <div class='로그인'><span id=main_btm>이미 아이디가 있다면?</span><a id=doLogin href="/login/main"> 로그인 하기></a></div>
        </div>

  
<%@include file= "../common/footer.jsp" %>
 

</body>

	<!-- /signup/naver로 보내버리기..흑흑.. -->
	<script>
		(function() {
            // naverLoginButton 클릭 이벤트
            $("#소셜회원가입").on("click", function(e) {
                e.preventDefault(); // 기본 동작 중단
                sessionStorage.setItem("trigger_소셜회원가입2", "true");
                // /login/naver 페이지로 이동
                window.location.href = "/signup/naver";
            });

            // 페이지가 /signup/naver 인 경우
            if (window.location.pathname === "/signup/naver") {
                // 세션 스토리지에서 flag 확인
                if (sessionStorage.getItem("trigger_소셜회원가입2") === "true") {
                    // flag 제거
                    sessionStorage.removeItem("trigger_소셜회원가입2");
                    // naverLoginButton2 클릭 이벤트 실행
                    $("#소셜회원가입2")[0].click();
                }
            }
        })();
    </script>

</html>