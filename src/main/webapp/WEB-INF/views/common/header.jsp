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

    <link rel="stylesheet" href="/resources/css/main.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,1,-25" />

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

</head>

<body>

    <div class="topBanner">
        <div class="topbox">
            <div class="topcontent">
                    모두의 야채, 모두의 과일<br>
                    싱싱한 농산물을 합리적인 가격에 제공하는 직거래 유통 마켓
            </div>
            <button type="button" class="topBannerBtn">X</button>
        </div>
    </div>

    <header>    
            <ul class="container2">
            
            	<!--로그인 x-->
                <c:if test = "${member == null}">
                    <li><a href="/login/main">로그인</a></li>
                    <li><a href="/signup/main">회원가입</a></li>
                    <li class="nullcart"><a href="/cart/">장바구니</a></li>
                </c:if> 
                
                <!--로그인 O -->
                <c:if test = "${member != null}">

                        <!-- 관리자 계정 -->
                        <c:if test="${member.adminCk == 1}">
                            <li id="main_adminpage"><a href="/admin/main" id="main_adminpage">*관리자 페이지 click!</a></li>
                        </c:if>  
                    <li id="container2_name">${member.name}님 환영합니다 ^o^</li> 
                    <li><a id="gnb_logout" href="/login/logout">로그아웃</a></li>
                    <li><a href="/mypage/userInfo/${member.id}">마이페이지</a></li>
                    <li><a href="/cart/${member.id}">장바구니</a></li>
                    
                </c:if>       
            </ul>

            <div class="container3">
                <a href="/main"><img id="logo" src="/resources/imgs/logo.png" alt="로고"></a>
              	
              	<form id="mainsearch" class="search1" action="/mainsearch" method="GET">
                    <input class="search-txt" name="keyword" type="text" placeholder=" 검색어를 입력해주세요!">    
                    <button class="search-btn" type="submit"><img id="btn" src="/resources/imgs/search.png" alt="메인검색버튼"></button>
                </form>    
                 
            </div>
    </header>

    <nav> 
        <div class="menu">
            <ul class="container4">
                <li><a href="#"><i class="fas fa-bars"> 전체 카테고리</i></a>
                    <ul class="menu2">
                        <li><a href="#" id="lidrop">농산 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; > </a>
                            <ul class="menu3">
                               	<li><a href="/product/list?code=10100" id="lidrop">전체보기</a></li>
                                <li><a href="/product/list?code=10200" id="lidrop">오늘과일채소</a></li>
                                <li><a href="/product/list?code=10300" id="lidrop">국내외과일</a></li>
                                <li><a href="/product/list?code=10400" id="lidrop">친환경유기농채소</a></li>
                                <li><a href="/product/list?code=10500" id="lidrop">우리땅채소</a></li>
                                <li><a href="/product/list?code=10600" id="lidrop">채소/샐러드</a></li>
                                <li><a href="/product/list?code=10700" id="lidrop">주곡/잡곡</a></li>
                                <li><a href="/product/list?code=10900" id="lidrop">오늘의 특가</a></li>
                                <li><a href="/product/list?code=10900" id="lidrop">신상품</a></li>
                            </ul>
                        </li>
                    </ul>
                <li><a href="/price/retail">농산물 시세</a></li>        
                <li><a href="/product/list?code=10800">오늘의 특가</a></li>
                <li><a href="/product/list?code=10900">신상품</a></li>
                <li><a href="/help/main">고객센터</a></li>
            </ul>
        </div>
    </nav>

<input id="maintopbtn" type="button" name="TOP" value="TOP">
    
</body>
<script>
//비동기식 로그아웃!
$("#gnb_logout").click(function() {
	// alert("로그아웃 버튼 활성화")
		$.ajax({
    		type:"POST",
    		url:"/login/logout",
    		success:function(data){
    			// alert("비동기식 로그아웃 성공");
    			document.location.reload();
    	}
    }); // ajax
});

//top banner
$(document).ready(function(){
    $(".topBannerBtn").on("click", function(){ // 버튼 클릭하면
      $(".topBanner").slideUp(); // 탑배너 사라지게 함
      });
    });
    
//상단 nav 메뉴 고정
$(document).ready(function(){
    var menuOffset = $('.menu').offset();
    $(window).scroll(function(){
       if($(document).scrollTop() > menuOffset.top){
          $('.menu').addClass('fixedmenu').css('z-index', '999');
       } else{
          $('.menu').removeClass('fixedmenu').css('z-index', '');
          }
       });
 });
 
//로그인 하지 않으면 장바구니 이용불가능함 -> 알람창
$('.nullcart').click(function(){
	alert('로그인 후 이용바랍니다.');
});

//top버튼
$(window).scroll(function(){
    $("#maintopbtn").css("margin-top",Math.max(50,0-$(this).scrollTop()));
});
    $(function() {
        $(window).scroll(function() {
            if ($(this).scrollTop() > 500) {
                $('#maintopbtn').fadeIn();
            } else {
                $('#maintopbtn').fadeOut();
            }
        });
        
        $("#maintopbtn").click(function() {
            $('html, body').animate({
                scrollTop : 0
            }, 400);
            return false;
        });
    });
</script>
</html>