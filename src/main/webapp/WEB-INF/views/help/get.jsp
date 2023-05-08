<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>
    
	<!-- include favicon -->
    <%@include file="/WEB-INF/views/common/favicon.jsp" %>
    
    <link rel="stylesheet" href="/resources/css/help/get.css">

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
	
    <main>
        <div class="help_wrap">


            <!-- 상단 고객센터, 검색창 -->
            <div class="help_top">
                <div class="help_center">
                    <p class="help_center_text">고객센터</p>
                </div>
                <div class="help_search">
                    <ul class="help_search_ul">
                        <p>공지사항 검색</p>
                        <div class="help_search_input_a">
                            <input type="text" id="noticeKeyword" value="" placeholder="자주 찾는 질문을 검색해 보세요! ">
                            <button id="search" style="color: #fff;">검색</button>
                        </div>
                    </ul>
                </div>
            </div>

            <!-- 중간 - notice 사이드 메뉴/ 게시물 -->
            <div class="help_content_wrap">

                <!-- 중간 - notice 사이드 메뉴 -->
                <div class="help_side_menu">
                    <ul class="sm_ul">
                        <li id="sm_li"><a href="/help/faq">FAQ</a></li>
                        <li id="sm_li"><a href="/help/notice">공지사항</a></li>
                        <li id="sm_li"><a href="/help/guide">이용안내</a></li>
                        <%-- 로그인이 되어 있는 경우 --%>
						<c:if test="${not empty sessionScope.member}">
							<li id="sm_li"><a href="/help/question">1:1문의</a></li>
						</c:if>

						<%-- 로그인이 되어 있지 않은 경우 --%>
						<c:if test="${empty sessionScope.member}">
							<li id="sm_li"><a href="/login/main">1:1문의</a></li>
						</c:if>
                    </ul>

                </div>
                

                <!-- 중간 - 게시물 -->
                <div class="content_wrap">
                        <h2>공지사항 <span class="test">새로운 소식들을 확인하세요</span></h2>
						<form>
                        <div class="content_list">
			                <table>
			                	<p><input type="hidden" name="no" value="${notice.no}"></p>
								<tr>
									<th><h3>${notice.title}</h3></th>
								</tr>
								<tr>
									<td>
										<div class="notice_detail">${notice.content}</div>
									</td>
								</tr>
							</table>
							<h4><button id="listBtn"><a href="/help/notice" id="Btn">전체보기</a></button></h4>
		
						</div>
						</form>
	    		</div>
        </div>
        </div>
    </main>
    <!-- ==========================footer========================= -->
   <%@include file= "/WEB-INF/views/common/footer.jsp" %>
</body>
</html>