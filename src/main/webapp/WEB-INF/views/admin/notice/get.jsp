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
    <title>공지사항 상세</title>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
    <!-- include favicon -->
	<%@include file="/WEB-INF/views/common/favicon.jsp" %>
    <style>

        h3 {
            width: 780px;
            height: 30px;
            font-size: 20px;
        }

        .notice_detail {
			width: 780px;
		    min-height: 300px;
		    font-size: 16px;
		    font-weight: 700;
        }

        form {
            width: 780px;
            margin-top: 40px;
        }

        .reg_date {
            float: right;
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

            <h2>공지사항</h2>
            <div class="write">
                <form>
                    <p><input type="hidden" name="no" value="${notice.no}"></p>
                    <h3>${notice.title}</h3>
                    <hr>
                    <span>[${notice.no}]</span> <span>작성자 : ${notice.writer}</span>
                    <span class="reg_date">작성날짜 : ${notice.reg_date}</span>
                    <hr>
                    <div class="notice_detail">
                        ${notice.content}
                    </div>
                    <hr>
                    <div class="notice_button">
                        <button type="button" id="modifyBtn">수정</button>
                        <button type="button" id="removeBtn">삭제</button>
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
	var modifyBtn = document.querySelector('#modifyBtn');
	var removeBtn = document.querySelector('#removeBtn');
	
	//목록 버튼을 눌러 목록으로 돌아가기
	listBtn.addEventListener('click', function() {
		console.log('listBtn clicked');
		location.href='/admin/notice/list';
	});
	
	// 수정 버튼을 눌러 수정페이지로 가기
	modifyBtn.addEventListener('click', function() {
		console.log('modifyBtn clicked');
		self.location="/admin/notice/modify?no=${notice.no}";
	});
	
	
	// 삭제 버튼을 눌러 목록으로 돌아가기
        removeBtn.addEventListener('click', function(){
            console.log('removeBtn clicked ㅇ_<');

            //form 태그를 조작해서 삭제요청을 전송! 
            var form = document.querySelector('form');
            console.log(form.constructor.prototype);


            form.setAttribute('method', 'POST');
            form.setAttribute('action', '/admin/notice/remove');
            form.submit();


    });
    
    
</script>
</html>