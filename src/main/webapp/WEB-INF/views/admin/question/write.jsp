<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항 상세</title>
<link rel="stylesheet" href="/resources/css/admin/common.css">
<!-- include favicon -->
<%@include file="/WEB-INF/views/common/favicon.jsp"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="/resources/css/admin/get2.css">
<script src="/resources/js/admin/page.js"></script>
<body>
	<!-- 헤더 - 로그인/로그아웃 로고, 관리자페이지 메인글씨 -->
	<%@include file="/WEB-INF/views/admin/common/header.jsp"%>

	<!-- 메인 - 보라색 가로줄(메인페이지글자), 왼쪽 메뉴 -->
	<main>
		<div class="content">
			<h2>답변하기</h2>
			<div class="write">
				<form>
					<div id="content_head">1:1문의 답변</div>
					<input id="ttle" type="text" placeholder="제목을 입력해주세요" name="title" required>
                              
					<br>
					
					<textarea id="con" placeholder="내용을 입력해주세요" name="content" minlength="10" required></textarea><br>

					<div class="btm_btn">
						<div>
							<a href="/admin/question/list"><span class="list">목록보기</span></a>
						</div>

						<div>
							<a href="/admin/question/write"><span class="list">등록하기</span></a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</main>
</body>

<!-- 파일을 따로주면 안먹힘 이유가 뭘까.. -->
<script>
	$("#delete_btn").on("click", function(e) {
		console.log("클릭");
		var form = $('form');
		form.attr("action", "/admin/question/delete");
		form.attr("method", "post");
		form.submit();
	});
</script>

</html>