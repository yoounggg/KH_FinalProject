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

    <style>
    
    span, li {
            font-size: 16px;
    }


    .content {
            font-size: 16px;
            font-weight: 600;
            font-family: "맑은 고딕",AppleGothic,Dotum,"돋움",sans-serif;
            /* color: #000; */
            padding: 0;
            width : 950px;
            height: 700px;
            margin : auto;
    }
    .content h1 {
        padding-top : 20px;
    }
    

    .write {
        width: 950px;
        margin-bottom: 20px;
        
    }
    .text_title {
        width: 945px;
        height: 30px;
        font-size: 20px;
    }
    textarea {
        width: 940px;
        height: 500px;
        font-size: 20px;
        font-weight: 700;
        padding: 0px;
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
     
    .farm_button {
     	margin-top : 20px;
	}

    /* --------------------- 농가 상세 페이지 --------------------------------- */

        .farm {
            background-color: #f7f7f7;
            text-align: center;
            padding: 30px;

        }

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
    <header>
        <div class="state">
            <span class="login">admin님 로그인하였습니다</span>
            <span class="login">Ι</span>
            <span class="login"><a href="/main">홈페이지</a></span>
            <span class="login">Ι</span>
            <span class="login"><a href="#">로그아웃</a></span>
        </div>
        <div>
  	      <h1><img src="/resources/css/admin/logo.png" id="logo" width="200"></h1> 
        </div>
        <h2 class="admin">관리자 페이지</h2>
        
    </header>

    <!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
    <main>
        <div class="sub_content">
            <nav> 메인페이지 </nav>
            <div class="menu1">
                <ul id="menu2">
                    <li><a href="/admin/product/register">상품등록</a></li>
                    <li><a href="/admin/product/list">상품목록</a></li>
                    <li><a href="/admin/notice/list">공지사항</a></li>
                    <li><a href="/admin/faq/list">FAQ</a></li>
                    <li><a href="/admin/member/list">회원관리</a></li>
                    <li><a href="/admin/farm/list">거래처관리</a></li>
                </ul>
            </div>   
            
    <!-- 메인 - 공지사항 글쓰기, 수정, 삭제 -->            
                 
            <div class="content">
                <h2>거래처관리 <span class="test">테스트</span></h2>
                        
                <div class="write">
                    <form action="/admin/farm/modify" method="POST">

	                    <div class="farm">
	                    <h3>농가 정보</h3>
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
	                    <div class="farm_button">
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