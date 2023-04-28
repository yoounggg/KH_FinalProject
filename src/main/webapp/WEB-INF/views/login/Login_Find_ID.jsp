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
    <link rel="stylesheet" href="/resources/css/login/Login_Find_ID.css">
    
	<!-- 아이디 찾기 메인창 js -->
    <!-- <script src="/resources/js/login/Login_Find_ID.js"></script>  -->

</head>

<body>

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>

        <!-- 아이디 찾기를 위한 전체 wrapper-->
        <div class="login_find_id_wrapper">

            <!-- <form action="/login" method="post"> -->
            <!-- <form id="login_find_ID_form"> -->
                <!-- 아이디 찾기 컨테이너 -->
    			<div class="find_id_container">

                    <!-- 아이디 찾기 타이틀 -->
					<div class="find_id_title">아이디 찾기</div>

                    <!-- 아이디 찾기 wrap - 폼 변경 -->
				    <ul class="find_id_wrap">
						<!-- 휴대폰 인증 버튼 -->
				        <li id="find_id_p_btn" class="find_id_active">휴대폰 인증</li>

                        <!-- 이메일 인증 버튼 -->
				        <li id="find_id_e_btn">이메일 인증</li>
				    </ul>
                    
                    <!-- 아이디 찾기 - 핸드폰 번호 인증 -->
				    <form id="find_id_p_form" class="find_id_form">
				    	<!-- 이름 입력창 -->
				        <input type="text"class="find_id_p_input" placeholder="이름을 입력해주세요." required>
				        <!-- 휴대폰 번호 입력창 -->
				        <input type="tel" placeholder="휴대폰 번호를 입력해주세요." required>
            			
            			<!-- 회원정보 조회 버튼 -->
						<button type="button" class="findid_button_p">회원정보 조회</button>
						
						<!-- 조회 성공 이후 인증번호 발송 버튼으로 변경 -->
						<button type="button" class="send_verification_button_p" style="display:none">인증번호 발송</button>
						
            			<!-- 회원정보 조회 버튼 클릭 이후 생기는 인증번호 입력창, 확인 버튼, 아이디 찾기 결과 버튼 -->
            			<div class="p_verification" style="display: none;">
            				<!-- 인증번호 입력창 -->
                        	<input style="display:none" type="text" id="tel" class="p_verification_input" placeholder="인증번호를 입력해주세요." required>
                        	<!-- 확인 버튼 -->
                        	<button style="display:none" type="button" class="p_verify_button">확인</button>
                        	<!-- 아이디 찾기 결과 버튼 -->
                        	<button style="display:none" type="button" class="p_verify_button_result">아이디 찾기 결과 확인하기</button>
                    	</div>
				    </form>

                    <!-- 아이디 찾기 - 이메일 인증 -->
			        <form id="find_id_e_form" class="find_id_form" style="display: none;">
			       		<!-- 이름 입력창 -->
			            <input type="text" class="find_id_e_input" placeholder="이름을 입력해주세요." required>
			            <!-- 이메일 입력창 -->
			            <input type="email" placeholder="이메일 주소를 입력해주세요." required>
			            
			            <!-- 회원정보 조회 버튼 -->
						<button type="button" class="findid_button_e">회원정보 조회</button>
						
						<!-- 조회 성공 이후 인증번호 발송 버튼으로 변경 -->
						<button type="button" class="send_verification_button_e" style="display:none">인증번호 발송</button>
			            <!-- 회원정보 조회 버튼 클릭 이후 생기는 인증번호 입력창 -->
			            <div class="e_verification" style="display: none;">
			            	<!-- 인증번호 입력창 -->
                        	<input style="display:none" type="text" class="e_verification_input" id="email" placeholder="인증번호를 입력해주세요." required>
                        	<!-- 확인 버튼 -->
                        	<button style="display:none" type="button" class="e_verify_button">확인</button>
                        	<!-- 아이디 찾기 결과 버튼 -->
                        	<button style="display:none" type="button" class="e_verify_button_result">아이디 찾기 결과 확인하기</button>
                    	</div>
			        </form>

    			</div>
    		</div>

    </main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>

	<!-- DOM이 완전히 로드되지 않은 상태에서 스크립트가 실행되는 것을 막기 위해 아래에다가 위치시키기! -->
    <!-- 아이디 찾기 메인창 js -->
    <script src="/resources/js/login/Login_Find_ID.js"></script>
</body>

</html>