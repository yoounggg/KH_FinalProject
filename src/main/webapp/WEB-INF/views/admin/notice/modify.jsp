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
    <title>공지사항 수정</title>

    <script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
    <!-- <link rel="stylesheet" href="/admin_page/css/notice/notice_reg.css"> -->

    <style>
    
        .content {
        font-size: 13px;
        font-weight: 600;
        font-family: "맑은 고딕",AppleGothic,Dotum,"돋움",sans-serif;
        /* color: #000; */
        padding: 0;
        width : 800px;
        height: 700px;
        margin : auto;
    }

    .content h1 {
        margin : 0 0 0 40px;
        padding-top : 20px;
    }
    

    .write {
        width: 700px;
        margin-bottom: 20px;
    }
    .text_title {
        width: 612px;
        height: 30px;
        font-size: 20px;
    }
    textarea {
        width: 800px;
        height: 500px;
        font-size: 20px;
        font-weight: 700;
    }

    form {
        margin : 40px;
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
        /* width: 600px; */
        height: 300px;
    }
    </style>
</head>


<body>
    <!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
    <header>
        <div class="state">
            <span class="login">admin님 로그인하였습니다</span>
            <span class="login">Ι</span>
            <span class="login"><a href="#">홈페이지</a></span>
            <span class="login">Ι</span>
            <span class="login"><a href="#">로그아웃</a></span>
        </div>
        <div>
            <h1><img src="/admin/css/admin/logo.png" id="logo" width="200"></h1> 
        </div>
        <h2 class="admin">관리자 페이지</h2>
        
    </header>

    <!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
    <main>
        <div class="sub_content">
            <nav> 메인페이지 </nav>
            <div class="menu1">
                <ul id="menu2">
                    <li><a href="#">상품등록</a></li>
                    <li><a href="#">상품목록</a></li>
                    <li><a href="#">공지사항</a></li>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">회원관리</a></li>
                </ul>
            </div>   
            
    <!-- 메인 - 공지사항 글쓰기, 수정, 삭제 -->            
                 
            <div class="content">

                <h2>공지사항 <span class="test">테스트</span></h2>
        
                <div class="write">
                    <form action="/admin/notice/modify" method="POST">
                        
                        <p>글번호 : <input type="text"  name="no" value="${notice.no}"  readonly></p>
                        <p>작성자 : <input type="text" name="writer" value="admin"  readonly>
				        </p>
				        <input type="text" value="${notice.title}"  name="title" placeholder="제목" class="text_title"><br>
				        <p>
				        	<textarea name="content" id="notice_ct" placeholder="내용" required>${notice.content}</textarea>
				        </p>
        
                        <button type="submit" id="modifyBtn" >수정</button>
                        <button type="button" id="removeBtn" >삭제</button>
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
            .create(document.querySelector('#notice_ct'))
            .catch(error=>{
                console.error(error);
            });

</script> 
<script>
	
	// 자바스트립트로 작성!
	var listBtn = document.querySelector('#listBtn');
	
	//목록 버튼을 눌러 목록으로 돌아가기
	listBtn.addEventListener('click', function() {
		console.log('listBtn clicked');
		location.href='/admin/notice/list';
	});

    var removeBtn = document.querySelector('#removeBtn');
       

     
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