<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MYMG</title>
    <link rel="stylesheet" href="/resources/css/admin/main.css">

</head>
<body>
    <!-- 헤더 - 로그인,로그아웃, 로고, 관리자페이지, 옆에 메뉴!!까지 -->
    <%@include file= "/WEB-INF/views/admin/common/header.jsp" %>
    <main>
        <div>
    <!-- 사이드 메인 - 보라색 가로줄(메인페이지글자), 메뉴-->
            
    <!-- 메인 - 테이블 목록 -->
            <div class="container">
                <div class="box1">
                    <div class="box2">
                        <h3>상품목록</h3>
                        <ul id="main_list">
                            <li><a href="#">[귤]제주감귤</a></li>
                            <li><a href="#">[감자]강원도감자</a></li>
                            <li><a href="#">[고추]청양고추</a></li>
                            <li><a href="#">[딸기]논산딸기</a></li>
                        </ul>
                    </div>
                    <div class="box2">
                        <h3>공지사항</h3>
                        <ul id="main_list">
                            <li><a href="#">[이벤트]12월 당첨자</a></li>
                            <li><a href="#">[이벤트]11월 당첨자</a></li>
                            <li><a href="#">[이벤트]10월 당첨자</a></li>
                            <li><a href="#">[이벤트]9월 당첨자</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- <br> -->
            <div class="box1">
                <div class="box2">
                    <h3>FAQ</h3>
                    <ul id="main_list">
                        <li><a href="#">[상품] 상품이 파손되었습니다.</a></li>
                        <li><a href="#">[회원] 로그인이 안 돼요.</a></li>
                        <li><a href="#">[배송] 배송이 지연됐어요.</a></li>
                        <li><a href="#">[배송] 취소하면 어떻게 되나요?</a></li>
                    </ul>
                </div>
                <div class="box2">
                    <h3>TEST</h3>
                    <ul id="main_list">
                        <li><a href="#">[test]test</a></li>
                        <li><a href="#">[test]test</a></li>
                        <li><a href="#">[test]test</a></li>
                        <li><a href="#">[test]test</a></li>
                    </ul>
                </div>
            </div>
            

        </div>  
    </main>

</body>
</html>