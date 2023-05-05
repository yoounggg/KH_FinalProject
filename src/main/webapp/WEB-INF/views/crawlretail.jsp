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
    
    <!-- include favicon -->
    <%@include file="/WEB-INF/views/common/favicon.jsp" %>

	<link rel="stylesheet" href="/resources/css/crawlretail.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,1,-25" />

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

</head>
<body>
	<!-- header -->
	<%@include file= "../views/common/header.jsp" %>
	
<main>
	<aside class="crawlbox">
        <ul class="box2">
            <li class="small2"><a href="/price/retail">농산물 소매 가격</a></li>
            <li class="small2"><a href="/price/wholesale">농산물 도매 가격</a></li>
        </ul>
    </aside>
    
	<div class="retailinfo">
	    <div class="sujeong1">
            <h2 id="sujeong2">농산물 소매 가격</h2>
        </div>

		${retailInfo}

	</div>
       
    
</main>
<!-- ==========================footer========================= -->
   <%@include file= "../views/common/footer.jsp" %>
</body>
</html>