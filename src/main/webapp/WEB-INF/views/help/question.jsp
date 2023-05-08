<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYAMOGA</title>

    <%@include file="/WEB-INF/views/common/favicon.jsp" %>

    <link rel="stylesheet" href="/resources/css/help/question.css">

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,1,-25" />

    <script src="https://kit.fontawesome.com/1fe7ba446e.js" crossorigin="anonymous"></script>

    <!-- slick: cdn 방식으로 css, js 가져오기 -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

</head>

<body>
<!-- header -->
<%@include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        <div class="help_wrap">
            <!-- 상단 고객센터 -->
            <div class="help_top">
                <div class="help_center">
                    <p class="help_center_text">고객센터</p>
                </div>
                <div class="help_search"></div>
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
                    <h2>1:1문의 <span class="test">나의 문의 내역</span></h2>
                    <a href="/help/write" id="write">문의하기</a>
                    <div>
                </div>
                    <div class="content_list">
                        <table>
                            <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>문의유형</th>
                                    <th>제목</th>
                                    <th>작성날짜</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="list">
                                    <tr>
                                        <td><c:out value="${list.qno}"/></td>
                                        
                                        <td><c:out value="${list.type}"/></td>
                                        <td>
                                            <a href="/help/page?qno=${list.qno}">
                                                ${list.title}
                                            </a>
                                        </td>
                                        <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${list.regDate}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
    </main>
<!-- ==========================footer========================= -->
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>

</html>