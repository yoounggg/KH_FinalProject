<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 목록</title>
    <!-- include favicon -->
	<%@include file="/WEB-INF/views/common/favicon.jsp" %>
    <link rel="stylesheet" href="/resources/css/admin/common.css">
	<link rel="stylesheet" href="/resources/css/admin/list2.css"> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

</head>

<body>

<!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
<%@include file= "/WEB-INF/views/admin/common/header.jsp" %> 

<!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
<main>
    <div class="sub_content">
<!-- 메인 - 공지사항 글 목록 테이블, 이동페이지, 수정, 삭제 -->            
                
        <div class="content">
        <form>
            <table id="list">
                <h2>1:1문의 <span class="test">관리페이지</span></h2>
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
								<a href="/admin/question/page?qno=${list.qno}">
									${list.title}
								</a>
							</td>
							<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${list.regDate}"/></td>
 						</tr>
					</c:forEach>
                </tbody>
            </table>
		</form>
        </div>
    </div>
</main>


    
</body>

</html>