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
    <title>거래처관리 등록</title>
	<!-- include favicon -->
	<%@include file="/WEB-INF/views/common/favicon.jsp" %>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
	<link rel="stylesheet" href="/resources/css/admin/register.css">
    <style>
     p {
		display: inline-block;
		font-size: 20px;

		width: 150px;
		height: 50px;

	 }

	#info {
		width: 600px;
		height: 50px;
		font-size: 20px;
	 }
        
    </style>
</head>


<body>
    <!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
    <%@include file= "/WEB-INF/views/admin/common/header.jsp" %> 

    <!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
    <main>
        <div class="sub_content">  
            
    <!-- 메인 - 공지사항 글쓰기, 수정, 삭제 -->            
                 
            <div class="content">
                <h2>거래처관리</h2>
                        
                <div class="write">
                    <form action="/admin/farm/register" method="POST">

	                    <div class="information">
	                    <h3>농가 정보</h3>
	                    <br>
	                    <br>
	                    <br>
	                        <input type="hidden" name="no" id="info">
	                        
	                        <p>농가 명</p>
	                        <input type="text" name="name" id="info" placeholder="업체명 입력">
	                        <br>
	    
	                        <p>사업자번호</p>
	                        <input type="text" name="business_no" id="info" placeholder="사업자번호 입력">
	                        <br>
	    
	                        <p>주소지</p>
	                        <input type="text" name="location" id="info" placeholder="주소지 입력">
	                        <br>
	    
	                        <p>전화번호</p>
	                        <input type="text" name="tel" id="info" placeholder="전화번호 입력">
	                        <br>
	                    </div>
	                    
	                    <div class="button">
	                        <button type="submit" id="registerBtn">등록</button>
	                        <button type="button" id="listBtn">목록</button>
                    	</div>
	                    
                    </form>
                </div>

            </div>

        </div>
    </main>


</body>
<!-- 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
 -->

<script>
	
	/* 자바스트립트로 작성! */
	var listBtn = document.querySelector('#listBtn');
	
	//목록 버튼을 눌러 목록으로 돌아가기
	listBtn.addEventListener('click', function() {
		console.log('listBtn clicked');
		location.href="/admin/farm/list";
	});

	

</script>	
    
</html>