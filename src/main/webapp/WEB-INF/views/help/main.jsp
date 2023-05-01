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
    
    <link rel="stylesheet" href="/resources/css/help/main.css">

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,1,-25" />

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>

    <!-- slick: cdn 방식으로 css, js 가져오기 -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>			
    <style>
		#search {
		    position: absolute;
		    display: inline-block;
		    width: 87px;
			height: 32px;
		    padding-top: 3.2px;
		    line-height: 20px;
		    border: 2px solid #5d5d5d;
		    background: #5d5d5d;
		    text-align: center;
		    font-size: 16px;
		    font-weight: 500;
		    color: #fff;
		    text-decoration: none;
		}
	</style>
</head>

<body>
<!-- header -->
	<%@include file= "/WEB-INF/views/common/header.jsp" %>

<!-- BEST10 창 -->

    <main>
        <div class="helf_wrap">


            <!-- 상단 고객센터, 검색창 -->
            <div class="helf_top">
                <div class="helf_center">
                    <p class="helf_center_text">고객센터</p>
                </div>
                <div class="helf_search">
                    <ul class="helf_search_ul">
                        <p>공지사항 검색</p>
                        <div class="helf_search_input_a">
                            <input type="text" id="noticeKeyword" value="" placeholder="자주 찾는 질문을 검색해 보세요! ">
                            <button id="search" style="color: #fff;">검색</button>
                        </div>
                    </ul>
                </div>
            </div>

            <!-- 중간 - notice 사이드 메뉴/ 게시물 -->
            <div class="helf_content_wrap">

                <!-- 중간 - notice 사이드 메뉴 -->
                <div class="helf_side_menu">
                    <ul class="sm_ul">
                        <li id="sm_li"><a href="/help/faq">FAQ</a></li>
                        <li id="sm_li"><a href="/help/notice">공지사항</a></li>
                        <li id="sm_li"><a href="/help/guide">이용안내</a></li>
                    </ul>

                </div>
                

                <!-- 중간 - 게시물 -->
                <div class="content_wrap">
                    <h2 style="color:#4f4f4f">고객센터를 통해 궁금증을 해결하세요. </h2>
                    <h2 class="test">상담 문의량이 많아 통화가 어려울 수 있습니다.</h2>
                        <div class="content_list">

                            <div class="helf_box1">
                                <div class="helf_box12">
                                    <p>고객센터</p>
                                    <h1>1588-1234</h1>
                                    <p>평일 07:00~18:00(점심시간 12:00~13:00)</p>
                                    <p>주말 09:00~12:00</p>
                                </div>

                                <div class="helf_box2">
                                    <div class="helf_box3"><p>FAQ</p></div>
                                    <div class="helf_box3"><p>공지사항</p></div>
                                    <div class="helf_box3"><p>이용안내</p></div>
                                </div>
                            </div>


                        </div>
                        
                </div>
        
            </div>

        </div>
    </main>
<!-- ==========================footer========================= -->
   <%@include file= "/WEB-INF/views/common/footer.jsp" %>

</body>

</html>