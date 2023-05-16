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

    <link rel="stylesheet" href="/resources/css/main.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,1,-25" />

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <script src="/resources/js/main.js"></script>

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
                            	<!-- [05/01 진호] -->
                            	<c:forEach var="cate" items="${__CategoryAll__}">
                                    <li><a href="/product/list?code=${cate.code}" id="lidrop">${cate.categoryName}</a></li>
                                </c:forEach>
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

    <main>
        <!-- 메인 배너 이미지 -->
        <section class="slideShow">
            <div class="slides"> 
                <div class="banner-img"><a href="/product/info?code=10300&no=142"><img src="/resources/imgs/banner1.png" alt="배너1"></a></div>
                <div class="banner-img"><a href="/product/info?code=10400&no=164"><img src="/resources/imgs/banner2.png" alt="배너2"></a></div>
                <div class="banner-img"><a href="/product/info?code=10200&no=204"><img src="/resources/imgs/banner3.png" alt="배너3"></a></div>
                <div class="banner-img"><a href="/product/info?code=10500&no=253"><img src="/resources/imgs/banner4.png" alt="배너4"></a></div>
                <div class="banner-img"><a href="/product/info?code=10400&no=157"><img src="/resources/imgs/banner5.png" alt="배너5"></a></div>
                <div class="banner-img"><a href="/product/info?code=10300&no=202"><img src="/resources/imgs/banner6.png" alt="배너6"></a></div>
                <div class="banner-img"><a href="/product/info?code=10700&no=159"><img src="/resources/imgs/banner7.png" alt="배너7"></a></div>
                <div class="banner-img"><a href="/product/info?code=10200&no=205"><img src="/resources/imgs/banner8.png" alt="배너8"></a></div>
                <div class="banner-img"><a href="/product/info?code=10400&no=166"><img src="/resources/imgs/banner9.png" alt="배너9"></a></div>
                <div class="banner-img"><a href="/product/info?code=10200&no=238"><img src="/resources/imgs/banner10.png" alt="배너10"></a></div>
            </div>
    
            <div class="banner-btn">
                <div class="btnradius">
                    <span class="material-symbols-outlined prev-btn">
                        chevron_left
                    </span>
                    <span class="material-symbols-outlined pause-btn">
                        pause
                    </span>
                    <span class="material-symbols-outlined play-btn">
                        resume
                    </span>
                    <span class="material-symbols-outlined next-btn">
                        chevron_right
                    </span>
                </div>
            </div>
        </section>

		<div class="minibanner1">
        	<a href="/price/retail">
        	<img src="/resources/imgs/minibanner2.png" alt="미니배너">
       		</a>
        </div>

        <div class="minititle">오늘의 특가</div>
        <div class="container5">
            <div class="thumbnail">
                <a href="/product/info?code=10400&no=174"><img src ="/resources/product/우엉.png" alt="오늘의 특가1"></a>
                <a href="/product/info?code=10400&no=174">[오늘의 특가] <br>친환경 우엉실채 200g</a>
                <span><span class="dis_Green">33%</span>&nbsp; <del>6,000원</del>&nbsp; 4,010원</span>
                
            </div>
            <div class="thumbnail">
                <a href="/product/info?code=10400&no=171"><img src="/resources/product/적상추.png" alt="오늘의 특가2"></a>
                <a href="/product/info?code=10400&no=171">[오늘의 특가] <br>적상추/무농약 이상 (120g)</a>
                <span><span class="dis_Green">33%</span>&nbsp; <del>2,800원</del>&nbsp; 1,870원</span>
            </div>
            <div class="thumbnail">
                <a href="/product/info?code=10400&no=167"><img src="/resources/product/블루베리.png"" alt="오늘의 특가3"></a>
                <a href="/product/info?code=10400&no=167">[오늘의 특가] <br>무농약 곡성 생블루베리(100g)</a>
                <span><span class="dis_Green">40%</span>&nbsp; <del>12,000원</del>&nbsp; 7,200원</span>
            </div>
            <div class="thumbnail">
                <a href="/product/info?code=10600&no=165"><img src="/resources/product/어린잎 채소.png" alt="오늘의 특가4"></a>
                <a href="/product/info?code=10600&no=165">[오늘의 특가] <br>무농약 피크닉 어린잎 채소(100g)</a>
                <span><span class="dis_Green">30%</span>&nbsp; <del>3,500원</del>&nbsp; 2,450원</span>
            </div>
		</div>

        <div class="minibanner1">
        	<a href="/product/info?code=10900&no=201">
        	<img src="/resources/imgs/minibanner.png" alt="미니배너">
       		</a>
        </div>
            
 		<div class="minititle">신상품</div>
        <div class="container6">
            <div class="thumbnail">
                <a href="/product/info?code=10900&no=145"><img src="/resources/product/청도 아이스 홍시.jpg" alt="신상품1"></a>
                <a href="/product/info?code=10900&no=145">[금주의 신상품] <br>청도 아이스 홍시</a>
                <span><span class="dis_Green">37%</span>&nbsp; <del>11,000원</del>&nbsp; 6,930원</span>
            </div>
            <div class="thumbnail">
                <a href="/product/info?code=10900&no=215"><img src="/resources/product/부추.png" alt="신상품2"></a>
                <a href="/product/info?code=10900&no=215">[금주의 신상품] <br>국내산 영양부추(100g)</a>
                <span><span class="dis_Green">20%</span>&nbsp; <del>4,500원</del>&nbsp; 3,600원</span>
            </div>
            <div class="thumbnail">
                <a href="/product/info?code=10900&no=214"><img src="/resources/product/래디시.png" alt="신상품3"></a>
                <a href="/product/info?code=10900&no=214">[금주의 신상품] <br>국내산 래디시(100g)</a>
                <span><span class="dis_Green">10%</span>&nbsp; <del>4,000원</del>&nbsp; 3,600원</span>
            </div>
            <div class="thumbnail">
                <a href="/product/info?code=10900&no=211"><img src="/resources/product/오트밀.png" alt="신상품4"></a>
                <a href="/product/info?code=10900&no=211">[금주의 신상품] <br>국산 유기농 오트밀(500g)</a>
                <span><span class="dis_Green">20%</span>&nbsp; <del>15,000원</del>&nbsp; 12,000원</span>
            </div>
        </div>
        
        
        <div class="minititle">모야모과의 레시피</div>
        <div class="recipe">
            <img src="/resources/imgs/recipe_potato.png" alt="레시피1">
            <div class="recipe2">
                <span><u><i>MOYAMOGA's Recipe</i></u></span><br>
                <span><i>에어프라이어로 버터 감자구이 만들기</i></span><br>
                <br>
                <span><i>STEPS</i></span><br>
                1. 감자는 껍질채 씻어 준비합니다 <br>
                2. 취향껏 자른 뒤 녹인 버터를 속까지 바르고 <br>
                3. 허브솔트를 알맞게 뿌리고 <br>
                4. 에어프라이어에 넣고 200도 온도에서 20분간 구워요 <br>
                5. 노릇노릇하니 잘 구워진 감자~ <br>
                6. 껍질째 먹어도 맛있어요! <br>
                <br>
                <a href="/product/info?code=10400&no=157"><i> 다른 레시피 보러가기 &nbsp;&nbsp; > </i></a>
            </div>    
        </div>

    </main>

    <footer>
        <div class="yakgwan1">
            <div class="yakgwan2">
	        	<li><a href="/help/main">고객센터</a></li>
	        	<li><a href="/help/terms">약관 및 정책</a></li>
	        	<li><a id="gaein" href="/help/privacy">개인정보 취급방침</a></li>
            </div>
        </div>

        <div class="bottom1">
            <div class="bottom2">
                <div id="com_address">
                    <p id="jusik">(주)모야모과</p>
                    <p class="gray">강남지원 1관 : 서울특별시 강남구 테헤란로14길 6 남도빌딩<br>
                        상호: 주식회사 모야모과 대표자:요셉킴 사업자등록번호: 123-45-67891<br>
                        통신판매업신고번호: 제2023-서울강남-0208</p>
                    <p></p>
                    <p class="gray">Copyright ⓒ MOYAMOGA Corp. All Rights Reserved.</p>
                </div>
                <div id="custom_notice">
                    <p id="gogaek">고객센터 <span id="phnum">1588-1234</span></p>
                    <p></p>
                    <p class="gray">평일 07:00~18:00 / 토, 일요일 09:00~12:00<br>
                        점심시간 12:00~13:00</p>
                    <a href="/help/main">고객센터 바로가기 ></a>
                </div>
            </div>
        </div>      
    </footer>
    
    <!-- 세인이..비동기식 로그아웃 js 불러오려면 여기다 넣어야지만 불러와짐 흑흑,, -->
    <script src="/resources/js/main.js"></script>
    
</body>
<script>

</script>
</html>