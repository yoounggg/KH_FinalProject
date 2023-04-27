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
    <title>거래처관리 상세</title>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
    <style>

        h3 {
            width: 950px;
            height: 30px;
            font-size: 22px;
        }

        .farm_detail {
            width: 950px;
            height: 500px;
            font-size: 20px;
            font-weight: 700;
        }

        form {
            width: 950px;
            margin-top: 40px;
        }

        .farm_button {
     		margin-top : 20px;
     	}

        .reg_date {
            float: right;
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
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<body>
	<!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
	<%@include file= "/WEB-INF/views/admin/common/header.jsp" %> 
    <!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
    <main>
            
    <!-- 메인 - 공지사항 글 목록 테이블, 이동페이지, 수정, 삭제 -->            
                 
        <div class="content">

            <h2>거래처관리 <span class="test">테스트</span></h2>
            <div class="write">
                <form>
                    <div class="farm">
                    <h3>농가 정보</h3>
                    <br>
                    <br>
                    <br>
                        <p>농가 번호</p>
                        <input type="text" value="${farm.no}" id="info" readonly>
                        <br>
    
                        <p>농가 명</p>
                        <input type="text" value="${farm.name}" id="info" readonly>
                        <br>
    
                        <p>사업자 번호</p>
                        <input type="text" value="${farm.business_no}" id="info" readonly>
                        <br>
    
                        <p>주소지</p>
                        <input type="text" value="${farm.location}" id="info" readonly>
                        <br>
    
                        <p>전화번호</p>
                        <input type="text" value="${farm.tel}" id="info" readonly>
                        <br>
                    </div>


                    <div class="farm_button">
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
		location.href='/admin/farm/list';
	});
	
	// 수정 버튼을 눌러 수정페이지로 가기
	modifyBtn.addEventListener('click', function() {
		console.log('modifyBtn clicked');
		self.location="/admin/farm/modify?no=${farm.no}";
	});
	
	
	// 삭제 버튼을 눌러 목록으로 돌아가기
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