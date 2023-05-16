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
    <title>MOYAMOGA</title>

	<!-- 파비콘 -->
    <link rel="shortcut icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/ico/favicon.ico" type="image/x-icon">
    
	<!-- 주문 내역 상세 css -->
    <link rel="stylesheet" href="/resources/css/mypage/OrderDetails.css">
    
	<!-- 주문 내역 상세 js -->
    <!-- <script src="/resources/js/mypage/derDetails.js"></script>  -->

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

    <!-- 주문 내역 상세 컨텐츠 전체 wrapper -->
    <div class="OrderDetailsWrapper">

        <!-- 상세주문내역 -->
        <div class="OrderDetailsContainer1">

            <!-- 상세주문내역타이틀 -->
            <div class="OrderDetailsTitle1">상세주문내역</div>
            <!-- 구분선 -->
            <div class="line1"></div>
            
            <div class="space"></div>

		<!-- <div>${productNames}</div>  -->
				<c:forEach var="productList" items="${productNames}">
		            <!-- 상세주문내역 contents 조절용 -->
		            <div class="order_details1">
		
		
		                <!-- 해당 상품에 대한 정보 -->
		                <div class="product_info_container">
		                
			                <!-- 주문 상품 사진 -->
			                <div class="product_photo">
			                    <!-- DB에서 상품 이미지 불러오기 -->
			                    <img src="/resources/product/${productList[0].getMain_image()}" alt="상품 이미지" class="product_photo2"/>
			                </div>
			                
		                    <div class="product_info">
		                        <div id="product_title">상품명:</div>
		                        <div id="product_name">${productList.get(0).getName()}</div>
		                    </div>
							
		                    <div class="product_info">
		                        <div id="product_title">총 가격:</div>
		                        <div id="product_price">${productList.get(0).getPrice()}원</div>
		                    </div> 
		
		                    <div class="product_info">
		                        <div id="product_title">결제일:</div>
		                        <div id="product_payment_date">${info.order_date}</div>
		                    </div> 
		
		                </div>
		
		            </div>
				</c:forEach>

        </div>


        <!-- 배송 정보 -->
        <div class="OrderDetailsContainer2">
            
            <!-- 배송 정보 타이틀 -->
            <div class="OrderDetailsTitle2">배송 정보</div>
            <!-- 구분선 -->
            <div class="line2"></div>

            <!-- 배송 정보 contents 조절용 -->
            <div class="order_details2">

                <!-- 수령인 -->
                <div class="delivery_info1">
                    <div id="delivery_title1">수령인</div>
                    <div id="receiver_name">${info.receiver_name}</div>
                </div>

                <!-- 연락처 -->
                <!-- 수령인 연락처인지 주문한 사람 연락처인지 모르겠음.. -->
                <div class="delivery_info2">
                    <div id="delivery_title2">연락처</div>
                    <div id="receiver_tel">${info.receiver_tel}</div>
                </div>

                <!-- 배송지 -->
                <div class="delivery_info3">
                    <div id="delivery_title3">배송지</div>
                    <div id="receiver_address">
                        <div id="receiver_address1">${info.receiver_address1}</div>
                        <div id="receiver_address2">${info.receiver_address2}</div>
                        <div id="receiver_address3">${info.receiver_address3}</div>
                    </div>
                </div>

                <!-- 배송메모 -->
                <div class="delivery_info4">
                    <div id="delivery_title4">배송메모</div>
                    <div id="delivery_memo">${info.delivery_comment}</div>
                </div>

            </div> 

        </div>


        <!-- 결제 정보 -->
        <div class="OrderDetailsContainer3">

            <!-- 결제 정보 타이틀 -->
            <div class="OrderDetailsTitle3">결제 정보</div>
            <!-- 구분선 -->
            <div class="line3"></div>

            <!-- 결제 정보 contents 조절용 -->
            <div class="order_details3">

                <div class="payment_amount_info">
                    <!-- 할인 안들어간 원래 상품 금액 -->
                    <div id="order_title">총 상품 가격</div>
                    <div id="total_product_amount">${totalPrice}원</div>
                </div>

                <div class="payment_amount_info">
                    <!-- 할인 얼마나 됐는지 금액 -->
                    <div id="order_title">할인 금액</div>
                    <div id="discount_amount">${totalDiscount}원</div>
                </div>

                <div class="payment_amount_info">
                    <!-- 배송비 -->
                    <div id="order_title">배송비</div>
                    <div id="delivery_charge">${info.delivery_cost}원</div>
                </div>

                <div class="payment_amount_info">
                    <!-- 총 상품 가격 - 할인 금액 + 배송비 결과 -->
                    <div id="order_title">총 결제 금액</div>
                    <div id="total_payment_amount">${finalPrice}원</div>
                </div>

            </div>
            
        </div>

    </div>

</main>
    
    <!-- footer -->
    <%@include file= "../common/footer.jsp" %>

	<!-- DOM이 완전히 로드되지 않은 상태에서 스크립트가 실행되는 것을 막기 위해 아래에다가 위치시키기! -->
    <!-- 주문 내역 상세 js -->
    <script src="/resources/js/mypage/OrderDetails.js"></script>
</body>

</html>