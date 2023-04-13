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
    <title>FAQ 등록</title>

    <script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>
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

    .ck-content {
/*         width: 948px; */
        height: 500px;
        padding: 0px;
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
                <h2>FAQ <span class="test">테스트</span></h2>
                        
                <div class="write">
                    <form action="/admin/faq/register" method="POST">

				        <input type="text" name="title" placeholder="제목" class="text_title"><br>
				        <p>
				        	<textarea name="answer" id="faq_ct" placeholder="내용" ></textarea>
				        </p>
				        <p><input type="hidden" name="writer" value="admin" ></p>
        
                        <button type="submit" id="registerBtn" >등록</button>
                        <button type="button" id="listBtn" >목록</button>
                        

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


    /* 위지윅 적용 */
        ClassicEditor
            .create(document.querySelector('#faq_ct'))
            .catch(error=>{
                console.error(error);
            });

</script> 
<script>
	
	/* 자바스트립트로 작성! */
	var listBtn = document.querySelector('#listBtn');
	
	//목록 버튼을 눌러 목록으로 돌아가기
	listBtn.addEventListener('click', function() {
		console.log('listBtn clicked');
		location.href="/admin/faq/list";
	});

	

</script>	
    
</html>