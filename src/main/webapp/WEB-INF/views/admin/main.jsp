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
    <title>MYMG</title>
    <link rel="stylesheet" href="/resources/css/admin/main.css">
	<!-- include favicon -->
	<%@include file="/WEB-INF/views/common/favicon.jsp" %>
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
							<c:forEach items="${mainProduct}" var="ProductDTO" varStatus="status" end="4">
							    <li><a href="/admin/product/get?no=${ProductDTO.no}">${ProductDTO.name}</a></li>
							</c:forEach>
							</ul>         
                    </div>
                    <div class="box2">
                        <h3>공지사항</h3>
                        <ul id="main_list">
							<c:forEach items="${mainNotice}" var="NoticeVO" varStatus="status" end="4">
							    <li><a href="/admin/notice/get?no=${NoticeVO.no}">${NoticeVO.title}</a></li>
							</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- <br> -->
            <div class="box1">
                <div class="box2">
                    <h3>FAQ</h3>
                    <ul id="main_list">
							<c:forEach items="${mainFaq}" var="FaqVO" varStatus="status" end="4">
							    <li><a href="/admin/faq/get?no=${FaqVO.no}">${FaqVO.title}</a></li>
							</c:forEach>
                    </ul>
                </div>
                <div class="box2">
                    <h3>TEST</h3>
                    <ul id="main_list">
                        <li>당일 주문량 : </li>
                        <li>당일 매출액 : </li>
                        <li>당일 방문수 : </li>
                        <li><a href="#">[test]test</a></li>
                    </ul>
                </div>
            </div>
            

        </div>  
    </main>
    <footer>
    </footer>

</body>
</html>