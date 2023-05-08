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
    <title>거래처관리 수정</title>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
	<link rel="stylesheet" href="/resources/css/admin/farm_modify.css">
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
                    <form action="/admin/farm/modify" method="POST">

	                    <div class="information">
	                    <h3>농가 정보 수정</h3>
	                    <br>
	                    <br>
	                    <br>
	                        <p>농가 번호</p>
	                        <input type="text" value="${farm.no}" name="no" id="info" readonly>
	                        <br>
	    
	                        <p>농가 명</p>
	                        <input type="text" value="${farm.name}" name="name" id="info" >
	                        <br>
	    
	                        <p>사업자 번호</p>
	                        <input type="text" value="${farm.business_no}" name="business_no" id="info">
	                        <br>
	    
	                        <p>주소지</p>
	                        <input type="text" value="${farm.location}" name="location" id="info">
	                        <br>
	    
	                        <p>전화번호</p>
	                        <input type="text" value="${farm.tel}" name="tel"  id="info">
	                        <br>
	                    </div>
	                    <div class="button">
	                        <button type="submit" id="modifyBtn">수정</button>
	                        <button type="button" id="removeBtn">삭제</button>
	                        <button type="button" id="listBtn">목록</button>
                    	</div>
                    
                    </form>
                </div>

            </div>

        </div>
    </main>


</body>


<script>
	
	/* 자바스트립트로 작성! */
	var listBtn = document.querySelector('#listBtn');
	
	//목록 버튼을 눌러 목록으로 돌아가기
	listBtn.addEventListener('click', function() {
		console.log('listBtn clicked');
		location.href='/admin/farm/list';
	});

	
	
	/* 게시물 삭제하기! */
    var removeBtn = document.querySelector('#removeBtn');
       
    	removeBtn.addEventListener('click', function(){
        console.log('removeBtn clicked ㅇ_<');

        //form 태그를 조작해서 삭제요청을 전송! 
        var form = document.querySelector('form');
        console.log(form.constructor.prototype);


        form.setAttribute('method', 'POST');
        form.setAttribute('action', '/admin/farm/remove');
        form.submit();

    });


</script>	
    
</html>