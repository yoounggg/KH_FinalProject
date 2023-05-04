<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>

	<!-- 파비콘 -->
    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    
	<!-- 주문 내역 css -->
    <link rel="stylesheet" href="/resources/css/mypage/OrderList.css">
    
	<!-- 주문 내역 js -->
    <!-- <script src="/resources/js/mypage/derDetails.js"></script>  -->

</head>

<body>

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>
	    <!-- side bar 메뉴 -->
	    <aside class="userinfoAside">
	        <ul class="box1">
	            <img src="/resources/imgs/profileimg.jpg" alt="프로필 사진">
	            <li class="small1">${details.name} 님</li>
	            <li class="small1"><a href="/mypage/userInfo/${member.id}"><i class="fab fa-whmcs"></i>회원정보관리</a></li>
	        </ul>
	
	        <ul class="box2">
	            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}">정보수정</a></li>
	            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}/changePw">비밀번호 변경</a></li>
	            <li class="small2 small3 small4"><a href="/mypage/?">주문내역</a></li>
	            <li class="small2 small5"><a href="/mypage/?">배송현황</a></li>
	        </ul>
	    </aside>
	    
	<!-- 주문내역 -->
	

    </main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>

	<!-- DOM이 완전히 로드되지 않은 상태에서 스크립트가 실행되는 것을 막기 위해 아래에다가 위치시키기! -->
    <!-- 주문 내역 js -->
    <script src="/resources/js/mypage/OrderList.js"></script>
</body>

</html>