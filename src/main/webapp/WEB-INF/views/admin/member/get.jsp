<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원관리 상세</title>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
	<link rel="stylesheet" href="/resources/css/admin/get.css">
	<!-- include favicon -->
	<%@include file="/WEB-INF/views/common/favicon.jsp" %>
    <style>
        p {
            display: inline-block;
            font-size: 16px;
            width: 150px;
            height: 50px;

        }
		button {
		    float: right;
		    margin-left : 5px;
		    margin-bottom : 100px;
		    font-weight: 800;
		    width: 75px;
		    height: 35px;
		    background-color: rgb(175, 124, 213);
		    border: 1px solid #ceced2;
		    font-size: 16px;
		}

    </style>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<body>
	<!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
	<%@include file= "/WEB-INF/views/admin/common/header.jsp" %> 
    <!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
    <main>
            
    <!-- 메인 - 공지사항 글 목록 테이블, 이동페이지, 수정, 삭제 -->            
                 
        <div class="content">

            <h2>회원관리</h2>
            <div class="write">
                <form>
                    <div class="information">
                    <h3>회원 정보</h3>
                    <br>
                    <br>
                    <br>
                        <p>번호</p>
                        <p id="info">${member.no}</p>
                        <br>
    
                        <p>이름</p>
                        <p id="info">${member.name}</p>
                        <br>
    
                        <p>생일</p>
                        <p id="info">${member.birth_year}. ${member.birth_month}. ${member.birth_day}.</p>
                        <br>
                        
                        <p>이메일</p>
                        <p id="info">${member.email}</p>
                        <br>
    
                        <p>전화번호</p>
                        <p id="info">${member.tel}</p>
                        <br>
    
                        <p>주소</p>
                        <p id="info">(${member.address1}) ${member.address2} ${member.address3}</p>
                        <br>
                        
                        <p>성별</p>
                        <p id="info">${member.gender}</p>
                        <br>
                        
                        <p>소셜 가입여부</p>
                        <p id="info">${member.social_type}</p>
                        <br>
                        
                        <p>가입 날짜</p>
                        <p id="info">${member.signup_date}</p>
                        <br>
                        
                    </div>


                    <div class="button">
                        <button type="button" id="listBtn">목록</button>
                    </div>
                 </form>
            </div>
        </div>
            

    </main>

</body>

 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>


<script>
	var listBtn = document.querySelector('#listBtn');
	
	//목록 버튼을 눌러 목록으로 돌아가기
	listBtn.addEventListener('click', function() {
		console.log('listBtn clicked');
		location.href='/admin/member/list';
	});

</script>
</html>