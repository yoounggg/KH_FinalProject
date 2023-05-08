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

	<!-- 파비콘 -->
    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    
	<!-- 주문 내역 css -->
    <link rel="stylesheet" href="/resources/css/mypage/OrderList.css">

</head>

<body>

	<!-- header -->
	<%@include file= "../common/header.jsp" %>

    <main>
	    <!-- side bar 메뉴 -->
	    <aside class="userinfoAside">
	        <ul class="box1">
	            <img src="/resources/imgs/profileimg.jpg" alt="프로필 사진">
	            <li class="small1">${details.name} 님</li>
	            <li class="small1"><a href="/mypage/userInfo/${member.id}"><i class="fab fa-whmcs"></i>회원정보관리</a></li>
	        </ul>
	
	        <ul class="box2">
	            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}">정보수정</a></li>
	            <li class="small2 small3 small4"><a href="/mypage/userInfo/${member.id}/changePw">비밀번호 변경</a></li>
	            <li class="small2 small3 small4"><a href="/mypage/orderList/${member.id}">주문내역</a></li>
	            <li class="small2 small5"><a href="/mypage/deliveryConfirm/${member.id}">배송현황</a></li>
	        </ul>
	    </aside>
	    
	    <!-- c:forEach 로 주문 내역이 여러 개면 반복할 수 있도록 해야 할 거 같음.. 
	        페이징 처리는 어떻게 해야 할지 모르겠다.. -->
	    <!-- 주문 내역 -->
	
	    <!-- 주문 내역 전체 래퍼 -->
	    <div class="OrderListWrapper">
	
	        <!-- 주문 내역 타이틀 -->
	        <div id="orderlist_title">주문내역</div>
	        <!-- 구분선 -->
	        <div class="separator"></div>
	        <!-- 상품 반복 -->
	        <!-- 다른건 다 잘되어 있어서 forEach문만 추가함!
	        	 forEach문으로 List에 담은 orderDTO(주문정보 MYMG_ORDER)를 반복돌려서 여러개의 상품이 출력되도록 함 -->
	 		<c:forEach items="${orderDTO}" var="ol" varStatus="status">
		        <!-- 주문 내역 추가할 때마다 생기는 컨테이너 -->
		        <div class="orderlist_container">
		        
		            <!-- 위 컨테이너에서 배송 현황 빼고 수평 정렬하기 위해서 하나 더 만들었음 -->
		            <div class="orderlist_container2">
		
		                <!-- 수평 정렬 -->
		                <div class="orderlist_container3">

		                    <!-- 이미지 옆 주문 번호, 가격, 결제일 간략 정보 -->
		                    <div class="orderlist_info">
		
		                        <!-- 주문 번호 -->
		                        <div class="info_container">
		                            <div class="info_title">주문 번호 :</div>
		                            <!-- DB에서 정보 불러오기!! -->
		                            <div class="order_no">${ol.no}번</div>
		                        </div>
		                        
		                        <!-- 가격 -->
		                        <div class="info_container">
		                            <div class="info_title">총 결제 금액 :</div>
		                            <div class="product_price">${totalPriceList[status.index]}원</div>
		                        </div>

		                        <!-- 결제일 -->
		                        <div class="info_container">
		                            <div class="info_title">결제일 :</div>
		                            <!-- DB에서 정보 불러오기!! -->
		                            <div class="payment_date">${ol.order_date}</div>
		                        </div>
		
		                    </div>
		
		                    <!-- 상세 주문 내역, 배송조회 버튼 컨테이너 -->
		                    <div class="button_container">
		                        <!-- 상세 주문 내역 -->
		                        <a type="button" href="/mypage/orderDetails/${member.id}?no=${ol.no}" class="orderlist_detail">상세주문내역</a>
		                        <!-- 배송 조회 -->
		                        <a type="button" href="/mypage/deliveryConfirm/${member.id}" class="delivery_tracking">배송조회</a>
		                    </div>
		                </div>
		            </div>
					
		        </div>
			</c:forEach>
	    </div>

</main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>

	<!-- DOM이 완전히 로드되지 않은 상태에서 스크립트가 실행되는 것을 막기 위해 아래에다가 위치시키기! -->
    <!-- 주문 내역 js -->
    <script src="/resources/js/mypage/OrderList.js"></script>
    <script src="/resources/js/login/Logout.js"></script>
</body>

</html>