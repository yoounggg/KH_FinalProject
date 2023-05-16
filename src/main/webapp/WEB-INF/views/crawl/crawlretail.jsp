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
	<%@include file= "/WEB-INF/views/common/header.jsp" %>
	
<main>
	<aside class="crawlbox">
        <ul class="box2">
            <li class="small2"><a href="/price/retail">농산물 소매 가격</a></li>
            <li class="small2"><a href="/price/wholesale">농산물 도매 가격</a></li>
        </ul>
    </aside>
    
    <div class="infobox">
    ● 본 자료의 출처는 한국농수산식품유통공사가 운영하는 농산물 유통정보입니다.<br>
    ● https://www.kamis.or.kr/customer/main/main.do <br>
    ● 대형마트, 전통시장 등에서 소비자에게 판매하는 가격입니다.<br>
	● 농산물 특성상 크기와 색상 등이 다양하고 저장기간, 기후변화에 따라 동일 등급에도 다소 차이가 있을 수 있음.<br>   
	● 1개월전, 1년전, 평년 가격은 해당일자 기준 5일 이동평균 가격임. (이동평균은 해당일 기준 전후로 4~5일의 평균값)<br> 
	● 평년은 5년간(금년 제외) 해당일에 대한 최고값과 최소값을 제외한 3년 평균값. (해당일 평균가격은 5일 이동평균값을 적용)<br>
	● 조사단위가 중량이 아닌 품목(포기, 개, 마리 등)은 kg 단위 환산이 제공되지 않음.<br> 
	● 본 가격자료는 전국 주요 시장에서 조사된 도∙소매 평균가격으로 개별 판매처 및 산지, 브랜드, 규격 등에 따라 가격이 다를 수 있으므로<br>
    &nbsp; &nbsp;&nbsp;실거래에 있어서 참고 자료로만 활용하여 주시기 바랍니다.<br>
    </div>
    
	<div class="retailinfo">
	    <div class="sujeong1">
            <h2 id="sujeong2">농산물 소매 가격</h2>
        </div>

		${retailInfo}

	</div>
       
    
</main>
<!-- ==========================footer========================= -->
   <%@include file= "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>