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
    <!-- <script src="/resources/js/login/Login_Change_PW.js"></script> -->

</head>

<body>

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>

		<!-- 비밀번호 변경을 위한 전체 wrapper-->
        <div class="login_change_pw_wrapper">

				<!-- 비밀번호 변경 컨테이너 -->
                <div class="change_pw_container">

					<!-- 비밀번호 변경 타이틀 -->
					<div class="change_pw_title">비밀번호 변경</div>
					
					<!-- 비밀번호 변경 wrap - 폼 변경 -->
                    <ul class="change_pw_wrap">
						<!-- 휴대폰 인증 버튼 -->
                        <li id="change_pw_p_btn" class="change_pw_active">휴대폰 인증</li>
                        
						<!-- 이메일 인증 버튼 -->
						<li id="change_pw_e_btn">이메일 인증</li>
                    </ul>
					
					<!-- 휴대폰 - 아이디 조회 -->
					<form id="id_search_form_p" class="id_search_form_p">
						<!-- 아이디 입력창 -->
                        <input type="text" id="id" class="id_search_input_p" placeholder="비밀번호 변경을 원하는 아이디를 입력해주세요." required>
                        <!-- 아이디 조회 버튼 -->
						<button type="submit" class="id_search_button_p">아이디 조회</button>
                        <!-- 아이디 조회 성공 이후 임시 비밀번호 발송 버튼으로 변경 -->
						<button style="display:none" type="submit" class="send_tempPw_button_p">임시 비밀번호 발송</button> 
					</form>
					
					<!-- 이메일 - 아이디 조회 -->
					<form id="id_search_form_e" class="id_search_form_e" style="display:none">
						<!-- 아이디 입력창 -->
						<input type="text" id="id" class="id_search_input_e" placeholder="비밀번호 변경을 원하는 아이디를 입력해주세요." required>
						<!-- 아이디 조회 버튼 -->
						<button type="submit" class="id_search_button_e">아이디 조회</button>
						<!-- 아이디 조회 성공 이후 임시 비밀번호 발송 버튼으로 변경 -->
						<button style="display:none" type="submit" class="send_tempPw_button_e">임시 비밀번호 발송</button>
					</form>
	
                </div>
            </div>
    </main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>
    
	<!-- 비밀번호 변경 메인창 js -->
	<script src="/resources/js/login/Login_Change_PW.js"></script>
</body>

</html>

