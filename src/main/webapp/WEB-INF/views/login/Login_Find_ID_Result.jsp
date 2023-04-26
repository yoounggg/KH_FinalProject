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
    
	<!-- 아이디 찾기 메인창 css -->
    <link rel="stylesheet" href="/resources/css/login/Login_Find_ID.css">
    
	<!-- 아이디 찾기 메인창 js -->
    <!-- <script src="/resources/js/login/Login_Find_ID.js"></script>  -->

</head>

<body>

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>

		<h1>아이디 찾기 결과입니다.</h1>
		<h2>${foundId}</h2>
        
    </main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>
    
	<!-- DOM이 완전히 로드되지 않은 상태에서 스크립트가 실행되는 것을 막기 위해 아래에다가 위치시키기! -->
    <!-- 아이디 찾기 메인창 js -->
    <script src="/resources/js/login/Login_Find_ID.js"></script>
</body>

</html>