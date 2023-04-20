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
    
	<!-- 로그인 메인창 css -->
    <link rel="stylesheet" href="/resources/css/login/Login_Find_ID.css">

</head>

<body>

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>

        <div class="login_find_id_wrapper">

            <!-- <form action="/login" method="post"> -->
            <!-- <form id="login_find_ID_form"> -->
    			<div class="find_id_container">
					<div class="find_id_title">아이디 찾기</div>

				    <ul class="find_id_wrap">
				            <li id="find_id_p_btn" class="find_id_active">휴대폰 인증</li>
				            <li id="find_id_e_btn">이메일 인증</li>
				    </ul>
				
				    <form id="find_id_p_form" class="find_id_form">
				        <input type="text"class="find_id_p_input" placeholder="이름을 입력해주세요." required>
				        <input type="tel" placeholder="휴대폰 번호를 입력해주세요." required>
            			<button type="submit" class="val_button">인증번호 발송</button>
				    </form>

			        <form id="find_id_e_form" class="find_id_form" style="display: none;">
			            <input type="text" class="find_id_p_input" placeholder="이름을 입력해주세요." required>
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

	<!-- 이거 헤드에다가 넣으면 작동 XX 왜인지는 모르겠음 ㅠㅠ -->
	<!-- 로그인 메인창 js -->
	<script src="/resources/js/login/Login_Main.js"></script>

</html>