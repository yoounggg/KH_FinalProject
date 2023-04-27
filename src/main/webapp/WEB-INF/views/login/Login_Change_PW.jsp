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
					
					<!-- 비밀번호 변경 - 핸드폰 번호 인증 -->
				    <form id="change_pw_p_form" class="change_pw_form">
						<!-- 이름 입력창 -->
				        <input type="text"class="change_pw_p_input" placeholder="이름을 입력해주세요." required>
				        <!-- 휴대폰 번호 입력창 -->
						<input type="tel" placeholder="휴대폰 번호를 입력해주세요." required>
            			
						<!-- 회원정보 조회 버튼 -->
						<button type="submit" class="val_button">회원정보 조회</button>
				    	<!-- 조회 성공 이후 인증번호 발송 버튼으로 변경 -->
						<button type="button" class="send_verification_button_e" style="display:none">인증번호 발송</button>
			            <!-- 회원정보 조회 버튼 클릭 이후 생기는 인증번호 입력창, 확인 버튼, 비밀번호 변경하기 결과 버튼, 새로운 비밀번호 변경 입력창 -->
			            <div class="e_verification" style="display: none;">
			            	<!-- 인증번호 입력창 -->
                        	<input style="display:none" type="text" class="e_verification_input" id="email" placeholder="인증번호를 입력해주세요." required>
                        	<!-- 확인 버튼 -->
                        	<button style="display:none" type="button" class="e_verify_button">확인</button>
                        	<!-- 아이디 찾기 결과 버튼 -->
                        	<button style="display:none" type="button" class="e_verify_button_result">아이디 찾기 결과 확인하기</button>
                    	</div>
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
    
	<!-- 비밀번호 변경 메인창 js -->
	<script src="/resources/js/login/Login_Change_PW.js"></script>
</body>

</html>

