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
    
    <%@include file="/WEB-INF/views/common/favicon.jsp" %>
    
    <link rel="stylesheet" href="/resources/css/mainsearch.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,1,-25" />

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>   
	
</head>

<body>
<%@include file= "../views/common/header.jsp" %>
 
<!--========== 메인 ==========-->
<main>
    <div class="searchtitle"><strong>'${__PAGE_MAKER__.cri.keyword}'</strong>&nbsp;검색결과</div>

    <div class="listcontent">
      <div class="toptotal">총 <strong>${__PAGE_MAKER__.totalAmount}개</strong>의 상품이 있습니다</div>
	        <div class="listproduct">
        	<c:forEach items="${mainsearchlist}" var="mainsearchlist">
	            <div class="searchedproduct">
	                <ul>
	                    <li>
	                        <div>
	                            <div class="imgbox">	
	                            	<a href="/product/info?code=${mainsearchlist.category}&no=${mainsearchlist.no}"><img src="/resources/product/${mainsearchlist.main_image}" width="220" height="220" alt="검색상품이미지"></a>
	                            </div>
	                            <div class="infobox">
	                                <div class="searchname"><a href="/product/info?no=${mainsearchlist.no}">${mainsearchlist.name}</a></div>
	                             	<div>
		                             	<span class="discount">${mainsearchlist.discount}%</span>
		                             	<span class="discountprice">${mainsearchlist.discount_price}원</span>
		                             	<span class="price"><del>${mainsearchlist.price}원</del></span>
	                             	</div>	                             
	                            </div>
	                        </div>
	                    </li>
	                </ul>
	            </div>
			</c:forEach>
	        </div>
    </div>
	<div>
	<!-- 페이징 -->
		<div class="searchpageinfo">
			<ul>
				<c:if test="${__PAGE_MAKER__.prev}">
					<li class="searchprev"><a href="/mainsearch?keyword=${__PAGE_MAKER__.cri.keyword}&currPage=${__PAGE_MAKER__.startPage-1}"> << </a></li>
				</c:if>
				
				<c:forEach var="num" begin="${__PAGE_MAKER__.startPage}" end="${__PAGE_MAKER__.endPage}">
		        	<li class="searchbutton"><a href="/mainsearch?keyword=${__PAGE_MAKER__.cri.keyword}&currPage=${num}&amount=${__PAGE_MAKER__.cri.amount}">${num}</a></li>
		        </c:forEach>
		        
		        <c:if test="${__PAGE_MAKER__.next}">
					<li class="searchnext"><a href="/mainsearch?keyword=${__PAGE_MAKER__.cri.keyword}&currPage=${__PAGE_MAKER__.endPage + 1 }&amount=${__PAGE_MAKER__.cri.amount}"> >> </a></li>
				</c:if>
			</ul>
		</div>
	</div>
</main>
  
<%@include file= "../views/common/footer.jsp" %>
</body>

</html>