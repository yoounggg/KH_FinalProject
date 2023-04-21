<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>

	<!-- 파비콘 -->
    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    
	<!-- 아이디 찾기 메인창 css -->
    <link rel="stylesheet" href="/resources/css/login/Login_Change_PW.css">
    
	<!-- 아이디 찾기 메인창 js -->
    <script src="/resources/js/login/Login_Change_PW.js"></script>

</head>

<body>

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>

        <div class="login_change_pw_wrapper">

            <!-- <form action="/login" method="post"> -->
            <!-- <form id="login_find_ID_form"> -->
    			<div class="change_pw_container">
					<div class="change_pw_title">비밀번호 변경</div>

				    <ul class="change_pw_wrap">
				            <li id="change_pw_p_btn" class="change_pw_active">휴대폰 인증</li>
				            <li id="change_pw_e_btn">이메일 인증</li>
				    </ul>
				
				    <form id="change_pw_p_form" class="change_pw_form">
				        <input type="text"class="change_pw_p_input" placeholder="이름을 입력해주세요." required>
				        <input type="tel" placeholder="휴대폰 번호를 입력해주세요." required>
            			<button type="submit" class="val_button">인증번호 발송</button>
				    </form>

			        <form id="change_pw_e_form" class="change_pw_form" style="display: none;">
			            <input type="text" class="change_pw_p_input" placeholder="이름을 입력해주세요." required>
			            <input type="email" placeholder="이메일 주소를 입력해주세요." required>
			            <button type="submit" class="val_button">인증번호 발송</button>
			        </form>

    			</div>
    		</div>
        <!-- </form> -->
        
    </main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>
    
</body>

</html>